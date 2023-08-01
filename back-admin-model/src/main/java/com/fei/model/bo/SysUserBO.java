package com.fei.model.bo;

import com.fei.model.enums.UserDelFlagEnum;
import com.fei.model.enums.UserSexEnum;
import com.fei.model.enums.UserStatusEnum;
import com.fei.model.enums.UserTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class SysUserBO implements Serializable {

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
    private UserTypeEnum userType;

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
    private UserSexEnum sex;

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
    private UserStatusEnum status;

    /**
     * 删除标识
     */
    private UserDelFlagEnum delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
