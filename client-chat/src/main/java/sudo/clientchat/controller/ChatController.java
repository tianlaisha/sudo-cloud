package sudo.clientchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sudo.clientchat.service.ChatService;
import sudo.clientchat.service.ClientEmployeeService;

import javax.annotation.Resource;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/289:28
 */
@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Resource
    ChatService chatService;

    @Resource
    ClientEmployeeService clientEmployeeService;

    @RequestMapping(value = "/port")
    public String userPort(@RequestParam(value = "port") String port) {
        return chatService.clientUserPort(port);
    }

    @RequestMapping("/get")
    public String gatewayGet() {
        return "this is gateway route way";
    }

    @RequestMapping("/mdc_traceId")
    public String mdctraceId() {
        // 调用traceid  全链路实现
        logger.info("logger==========================start");
        clientEmployeeService.traceId();
        return "traceId";
    }
}
