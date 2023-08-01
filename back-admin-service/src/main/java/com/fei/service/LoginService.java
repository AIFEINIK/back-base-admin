package com.fei.service;

import com.fei.common.result.Result;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.GetRouterResponse;
import com.fei.interfaces.response.GetUserInfoResponse;
import com.fei.interfaces.response.LoginResponse;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description 登录
 */
public interface LoginService {
    Result<LoginResponse> login(LoginRequest request);

    Result<GetUserInfoResponse> getUserInfo();

    Result<List<GetRouterResponse>> getRouters();
}
