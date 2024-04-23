package sudo.gateway_server.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author glz
 * @version 1.0
 * @description gateway的全局过滤器 要实现GlobalFilter  Ordered
 * @date 2024/4/240:40
 */
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtils.isBlank(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();  //返回数据 终止
        }
        return chain.filter(exchange);  // 执行过滤后事情
    }

    @Override
    public int getOrder() {
        // 值越小越先执行
        return 1;
    }
}
