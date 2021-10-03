package sudo.gatewayserver.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/311:07
 */
public class HostAddrKeyResolver implements KeyResolver {

    // 根据主机进行限流
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

    @Bean
    public HostAddrKeyResolver HostAddrKeyResolver(){
        return new HostAddrKeyResolver();
    }
}
