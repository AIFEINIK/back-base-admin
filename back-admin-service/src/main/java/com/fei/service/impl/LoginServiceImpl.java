package com.fei.service.impl;

import com.fei.common.context.AuthenticationContextHolder;
import com.fei.common.entity.LoginUser;
import com.fei.common.result.Result;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.LoginResponse;
import com.fei.service.LoginService;
import com.fei.common.support.TokenSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhangPengFei
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private TokenSupport tokenSupport;

    @Override
    public Result<LoginResponse> login(LoginRequest request) {
        Authentication authenticate;
        try {
            // 用户验证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword());

            AuthenticationContextHolder.setContext(authenticationToken);

            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authenticate = authenticationManager.authenticate(authenticationToken);

        } catch (Exception e) {
            return Result.unKnowErr(e.getMessage());

        } finally {
            AuthenticationContextHolder.clear();
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String token = tokenSupport.createToken(loginUser);
        return Result.success(LoginResponse.builder().token(token).build());
    }
}
