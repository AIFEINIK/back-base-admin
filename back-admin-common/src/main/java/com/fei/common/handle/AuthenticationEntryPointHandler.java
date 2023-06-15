package com.fei.common.handle;

import com.alibaba.fastjson2.JSON;
import com.fei.common.result.Result;
import com.fei.common.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @author ZhangPengFei
 * @description 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        String msg = MessageFormat.format("请求访问：{0}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(Result.error(HttpStatus.UNAUTHORIZED.value(), msg)));
    }
}
