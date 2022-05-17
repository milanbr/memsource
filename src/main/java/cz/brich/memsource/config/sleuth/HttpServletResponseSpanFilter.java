package cz.brich.memsource.config.sleuth;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HttpServletResponseSpanFilter extends GenericFilterBean {

    public static final String TRACE_ID_NAME = "X-B3-TraceId";
    public static final String PARENT_SPAN_ID = "X-B3-ParentSpanId";
    public static final String SPAN_ID_NAME = "X-B3-SpanId";

    private final Tracer tracer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Span currentSpan = this.tracer.currentSpan();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (currentSpan != null) {
            TraceContext context = currentSpan.context();
            httpServletResponse.setHeader(TRACE_ID_NAME, context.traceIdString());
            httpServletResponse.setHeader(SPAN_ID_NAME, context.spanIdString());

            String parentIdString = context.parentIdString();
            if (parentIdString != null) {
                httpServletResponse.setHeader(PARENT_SPAN_ID, context.parentIdString());
            }
        }
        chain.doFilter(request, httpServletResponse);
    }
}
