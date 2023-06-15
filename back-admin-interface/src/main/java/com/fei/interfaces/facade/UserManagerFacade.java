package com.fei.interfaces.facade;


import com.fei.common.result.Result;
import com.fei.interfaces.request.GetUserInfoRequest;
import com.fei.interfaces.response.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerFacade {

    @GetMapping("getUserInfo")
    Result<UserInfoResponse> getUserInfo(@RequestBody GetUserInfoRequest getUserInfoRequest);
}
