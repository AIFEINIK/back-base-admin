package com.fei.interfaces.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description 登录request
 */
@ApiModel("登录request")
@Data
public class LoginRequest implements Serializable {

    /**
     * 账户
     */
    @NotBlank(message = "账号不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度为5-20位")
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;
}
