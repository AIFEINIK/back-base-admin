package com.fei.dao.mapper;

import com.fei.dao.entity.SysMenu;
import com.fei.dao.entity.SysRole;
import com.fei.model.condition.SysMenuSearchCondition;
import com.fei.model.condition.SysRoleSearchCondition;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface MenuMapper {
    List<SysMenu> searchSysMenus(SysMenuSearchCondition condition);
}
