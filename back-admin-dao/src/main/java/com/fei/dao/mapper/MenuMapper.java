package com.fei.dao.mapper;

import com.fei.dao.entity.SysMenu;
import com.fei.model.bo.condition.SysMenuSearchConditionBO;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
public interface MenuMapper {
    List<SysMenu> searchSysMenus(SysMenuSearchConditionBO condition);
}
