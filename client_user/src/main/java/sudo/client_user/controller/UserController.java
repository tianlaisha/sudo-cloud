package sudo.client_user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sudo.client_user.entity.User;
import sudo.client_user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2723:32
 * <p>
 * springmvc 是单例的  单例减少了创建servlet的资源，但当并发情况服务器会创建多个线程
 * 当存在成员变量（有状态->可以保存数据，是线程不安全的 无状态->不能保存数据，线程是安全的）
 */
@RestController
@Api(value = "UserController", description = "用户的控制层")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "端口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")
    })
    @RequestMapping("/port")
    public String clientUserPort(@RequestParam(value = "port") String port) {
        System.out.println(port);
//        if (StringUtils.isNotEmpty(port)) this.port = port;
        System.out.println(this.port + "===============================");

        logger.debug("this is the port: " + port);
        return "this is user port : " + this.port;
    }

    @RequestMapping("/userById")
    public String userById(@RequestParam(value = "id") Integer id) {
        logger.debug("========controler-日志-begin==========");
        User user = userService.selectUser(id);
        logger.debug(user.getUserName() + "==controller end=");
        return user.getUserName();
    }


    @RequestMapping("/login")
    public String login(@RequestParam("username") String userName, @RequestParam("password") String passWord) {
        userService.login(userName, passWord);
        // 之后统一升级异常处理
        if (true) {
            return "login success";
        } else {
            return "login error";
        }
    }


    // 采用restTemplate进行服务间调用
    @RequestMapping("/getEurekaRegistryInfo")
    public String getEurekaRegistryInfo() {
        List<ServiceInstance> instances = discoveryClient.getInstances("client-chat");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/mdc_traceId";
        logger.info("url:" + url);
        String s = userService.callChatFeign();
        logger.info("callChatFeign:" + s);
        return s;// restTemplate.getForObject(url,String.class);
    }

}
