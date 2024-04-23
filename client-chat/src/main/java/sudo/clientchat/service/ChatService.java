package sudo.clientchat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sudo.clientchat.config.FeignConfig;
import sudo.clientchat.service.ChatServiceImpl.ChatServiceHystric;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/289:21
 */
@FeignClient(value = "client-user", fallback = ChatServiceHystric.class, configuration = FeignConfig.class)
public interface ChatService {

    /**
     * openfeign 传递参数调用 服务间接口
     */
    @RequestMapping(value = "/port", method = RequestMethod.GET)
    public String clientUserPort(@RequestParam(value = "port") String port);

}
