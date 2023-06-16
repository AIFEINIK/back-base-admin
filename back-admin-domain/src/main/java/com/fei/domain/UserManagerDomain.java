package com.fei.domain;

import com.fei.common.utils.BeanUtils;
import com.fei.dao.entity.SysUser;
import com.fei.dao.mapper.UserManagerMapper;
import com.fei.model.bo.SysUserBO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZhangPengFei
 * @description
 */
@Component
public class UserManagerDomain {

    @Resource
    private UserManagerMapper userManagerMapper;

    public SysUserBO getSysUser(String account) {
        SysUser sysUser = userManagerMapper.getSysUserByAccount(account);
        return BeanUtils.transform(SysUserBO.class, sysUser, true, BeanUtils.TransformEnumType.VALUE_TO_ENUM);
    }
}
