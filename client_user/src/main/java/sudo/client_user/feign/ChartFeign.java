package sudo.client_user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sudo.client_user.config.FeignConfig;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2024/4/2312:10
 */
@FeignClient(name = "client-chat",value = "client-chat", fallback = ChartFeignFallBack.class, configuration = FeignConfig.class)
public interface ChartFeign {

    @RequestMapping(value = "/mdc_traceId", method = RequestMethod.GET)
    public String mdcTraceId();

}
