package com.fei.service;

import com.fei.common.result.Result;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.LoginResponse;

/**
 * @author ZhangPengFei
 * @description 登录
 */
public interface LoginService {
    Result<LoginResponse> login(LoginRequest request);
}
