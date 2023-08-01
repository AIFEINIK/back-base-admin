package com.fei.interfaces.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfoResponse implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态
     */
    private String status;

    /**
     * 删除标识
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private Long createTime;
}
