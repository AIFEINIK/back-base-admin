package com.fei.domain;

import com.fei.common.page.PageRequest;
import com.fei.common.utils.BeanUtils;
import com.fei.dao.entity.SysUser;
import com.fei.dao.mapper.UserManagerMapper;
import com.fei.model.bo.SysUserBO;
import com.fei.model.condition.SysUserSearchCondition;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
@Component
public class UserManagerDomain {

    @Resource
    private UserManagerMapper userManagerMapper;

    public SysUserBO getSysUser(String userName) {
        SysUser sysUser = userManagerMapper.getSysUserByUserName(userName);
        return BeanUtils.transform(SysUserBO.class, sysUser, true, BeanUtils.TransformEnumType.VALUE_TO_ENUM);
    }

    public void updateSysUser(SysUserBO sysUser) {
        userManagerMapper.updateSysUser(BeanUtils.transform(SysUser.class, sysUser, BeanUtils.TransformEnumType.ENUM_TO_VALUE));
    }

    public int getSysUserCount(SysUserSearchCondition condition) {
        return userManagerMapper.getSysUserCount(condition);
    }

    public List<SysUserBO> searchSysUsers(SysUserSearchCondition condition, PageRequest page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);
        return BeanUtils.batchTransform(SysUserBO.class, userManagerMapper.searchSysUsers(condition), BeanUtils.TransformEnumType.VALUE_TO_ENUM);
    }
}
