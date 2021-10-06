package sudo.client_user.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sudo.client_user.entity.User;
import sudo.client_user.service.UserService;

import javax.annotation.Resource;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2723:32
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/port")
    public String clientUserPort(@RequestParam(value = "port")String port){
//        if (StringUtils.isNotEmpty(port)) this.port = port;
        System.out.println(this.port+"===============================");
        return "this is user port : " + this.port;
    }


    @RequestMapping("userById")
    public String userById(@RequestParam(value = "id")Integer id){
        User user = userService.selectUser(id);
        System.out.println(user.getUserName() + "==========================================");
        return user.getUserName();
    }

}
