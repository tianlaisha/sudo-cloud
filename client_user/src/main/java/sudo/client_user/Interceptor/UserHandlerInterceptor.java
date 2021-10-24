package sudo.client_user.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2415:13
 */
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(UserHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        logger.debug("UserHandlerInterceptor -> InterceptorUrl->"+requestURI);
        return true;
    }
}
