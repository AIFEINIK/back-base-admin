package com.fei.common.interceptor;
import com.alibaba.fastjson2.JSON;
import com.fei.common.result.Result;
import com.fei.common.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ZhangPengFei
 * @description 参数校验拦截器
 */
@Aspect
@Component
@Slf4j
public class ParamCheckInterceptor {

    @Pointcut("execution(* com.fei.*.web.controller..*(..))" )
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object request = null;
        // 获取非get请求参数
        Object[] args = point.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return point.proceed();
        }
        request = Arrays.stream(args)
                .filter(arg -> Objects.nonNull(arg) && !(arg instanceof HttpServletRequest || arg instanceof HttpServletResponse ))
                .findAny().orElse(null);
        if (Objects.isNull(request)) {
            return point.proceed();
        }
        try {
            ValidationUtils.validate(request);
            return point.proceed();
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                log.warn("parameter check failed, method: {}, request:{}", point.getSignature().getName(), JSON.toJSONString(request));
                return new Result<>(500, e.getMessage());
            }
            return point.proceed();
        }
    }
}
