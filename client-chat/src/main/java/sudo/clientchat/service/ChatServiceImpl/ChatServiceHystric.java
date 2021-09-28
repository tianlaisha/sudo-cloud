package sudo.clientchat.service.ChatServiceImpl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sudo.clientchat.service.ChatService;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2810:25
 */
@Service
public class ChatServiceHystric implements ChatService {

    @Override
    public String clientUserPort(String port) {
        return "sorry this port is null";
    }
}
