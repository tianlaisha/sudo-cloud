package sudo.client_user.entity;

import lombok.*;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:20
 */
@Data
@NoArgsConstructor
public class User {
    private Integer id;

    private String userName;

    private String passWord;

    private String realName;
}
