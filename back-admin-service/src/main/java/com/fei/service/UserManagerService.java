package com.fei.service;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.request.GetSysUserRequest;
import com.fei.interfaces.request.ModifySysUserPasswordRequest;
import com.fei.interfaces.request.SearchSysUserPageRequest;
import com.fei.interfaces.response.SearchSysUserPageResponse;
import com.fei.interfaces.response.UserInfoResponse;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerService {
    Result<UserInfoResponse> getSysUser(GetSysUserRequest request);

    Result<Boolean> modifySysUserPassword(ModifySysUserPasswordRequest request);

    Result<Page<SearchSysUserPageResponse>> searchSysUserPage(SearchSysUserPageRequest request);
}
