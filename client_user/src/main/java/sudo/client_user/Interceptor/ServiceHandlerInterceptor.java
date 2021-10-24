package sudo.client_user.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2415:15
 */
@Component
public class ServiceHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(ServiceHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        logger.debug("ServiceHandlerInterceptor -> InterceptorUrl->"+requestURI);
        return true;
    }

}
