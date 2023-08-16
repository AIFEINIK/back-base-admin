package com.fei.dao.mapper;

import com.fei.dao.entity.SysRole;
import com.fei.model.bo.condition.SysRoleSearchConditionBO;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface RoleMapper {
    List<SysRole> searchSysRoles(SysRoleSearchConditionBO condition);
}
