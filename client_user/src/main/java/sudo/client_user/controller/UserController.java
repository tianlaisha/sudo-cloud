package sudo.client_user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(value = "UserController",description = "用户的控制层")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "端口")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Success|OK")
    })
    @RequestMapping("/port")
    public String clientUserPort(@RequestParam(value = "port")String port){
        System.out.println(port);
//        if (StringUtils.isNotEmpty(port)) this.port = port;
        System.out.println(this.port+"===============================");

        logger.debug("this is the port: " + port );
        return "this is user port : " + this.port;
    }

    @RequestMapping("/userById")
    public String userById(@RequestParam(value = "id")Integer id){
        logger.debug("========controler-日志-begin==========");
        User user = userService.selectUser(id);
        logger.debug(user.getUserName()+"==controller end=");
        return user.getUserName();
    }


    @RequestMapping("/login")
    public String login(@RequestParam("username")String userName,@RequestParam("password")String passWord){
        userService.login(userName,passWord);
        // 之后统一升级异常处理
        if(true){
            return "login success";
        } else {
            return "login error";
        }
    }

}
