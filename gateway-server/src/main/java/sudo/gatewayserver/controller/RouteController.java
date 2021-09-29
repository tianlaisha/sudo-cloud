package sudo.gatewayserver.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes().route(
                p -> p.path("/get").uri("http://localhost:9002")
        ).build();
    }

}
