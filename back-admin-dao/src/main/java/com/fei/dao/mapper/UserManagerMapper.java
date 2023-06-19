package com.fei.dao.mapper;

import com.fei.dao.entity.SysUser;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerMapper {
    SysUser getSysUserByAccount(String account);

    void updateSysUser(SysUser sysUser);
}
