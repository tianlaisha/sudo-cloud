package sudo.clientchat.service.ChatServiceImpl;

import org.springframework.stereotype.Service;
import sudo.clientchat.service.ClientEmployeeService;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/12/623:13
 */
@Service
public class ClientEmployeeCallback implements ClientEmployeeService {
    @Override
    public String traceId() {
        return "fallback traceId";
    }
}
