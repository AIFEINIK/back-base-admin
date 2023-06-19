package com.fei.interfaces.facade;


import com.fei.common.result.Result;
import com.fei.interfaces.request.GetSysUserRequest;
import com.fei.interfaces.request.ModifySysUserPasswordRequest;
import com.fei.interfaces.response.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerFacade {

    @GetMapping("getSysUser")
    Result<UserInfoResponse> getSysUser(@RequestBody GetSysUserRequest getUserInfoRequest);

    @PostMapping("modifySysUserPassword")
    Result<Boolean> modifySysUserPassword(@RequestBody ModifySysUserPasswordRequest request);
}
