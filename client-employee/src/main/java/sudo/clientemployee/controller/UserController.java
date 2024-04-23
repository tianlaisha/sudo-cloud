package sudo.clientemployee.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sudo.clientemployee.MDCFilter.MDCFilter;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/9/2810:57
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(MDCFilter.class);

//    @Value("${server.port}")
//    private String port;

//    @Value("${foo}")
//    private String foo;
//    @RequestMapping(value = "/config")
//    public String config(){
//        return this.foo;
//    }

    @RequestMapping(value = "/traceId", method = RequestMethod.GET)
    public String traceId() {
        logger.info("traceId");
        return "traceId";
    }

//    @RequestMapping("/port")
//    public String clientUserPort(@RequestParam(value = "port")String port){
//        if (StringUtils.isNotEmpty(port)) this.port = port;
//        return "this is user port : " + this.port;
//    }

}
