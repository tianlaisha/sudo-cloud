package sudo.client_user.feign;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author glz
 * @version 1.0
 * @description 描述
 * @date 2024/4/2312:13
 */
@Service
public class ChartFeignFallBack implements ChartFeign {

    @Override
    public String mdcTraceId() {
        return "网路出现异常";
    }
}
