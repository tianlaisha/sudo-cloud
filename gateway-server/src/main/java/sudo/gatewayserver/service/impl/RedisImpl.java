package sudo.gatewayserver.service.impl;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sudo.gatewayserver.service.ReidsService;

import javax.annotation.Resource;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/311:14
 */
@Service
public class RedisImpl implements ReidsService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void redisOperate(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String getRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }


}
