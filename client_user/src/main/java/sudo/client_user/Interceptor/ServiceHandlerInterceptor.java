package sudo.client_user.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sudo.client_user.JWT.JWTManager;
import sudo.client_user.entity.User;

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
        logger.debug("ServiceHandlerInterceptor -> InterceptorUrl->" + requestURI);
        User user = new User();
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        boolean validateToken = JWTManager.verifyToken(token, password);
        validateToken = true; // 防止开发时设定一个开关  可以配置到配置文件中  更方便--后进行维护
        logger.debug("JWS验证TOKEN：{}" + validateToken);  // 请求的非登录路径进行拼写token参数从login打印的日志获取 进行验密
        return true;
    }

}
