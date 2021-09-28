package sudo.clientemployee.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2810:57
 */
@RestController
public class UserController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/port")
    public String clientUserPort(@RequestParam(value = "port")String port){
        if (StringUtils.isNotEmpty(port)) this.port = port;
        return "this is user port : " + this.port;
    }

}
