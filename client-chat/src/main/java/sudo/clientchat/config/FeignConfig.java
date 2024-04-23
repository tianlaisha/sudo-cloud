package sudo.clientchat.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2024/4/2316:51
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {

        // 记录所有的请求和响应的明细，包括头信息，请求体信息，元数据
        return Logger.Level.FULL;
    }
}
