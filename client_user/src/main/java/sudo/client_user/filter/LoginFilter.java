package sudo.client_user.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2414:45
 */
@Component
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String requestURI = req.getRequestURI();
        String password = (String) session.getAttribute("password");
        String username = (String) session.getAttribute("username");
        logger.debug("LoginFilter->请求路径:[{}-{}-{}]", requestURI, username, password);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
