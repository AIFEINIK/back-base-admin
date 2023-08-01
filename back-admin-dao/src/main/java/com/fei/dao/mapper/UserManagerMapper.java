package com.fei.dao.mapper;

import com.fei.dao.entity.SysUser;
import com.fei.model.condition.SysUserSearchCondition;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerMapper {
    SysUser getSysUserByUserName(String userName);

    void updateSysUser(SysUser sysUser);

    int getSysUserCount(SysUserSearchCondition condition);

    List<SysUser> searchSysUsers(SysUserSearchCondition condition);
}
