package sudo.client_user.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * @author glz
 * @version 1.0
 * @description 线程上下文 ----后续做一套线程池的MDC日志   考虑一下服务间线程日志收集一致性
 * @date 2021/10/2722:51
 * <p>
 * Java中的ThreadLocal是用哈希表实现的，每个线程Thread维护一个ThreadLocalMap属性，里面就以Map的形式存储了多个ThreadLocal对象
 */
public class MDCThreadContext {

    /**
     * 线程上下文变量的持有者
     */
    private final static ThreadLocal<HashMap> ctx_holder = new ThreadLocal<HashMap>();

    static {
        ctx_holder.set(new HashMap());
    }

    private final static String TRACE_ID = "trace_id";

    private final static String SESSION_KEY = "token";

    private final static String VISTOR_ID = "user_id";

    private final static String CLIENT_ID = "clientIp";

    public final static void putContext(String key, Object value) {
        Map map = ctx_holder.get();

        if (map == null) {
            return;
        }
        map.put(key, value);
    }

    public final static <T> T getContext(String key) {
        Map map = ctx_holder.get();
        if (map == null) {
            return null;
        }
        return (T) map.get(key);
    }


    public final static Map getContext() {
        Map map = ctx_holder.get();
        if (map == null) {
            return null;
        }
        return map;
    }

    public final static void remove(String key) {
        Map map = ctx_holder.get();
        if (map != null) {
            map.remove(key);
        }
    }

    public final static boolean contains(String key) {
        Map map = ctx_holder.get();
        if (map != null) {
            return map.containsKey(key);
        }
        return false;
    }

    public final static void clean() {
        ctx_holder.remove();
    }

    public final static void init() {
        ctx_holder.set(new HashMap());
    }

    public final static void putTraceId(String traceId) {
        putContext(TRACE_ID, traceId);
    }

    public final static String getTraceId() {
        return getContext(TRACE_ID);
    }


}
