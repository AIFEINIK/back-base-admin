package com.fei.common.permission;

import com.fei.common.entity.LoginUser;
import com.fei.common.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ZhangPengFei
 * @description
 */
@Service("ss")
public class PermissionService {

    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    public boolean auth(String permission) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (Objects.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        return loginUser.getPermissions().contains(ALL_PERMISSION) || loginUser.getPermissions().contains(permission);
    }
}
