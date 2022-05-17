package cz.brich.memsource.config;

import feign.Client;
import feign.Response;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Configuration
public class FeignClientConfig {

    private final Logbook logbook;
    private final ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create()
                .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
                .addInterceptorLast(new LogbookHttpResponseInterceptor())
                .useSystemProperties() // needed to use proxy cache from system properties
                .build();
    }

    // needed for logbook to work
    @Bean
    Client apacheHttpClient() {
        return new ApacheHttpClient(httpClient());
    }

    /**
     * Sprint encoder for encoding Page responses.
     * @return encoder
     */
    @Bean
    public Encoder encoder() {
        return new SpringEncoder(messageConverters);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder() {
            private ErrorDecoder delegate = new Default();

            @Override
            public Exception decode(String methodKey, Response response) {
                if (response.status() >= 400 && response.status() <= 599) {
                    byte[] responseBody;
                    try {
                        Response.Body body = response.body();
                        responseBody = body == null ? null : IOUtils.toByteArray(response.body().asInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to process response body.", e);
                    }
                    HttpHeaders responseHeaders = new HttpHeaders();
                    response.headers().forEach((key, value) -> responseHeaders.put(key, new ArrayList<>(value)));
                    return new HttpClientErrorException(HttpStatus.valueOf(response.status()), response.reason(), responseHeaders, responseBody, null);
                }
                return delegate.decode(methodKey, response);
            }
        };
    }
}
