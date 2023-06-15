package com.fei.web.controller;

import com.fei.common.result.Result;
import com.fei.interfaces.facade.UserManagerFacade;
import com.fei.interfaces.request.GetUserInfoRequest;
import com.fei.interfaces.response.UserInfoResponse;
import com.fei.service.UserManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhangPengFei
 * @description
 */
@RestController
@RequestMapping("/userManager")
public class UserManagerController implements UserManagerFacade {

    @Resource
    private UserManagerService userManagerService;

    @Override
    @GetMapping("getUserInfo")
    public Result<UserInfoResponse> getUserInfo(@RequestBody GetUserInfoRequest request) {
        return userManagerService.getUserInfo(request);
    }
}
