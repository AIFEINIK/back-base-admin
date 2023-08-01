package com.fei.interfaces.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class SearchSysUserPageResponse implements Serializable {
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
    private String account;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账号状态
     */
    private String accountStatus;

    /**
     * 创建时间
     */
    private Long createTime;
}
