package com.fei.interfaces.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
@Builder
@ApiModel("登录response")
public class LoginResponse implements Serializable {

    @ApiModelProperty("登录token")
    private String token;
}
