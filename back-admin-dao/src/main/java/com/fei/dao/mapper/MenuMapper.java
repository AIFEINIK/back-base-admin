package com.fei.dao.mapper;

import com.fei.dao.entity.SysMenu;
import com.fei.model.bo.condition.SysMenuSearchCondition;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface MenuMapper {
    List<SysMenu> searchSysMenus(SysMenuSearchCondition condition);
}
