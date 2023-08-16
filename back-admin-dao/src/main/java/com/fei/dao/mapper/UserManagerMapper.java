package com.fei.dao.mapper;

import com.fei.dao.entity.SysUser;
import com.fei.model.bo.condition.SysUserSearchConditionBO;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerMapper {
    SysUser getSysUserByUserName(String userName);

    void updateSysUser(SysUser sysUser);

    int getSysUserCount(SysUserSearchConditionBO condition);

    List<SysUser> searchSysUsers(SysUserSearchConditionBO condition);
}
