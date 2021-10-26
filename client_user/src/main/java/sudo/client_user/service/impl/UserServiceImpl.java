package sudo.client_user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sudo.client_user.ThreadPool.UserThreadPool;
import sudo.client_user.entity.User;
import sudo.client_user.mapper.UserMapper;
import sudo.client_user.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:24
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    UserThreadPool userThreadPool = UserThreadPool.getUserThreadPool();

    @Resource
    UserMapper userMapper;

    @Override
    public User selectUser(Integer id) {
        logger.debug("service begin");
        return userMapper.selectUser(id);
    }

    @Override
    public void login(String username, String password) {
        // 此处多线程开发之后需要完善
        String[] s = password.split("");
        List<String> strs = Arrays.asList(s);
        logger.debug("msg:{}" , strs);
        strs.stream().forEach(c ->
                {
                    Future<String>  stringFuture = userThreadPool.submit(() -> "HELLO:" + c
                            + this.userThreadPool.getThreadFactory()+"-"+this.userThreadPool.getCorePoolSize()
                            + Thread.currentThread()
                        );
                    try {
                        logger.debug(stringFuture.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
