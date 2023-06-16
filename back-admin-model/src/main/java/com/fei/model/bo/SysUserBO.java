package com.fei.model.bo;

import com.fei.model.enums.AccountStatusEnum;
import lombok.Data;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class SysUserBO {

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
     * 密码
     */
    private String password;

    /**
     * 账号状态
     */
    private AccountStatusEnum accountStatus;
}
