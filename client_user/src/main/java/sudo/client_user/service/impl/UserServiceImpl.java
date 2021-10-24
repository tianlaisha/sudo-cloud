package sudo.client_user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sudo.client_user.entity.User;
import sudo.client_user.mapper.UserMapper;
import sudo.client_user.service.UserService;

import javax.annotation.Resource;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:24
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    UserMapper userMapper;

    @Override
    public User selectUser(Integer id) {
        logger.debug("service begin");
        return userMapper.selectUser(id);
    }

    @Override
    public void login(String username, String password) {
    }

}
