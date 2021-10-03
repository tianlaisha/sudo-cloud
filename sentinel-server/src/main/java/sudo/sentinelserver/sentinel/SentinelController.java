package sudo.sentinelserver.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/314:18
 */
@RestController
public class SentinelController {

    @SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
    @GetMapping("/hello")
    public String hello() {
        return "Hello Sentinel";
    }

    public String blockHandlerHello(BlockException e) {
        return "限流了";
    }
}
