package cz.brich.memsource.config;

import cz.brich.memsource.client.memsource.AuthenticationService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class FeignAuthInterceptor {

    private final AuthenticationService authenticationService;

    @Bean
    RequestInterceptor authInterceptor() {
        return request -> {
            request.header("Authorization", "ApiToken " + authenticationService.getToken());
        };
    }
}
