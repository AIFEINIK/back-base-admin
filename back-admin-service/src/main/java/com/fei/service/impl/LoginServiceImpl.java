package com.fei.service.impl;

import com.fei.common.constants.CommonConstants;
import com.fei.common.entity.LoginUser;
import com.fei.common.enums.ReturnStatusEnum;
import com.fei.common.result.Result;
import com.fei.common.support.TokenSupport;
import com.fei.common.utils.BeanUtils;
import com.fei.common.utils.SecurityUtils;
import com.fei.domain.UserManagerDomain;
import com.fei.interfaces.request.LoginRequest;
import com.fei.interfaces.response.GetRouterResponse;
import com.fei.interfaces.response.GetUserInfoResponse;
import com.fei.interfaces.response.LoginResponse;
import com.fei.interfaces.response.UserInfoResponse;
import com.fei.model.bo.SysMenuBO;
import com.fei.model.bo.SysUserBO;
import com.fei.model.enums.MenuTypeEnum;
import com.fei.model.enums.MenuVisableEnum;
import com.fei.model.enums.UserStatusEnum;
import com.fei.service.LoginService;
import com.fei.service.support.PermissionSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author ZhangPengFei
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private TokenSupport tokenSupport;
    @Resource
    private UserManagerDomain userManagerDomain;
    @Resource
    private PermissionSupport permissionSupport;

    @Override
    public Result<LoginResponse> login(LoginRequest request) {
        SysUserBO sysUser = userManagerDomain.getSysUser(request.getUserName());
        // 合法性校验
        if (Objects.isNull(sysUser)) {
            return ReturnStatusEnum.SYS_USER_NOT_FOUND.toResult(request.getUserName());
        }

        if (UserStatusEnum.NORMAL != sysUser.getStatus()) {
            return ReturnStatusEnum.SYS_USER_ACCOUNT_STATUS_INVALID.toResult(request.getUserName());
        }

        // 密码校验
        if (!SecurityUtils.matchesPassword(request.getPassword(), sysUser.getPassword())) {
            return ReturnStatusEnum.SYS_USER_PWD_INVALID.toResult(request.getUserName());
        }

        LoginUser loginUser = new LoginUser(sysUser);
        String token = tokenSupport.createToken(loginUser);
        return Result.success(LoginResponse.builder().token(token).build());
    }

    @Override
    public Result<GetUserInfoResponse> getUserInfo() {
        SysUserBO sysUser = SecurityUtils.getLoginUser().getSysUser();

        GetUserInfoResponse response = new GetUserInfoResponse();
        response.setUser(BeanUtils.transform(UserInfoResponse.class, sysUser, BeanUtils.TransformEnumType.ENUM_TO_NAME));
        response.setRoles(permissionSupport.getRoles(sysUser));
        response.setPermissions(permissionSupport.getPermissions(sysUser));
        return Result.success(response);
    }

    @Override
    public Result<List<GetRouterResponse>> getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenuBO> sysMenus = permissionSupport.getSysMenusByUserId(userId);
        return Result.success(buildMenus(sysMenus));
    }

    private List<GetRouterResponse> buildMenus(List<SysMenuBO> sysMenus) {
        List<GetRouterResponse> routers = new LinkedList<>();
        for (SysMenuBO menu : sysMenus) {
            GetRouterResponse router = new GetRouterResponse();
            router.setHidden(MenuVisableEnum.DISABLE == menu.getVisible());
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new GetRouterResponse.Meta(menu.getMenuName(), menu.getIcon(), !menu.getIsCache(), menu.getPath()));

            List<SysMenuBO> cMenus = menu.getChildren();
            if (CollectionUtils.isNotEmpty(cMenus) && MenuTypeEnum.M == menu.getMenuType()) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));

            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<GetRouterResponse> childrenList = new ArrayList<>();
                GetRouterResponse children = new GetRouterResponse();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new GetRouterResponse.Meta(menu.getMenuName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);

            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new GetRouterResponse.Meta(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<GetRouterResponse> childrenList = new ArrayList<>();
                GetRouterResponse children = new GetRouterResponse();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(CommonConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new GetRouterResponse.Meta(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    public String getComponent(SysMenuBO menu) {
        String component = CommonConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = CommonConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = CommonConstants.PARENT_VIEW;
        }
        return component;
    }

    private boolean isParentView(SysMenuBO menu) {
        return menu.getParentId().intValue() != 0 && MenuTypeEnum.M == menu.getMenuType();
    }

    private String getRouterPath(SysMenuBO menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && MenuTypeEnum.M == menu.getMenuType()
                && !menu.getIsFrame()) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    private String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{CommonConstants.HTTP, CommonConstants.HTTPS, CommonConstants.WWW, "."},
                new String[]{"", "", "", "/"});
    }

    private boolean isInnerLink(SysMenuBO menu) {
        return !menu.getIsFrame() && StringUtils.startsWithAny(menu.getPath(), CommonConstants.HTTP, CommonConstants.HTTPS);
    }

    /**
     * 获取路由名称
     * @param menu
     * @return
     */
    private String getRouteName(SysMenuBO menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    private boolean isMenuFrame(SysMenuBO menu) {
        return menu.getParentId().intValue() == 0 && MenuTypeEnum.M == menu.getMenuType()
                && !menu.getIsFrame();
    }
}
