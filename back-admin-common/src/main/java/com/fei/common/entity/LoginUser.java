package com.fei.common.entity;

import com.fei.model.bo.SysUserBO;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ZhangPengFei
 * @description 登录用户身份权限
 */
@Data
public class LoginUser implements Serializable {

    /**
     * 系统用户
     */
    private SysUserBO sysUser;

    /**
     * 登录token
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    public LoginUser() {
    }

    public LoginUser(SysUserBO sysUser) {
        this.sysUser = sysUser;
    }

    public String getUserName() {
        return getSysUser().getUserName();
    }
}
