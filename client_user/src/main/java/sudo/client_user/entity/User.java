package sudo.client_user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:20
 */
@Data
@NoArgsConstructor
@ApiModel(description = "用户", value = "系统用户")
public class User {
    private Integer id;

    @ApiModelProperty(notes = "用户名称", name = "userName", required = true, value = "用户名")
    private String userName;

    private String passWord;

    private String realName;
}
