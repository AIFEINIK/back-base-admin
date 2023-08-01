package com.fei.common.handle;

import com.alibaba.fastjson2.JSON;
import com.fei.common.entity.LoginUser;
import com.fei.common.result.Result;
import com.fei.common.support.TokenSupport;
import com.fei.common.utils.ServletUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author ZhangPengFei
 * @description 自定义退出处理类 返回成功
 */
@Configuration
public class LogoutSuccessHandlerHandler implements LogoutSuccessHandler {

    @Resource
    private TokenSupport tokenSupport;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //LoginUser loginUser = tokenSupport.getLoginUser(request);
        //if (Objects.nonNull(loginUser)) {
        //    tokenSupport.delLoginUser(loginUser.getToken());
        //}
        ServletUtils.renderString(response, JSON.toJSONString(Result.success("退出成功")));
    }
}
