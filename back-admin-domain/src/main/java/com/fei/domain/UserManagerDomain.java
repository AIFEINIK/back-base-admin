package com.fei.domain;

import com.fei.common.annotation.DataSource;
import com.fei.common.enums.DataSourceType;
import com.fei.common.utils.BeanUtils;
import com.fei.dao.entity.UserInfo;
import com.fei.dao.mapper.UserManagerMapper;
import com.fei.model.UserInfoBO;
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

    public UserInfoBO getUserInfo(String account) {
        UserInfo userInfo = userManagerMapper.getUserInfoByAccount(account);
        return BeanUtils.transform(UserInfoBO.class, userInfo, true, BeanUtils.TransformEnumType.VALUE_TO_ENUM);
    }
}
