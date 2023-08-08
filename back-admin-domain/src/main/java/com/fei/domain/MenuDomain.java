package com.fei.domain;

import com.fei.common.page.PageRequest;
import com.fei.common.utils.BeanUtils;
import com.fei.dao.mapper.MenuMapper;
import com.fei.model.bo.SysMenuBO;
import com.fei.model.bo.condition.SysMenuSearchCondition;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description 权限
 */
@Component
public class MenuDomain {

    @Resource
    private MenuMapper menuMapper;

    public List<SysMenuBO> searchSysMenus(SysMenuSearchCondition condition, PageRequest page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);
        return BeanUtils.batchTransform(SysMenuBO.class, menuMapper.searchSysMenus(condition), BeanUtils.TransformEnumType.VALUE_TO_ENUM);
    }
}
