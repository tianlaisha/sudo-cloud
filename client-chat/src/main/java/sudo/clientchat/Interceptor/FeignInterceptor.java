package sudo.clientchat.Interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/12/70:30
 */
@Component
public class FeignInterceptor implements RequestInterceptor {

    private static final String TRACE_ID = "traceId";

    @Override
    public void apply(RequestTemplate template) {
        template.header(TRACE_ID, MDC.get(TRACE_ID));
    }
}
