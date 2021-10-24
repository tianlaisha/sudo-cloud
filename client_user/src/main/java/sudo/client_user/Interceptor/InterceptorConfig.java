package sudo.client_user.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2415:06
 */
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UserHandlerInterceptor userHandlerInterceptor;
    @Autowired
    ServiceHandlerInterceptor serviceHandlerInterceptor;

    public String[] strs = new String[]{"/userById"};
    public String[] exstrs = new String[]{"/login"};

    public List<String> addUrls;
    public List<String> exUrls;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 多个拦截器 组成拦截器链 addPathPatterns 用于添加拦截规则 excludePathPatterns 用于排除拦截
        addUrls = Arrays.asList(strs);
        exUrls = Arrays.asList(exstrs);
        registry.addInterceptor(userHandlerInterceptor).addPathPatterns(exstrs).excludePathPatterns(strs);
        registry.addInterceptor(serviceHandlerInterceptor).addPathPatterns(strs).excludePathPatterns(exstrs);
        super.addInterceptors(registry);
    }

}
