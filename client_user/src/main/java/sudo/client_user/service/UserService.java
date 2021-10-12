package sudo.client_user.service;

import sudo.client_user.entity.User;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:23
 */
public interface UserService {

    public User selectUser(Integer id);

    public void login(String username ,String password);

}
