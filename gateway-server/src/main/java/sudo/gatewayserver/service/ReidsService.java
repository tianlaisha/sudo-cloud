package sudo.gatewayserver.service;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/311:13
 */
public interface ReidsService {

    void redisOperate(String key ,String value);

    String getRedis(String key);

}
