package com.fei.service.impl;

import com.fei.common.context.AuthenticationContextHolder;
import com.fei.common.entity.LoginUser;
import com.fei.common.enums.ReturnStatusEnum;
import com.fei.common.exceptions.ServiceException;
import com.fei.common.utils.SecurityUtils;
import com.fei.domain.UserManagerDomain;
import com.fei.model.bo.SysUserBO;
import com.fei.model.enums.AccountStatusEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ZhangPengFei
 * @description 用户验证处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserManagerDomain userManagerDomain;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserBO sysUser = userManagerDomain.getSysUser(username);
        // 合法性校验
        if (Objects.isNull(sysUser)) {
            throw new ServiceException(ReturnStatusEnum.SYS_USER_NOT_FOUND.toResult(username).getMessage());
        }

        if (AccountStatusEnum.NORMAL != sysUser.getAccountStatus()) {
            throw new ServiceException(ReturnStatusEnum.SYS_USER_ACCOUNT_STATUS_INVALID.toResult(username).getMessage());
        }

        // 密码校验
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        if (!SecurityUtils.matchesPassword(password, sysUser.getPassword())) {
            throw new ServiceException((ReturnStatusEnum.SYS_USER_PWD_INVALID.toResult(username).getMessage()));
        }
        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(SysUserBO sysUser) {
        return new LoginUser(sysUser);
    }
}
