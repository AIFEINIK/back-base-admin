package com.fei.service;

import com.fei.common.result.Result;
import com.fei.interfaces.request.GetUserInfoRequest;
import com.fei.interfaces.response.UserInfoResponse;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerService {
    Result<UserInfoResponse> getUserInfo(GetUserInfoRequest request);
}
