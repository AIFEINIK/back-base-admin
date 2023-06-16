package com.fei.interfaces.facade;

import com.fei.common.result.Result;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangPengFei
 * @description 登录
 */
public interface LoginFacade {

    Result<LoginResponse> login(@RequestBody LoginRequest request);
}
