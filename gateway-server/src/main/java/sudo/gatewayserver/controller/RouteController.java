package sudo.gatewayserver.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sudo.gatewayserver.filter.ReqeustTimeFilter;
import sudo.gatewayserver.filter.TokenFilter;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2913:59
 */
@RestController
public class RouteController {

    @RequestMapping("/gateway")
    public String gateway(){
        return "this is gateway";
    }
    // 这个是简单的从代码层面进行设置路由转发
    // 使用自定义Filter
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes().route(
                p -> p.path("/get")
                        .filters(f -> f.filter(new ReqeustTimeFilter()))
                        .uri("http://localhost:9002")
        ).build();
    }

    // 全局filter  不用配置  全部请求都要过滤
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

}
