package com.fei.domain;

import com.fei.common.page.PageRequest;
import com.fei.common.utils.BeanUtils;
import com.fei.dao.mapper.RoleMapper;
import com.fei.model.bo.SysRoleBO;
import com.fei.model.condition.SysRoleSearchCondition;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description 角色
 */
@Component
public class RoleDomain {

    @Resource
    private RoleMapper roleMapper;

    public List<SysRoleBO> searchSysUsers(SysRoleSearchCondition condition, PageRequest page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);
        return BeanUtils.batchTransform(SysRoleBO.class, roleMapper.searchSysRoles(condition), BeanUtils.TransformEnumType.VALUE_TO_ENUM);
    }
}
