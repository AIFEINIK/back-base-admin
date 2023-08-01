package com.fei.web.controller;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.facade.UserManagerFacade;
import com.fei.interfaces.request.GetSysUserRequest;
import com.fei.interfaces.request.ModifySysUserPasswordRequest;
import com.fei.interfaces.request.SearchSysUserPageRequest;
import com.fei.interfaces.response.SearchSysUserPageResponse;
import com.fei.interfaces.response.UserInfoResponse;
import com.fei.service.UserManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author ZhangPengFei
 * @description
 */
@RestController
@RequestMapping("/sysUser")
public class UserManagerController implements UserManagerFacade {

    @Resource
    private UserManagerService userManagerService;

    @Override
    public Result<UserInfoResponse> getSysUser(@RequestBody GetSysUserRequest request) {
        return userManagerService.getSysUser(request);
    }

    @Override
    public Result<Page<SearchSysUserPageResponse>> searchSysUserPage(@RequestBody SearchSysUserPageRequest request) {
        return userManagerService.searchSysUserPage(request);
    }

    @Override
    public Result<Boolean> modifySysUserPassword(@RequestBody ModifySysUserPasswordRequest request) {
        return userManagerService.modifySysUserPassword(request);
    }
}
