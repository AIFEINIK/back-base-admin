package com.fei.interfaces.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfoResponse implements Serializable {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("住址")
    private String address;
}
