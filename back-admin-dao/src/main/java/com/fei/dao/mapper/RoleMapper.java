package com.fei.dao.mapper;

import com.fei.dao.entity.SysRole;
import com.fei.model.bo.condition.SysRoleSearchCondition;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface RoleMapper {
    List<SysRole> searchSysRoles(SysRoleSearchCondition condition);
}
