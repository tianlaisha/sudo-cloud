package sudo.clientchat.service.ChatServiceImpl;

import org.springframework.stereotype.Service;
import sudo.clientchat.chain.ApplicationFilterChain;
import sudo.clientchat.service.ChatService;

import javax.annotation.Resource;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2810:25
 */
@Service
public class ChatServiceHystric implements ChatService {

    @Resource
    ApplicationFilterChain applicationFilterChain;

    @Override
    public String clientUserPort(String port) {

        System.out.println(port + "=====================================");
        applicationFilterChain.doFilter("req is req", "res is res", applicationFilterChain);

        return "sorry this port is null";
    }
}
