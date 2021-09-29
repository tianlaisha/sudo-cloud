package sudo.clientchat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sudo.clientchat.service.ChatService;

import javax.annotation.Resource;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/289:28
 */
@RestController
public class ChatController {

    @Resource
    ChatService chatService;

    @RequestMapping( value = "/port")
    public String userPort(@RequestParam(value = "port") String port){
        System.out.println(port+"=====================================");
        return chatService.clientUserPort(port);
    }

    @RequestMapping("/get")
    public String gatewayGet(){
        return "this is gateway route way";
    }
}
