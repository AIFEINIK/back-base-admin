package com.fei.web.controller;

import com.fei.common.result.Result;
import com.fei.interfaces.facade.LoginFacade;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.GetRouterResponse;
import com.fei.interfaces.response.GetUserInfoResponse;
import com.fei.interfaces.response.LoginResponse;
import com.fei.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description
 */
@RestController
@Api("登录")
public class LoginController implements LoginFacade {

    @Resource
    private LoginService loginService;

    @Override
    @ApiOperation("登录")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }

    @Override
    @ApiOperation("获取用户信息")
    public Result<GetUserInfoResponse> getUserInfo() {
        return loginService.getUserInfo();
    }

    @Override
    public Result<List<GetRouterResponse>> getRouters() {
        return loginService.getRouters();
    }
}
