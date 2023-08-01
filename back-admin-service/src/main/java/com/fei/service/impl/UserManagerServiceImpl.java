package com.fei.service.impl;

import com.fei.common.entity.LoginUser;
import com.fei.common.enums.ReturnStatusEnum;
import com.fei.common.page.Page;
import com.fei.common.page.PageRequest;
import com.fei.common.result.Result;
import com.fei.common.utils.BeanUtils;
import com.fei.common.utils.SecurityUtils;
import com.fei.domain.UserManagerDomain;
import com.fei.interfaces.request.GetSysUserRequest;
import com.fei.interfaces.request.ModifySysUserPasswordRequest;
import com.fei.interfaces.request.SearchSysUserPageRequest;
import com.fei.interfaces.response.SearchSysUserPageResponse;
import com.fei.interfaces.response.UserInfoResponse;
import com.fei.model.bo.SysUserBO;
import com.fei.model.condition.SysUserSearchCondition;
import com.fei.service.UserManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
        SysUserBO userInfo = userManagerDomain.getSysUser(request.getUserName());
        return new Result<>(BeanUtils.transform(UserInfoResponse.class, userInfo, true, BeanUtils.TransformEnumType.ENUM_TO_NAME));
    }

    @Override
    public Result<Page<SearchSysUserPageResponse>> searchSysUserPage(SearchSysUserPageRequest request) {
        SysUserSearchCondition userSearchCondition = BeanUtils.transform(SysUserSearchCondition.class, request, true, BeanUtils.TransformEnumType.NAME_TO_ENUM);
        int count = userManagerDomain.getSysUserCount(userSearchCondition);
        if (count == 0) {
            return new Result<>(new Page<>(Collections.emptyList()));
        }
        List<SysUserBO> sysUsers = userManagerDomain.searchSysUsers(userSearchCondition, PageRequest.of(request.getPageNum(), request.getPageSize()));
        return new Result<>(new Page<>(count, BeanUtils.batchTransform(SearchSysUserPageResponse.class, sysUsers, true, BeanUtils.TransformEnumType.ENUM_TO_NAME)));
    }

    @Override
    public Result modifySysUserPassword(ModifySysUserPasswordRequest request) {
        if (Objects.equals(request.getOldPassword(), request.getNewPassword())) {
            return ReturnStatusEnum.SYS_USER_OLD_PWD_INVALID.toResult();
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUserBO sysUser = userManagerDomain.getSysUser(loginUser.getUserName());
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
