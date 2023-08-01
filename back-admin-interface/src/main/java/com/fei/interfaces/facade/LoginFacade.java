package com.fei.interfaces.facade;

import com.fei.common.result.Result;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.GetRouterResponse;
import com.fei.interfaces.response.GetUserInfoResponse;
import com.fei.interfaces.response.LoginResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description 登录
 */
public interface LoginFacade {

    @PostMapping("login")
    Result<LoginResponse> login(@RequestBody LoginRequest request);

    @GetMapping("getUserInfo")
    Result<GetUserInfoResponse> getUserInfo();

    @GetMapping("getRouters")
    Result<List<GetRouterResponse>> getRouters();
}
