package sudo.clientchat.MDCFilter;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sudo.clientchat.MDC.MDCUtil;
import sudo.clientchat.controller.ChatController;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/12/623:38
 */
@Component
public class MDCFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MDCFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 如果请求里没有traceid  那么生成并设置traceId
        String traceId = request.getParameter("traceId");
        if (StringUtils.isBlank(traceId) || MDCUtil.defaultTraceId(traceId)) {
            traceId = MDCUtil.genTraceId();
        }
        MDCUtil.setTraceId(traceId);
        logger.info(request.getLocalAddr() + "--" + request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
