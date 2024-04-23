package sudo.clientchat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sudo.clientchat.config.FeignConfig;
import sudo.clientchat.service.ChatServiceImpl.ClientEmployeeCallback;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/12/623:02
 */
@FeignClient(value = "client-employee", fallback = ClientEmployeeCallback.class, configuration = FeignConfig.class)
public interface ClientEmployeeService {

    /**
     * openfeign 传递参数调用 服务间接口
     */
    @RequestMapping(value = "/traceId", method = RequestMethod.GET)
    public String traceId();
}
