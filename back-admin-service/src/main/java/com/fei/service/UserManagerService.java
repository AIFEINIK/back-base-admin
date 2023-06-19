package com.fei.service;

import com.fei.common.result.Result;
import com.fei.interfaces.request.GetSysUserRequest;
import com.fei.interfaces.request.ModifySysUserPasswordRequest;
import com.fei.interfaces.response.UserInfoResponse;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerService {
    Result<UserInfoResponse> getSysUser(GetSysUserRequest request);

    Result<Boolean> modifySysUserPassword(ModifySysUserPasswordRequest request);
}
