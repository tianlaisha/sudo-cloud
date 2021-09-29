package sudo.gatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author glz
 * @version 1.0
 * @description 自定义过滤器-添加请求开始时间  gatewayfilter是作用在每个route上  globlefilter是作用在全局上
 * @date 2021/9/2918:56
 */
public class ReqeustTimeFilter implements GatewayFilter, Ordered {


    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put(REQUEST_TIME_BEGIN,System.currentTimeMillis());
        return chain.filter(exchange);
    }
}
