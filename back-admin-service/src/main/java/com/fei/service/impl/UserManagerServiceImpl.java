package com.fei.service.impl;

import com.fei.common.entity.LoginUser;
import com.fei.common.enums.ReturnStatusEnum;
import com.fei.common.result.Result;
import com.fei.common.utils.BeanUtils;
import com.fei.common.utils.SecurityUtils;
import com.fei.domain.UserManagerDomain;
import com.fei.interfaces.request.GetSysUserRequest;
import com.fei.interfaces.request.ModifySysUserPasswordRequest;
import com.fei.interfaces.response.UserInfoResponse;
import com.fei.model.bo.SysUserBO;
import com.fei.service.UserManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ZhangPengFei
 * @description
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Resource
    private UserManagerDomain userManagerDomain;

    @Override
    public Result<UserInfoResponse> getSysUser(GetSysUserRequest request) {
        SysUserBO userInfo = userManagerDomain.getSysUser(request.getAccount());
        return new Result<>(BeanUtils.transform(UserInfoResponse.class, userInfo, true, BeanUtils.TransformEnumType.ENUM_TO_NAME));
    }

    @Override
    public Result modifySysUserPassword(ModifySysUserPasswordRequest request) {
        if (Objects.equals(request.getOldPassword(), request.getNewPassword())) {
            return ReturnStatusEnum.SYS_USER_OLD_PWD_INVALID.toResult();
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUserBO sysUser = userManagerDomain.getSysUser(loginUser.getUsername());
        if (!SecurityUtils.matchesPassword(request.getOldPassword(), sysUser.getPassword())) {
            return ReturnStatusEnum.SYS_USER_NEW_PWD_INVALID.toResult();
        }

        SysUserBO updateUser = new SysUserBO();
        updateUser.setUserId(sysUser.getUserId());
        updateUser.setPassword(SecurityUtils.encryptPassword(request.getNewPassword()));
        userManagerDomain.updateSysUser(updateUser);
        return new Result<>();
    }
}
