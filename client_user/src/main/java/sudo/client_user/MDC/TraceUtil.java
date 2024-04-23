package sudo.client_user.MDC;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2723:32
 */
public class TraceUtil {

    public static void traceStart() {
        MDCThreadContext.init();

        String traceId = generateTraceId();

        MDC.put("traceId", traceId);

        MDCThreadContext.putTraceId(traceId);
    }

    public static void traceEnd() {
        MDC.clear();
        MDCThreadContext.clean();
    }

    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }
}
