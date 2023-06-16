package com.fei.service.impl;

import com.fei.common.result.Result;
import com.fei.common.utils.BeanUtils;
import com.fei.domain.UserManagerDomain;
import com.fei.interfaces.request.GetUserInfoRequest;
import com.fei.interfaces.response.UserInfoResponse;
import com.fei.model.bo.SysUserBO;
import com.fei.service.UserManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhangPengFei
 * @description
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Resource
    private UserManagerDomain userManagerDomain;

    @Override
    public Result<UserInfoResponse> getUserInfo(GetUserInfoRequest request) {
        SysUserBO userInfo = userManagerDomain.getSysUser(request.getAccount());
        return new Result<>(BeanUtils.transform(UserInfoResponse.class, userInfo, true, BeanUtils.TransformEnumType.ENUM_TO_NAME));
    }
}
