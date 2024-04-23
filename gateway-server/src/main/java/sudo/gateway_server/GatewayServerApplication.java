package sudo.gateway_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2024/4/2323:23
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class GatewayServerApplication {

    public static void main(String[] args){
        SpringApplication.run(GatewayServerApplication.class,args);
    }
}
