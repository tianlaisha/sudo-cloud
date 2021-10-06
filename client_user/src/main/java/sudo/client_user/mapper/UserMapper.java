package sudo.client_user.mapper;

import org.springframework.stereotype.Repository;
import sudo.client_user.entity.User;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:31
 */
@Repository
public interface UserMapper {

    User selectUser(Integer id);

}
