package sudo.gateway_server.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author glz
 * @version 1.0
 * @description gateway的自定义局部过滤器实现DEMO
 * @date 2024/4/240:24
 */
@Component
public class MyParamGatewayFilterFactory extends AbstractGatewayFilterFactory<MyParamGatewayFilterFactory.Config> {


    public MyParamGatewayFilterFactory(){
        super(MyParamGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param");
    }

    @Override
    public GatewayFilter apply(Config config) {


        return (exchange, chain) -> {
            // 方法前过滤
            ServerHttpRequest request = exchange.getRequest();
            if(request.getQueryParams().containsKey(config.getParam())){
                request.getQueryParams().get(config.getParam()).forEach((v) -> {
                    System.out.printf("自定义局部过滤器的实现：%s= %s",config.param,v);
                });
            }
            return chain.filter(exchange);
            // 方法执行后过滤
        };
    }


    // 读取过滤器的参数
    public static class Config{

        // 对应application.yml文件中过滤器中的配置参数
        private String param;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }


    }
}
