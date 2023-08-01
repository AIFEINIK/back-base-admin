package com.fei.service.support;

import com.fei.common.constants.CommonConstants;
import com.fei.common.page.PageRequest;
import com.fei.common.utils.SecurityUtils;
import com.fei.domain.MenuDomain;
import com.fei.domain.RoleDomain;
import com.fei.model.bo.SysMenuBO;
import com.fei.model.bo.SysRoleBO;
import com.fei.model.bo.SysUserBO;
import com.fei.model.condition.SysMenuSearchCondition;
import com.fei.model.condition.SysRoleSearchCondition;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZhangPengFei
 * @description
 */
@Component
public class PermissionSupport {

    @Resource
    private RoleDomain roleDomain;
    @Resource
    private MenuDomain menuDomain;

    public Set<String> getRoles(SysUserBO sysUser) {
        if (sysUser.isAdmin()) {
            return Sets.newHashSet("admin");
        }

        SysRoleSearchCondition roleSearchCondition = new SysRoleSearchCondition();
        roleSearchCondition.setUserId(sysUser.getUserId());
        List<SysRoleBO> roles = roleDomain.searchSysUsers(roleSearchCondition, PageRequest.NO_PAGE);

        return roles.stream().map(SysRoleBO::getRoleKey)
                .filter(Objects::nonNull)
                .map(roleKey -> roleKey.trim().split(CommonConstants.COMMA))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
    }

    public Set<String> getPermissions(SysUserBO sysUser) {
        if (sysUser.isAdmin()) {
            return Sets.newHashSet("*:*:*");
        }

        SysMenuSearchCondition menuSearchCondition = new SysMenuSearchCondition();
        menuSearchCondition.setUserId(sysUser.getUserId());
        List<SysMenuBO> sysMenus = menuDomain.searchSysMenus(menuSearchCondition, PageRequest.NO_PAGE);
        return sysMenus.stream().map(SysMenuBO::getPerms)
                .filter(Objects::nonNull)
                .map(perms -> perms.trim().split(CommonConstants.COMMA))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
    }

    public List<SysMenuBO> getSysMenusByUserId(Long userId) {
        List<SysMenuBO> sysMenus;
        if (SecurityUtils.isAdmin(userId)) {
            SysMenuSearchCondition menuSearchCondition = new SysMenuSearchCondition();
            menuSearchCondition.setMenuTree(true);
            sysMenus = menuDomain.searchSysMenus(menuSearchCondition, PageRequest.NO_PAGE);

        } else {
            SysMenuSearchCondition menuSearchCondition = new SysMenuSearchCondition();
            menuSearchCondition.setUserId(userId);
            menuSearchCondition.setMenuTree(true);
            sysMenus = menuDomain.searchSysMenus(menuSearchCondition, PageRequest.NO_PAGE);
        }

        return getChildPerms(sysMenus, 0);
    }

    private List<SysMenuBO> getChildPerms(List<SysMenuBO> sysMenus, int parentId) {
        List<SysMenuBO> menus = new ArrayList<>();
        for (SysMenuBO menu : sysMenus) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (menu.getParentId() == parentId) {
                recursionFn(sysMenus, menu);
                menus.add(menu);
            }
        }
        return menus;
    }

    private void recursionFn(List<SysMenuBO> sysMenus, SysMenuBO menu) {
        // 得到子节点列表
        List<SysMenuBO> childList = getChildList(sysMenus, menu);
        menu.setChildren(childList);
        for (SysMenuBO tChild : childList) {
            if (hasChild(sysMenus, tChild)) {
                recursionFn(sysMenus, tChild);
            }
        }
    }

    private boolean hasChild(List<SysMenuBO> sysMenus, SysMenuBO tChild) {
        return getChildList(sysMenus, tChild).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuBO> getChildList(List<SysMenuBO> sysMenus, SysMenuBO menu) {
        List<SysMenuBO> subMenus = new ArrayList<>();
        for (SysMenuBO n : sysMenus) {
            if (n.getParentId().longValue() == menu.getMenuId().longValue()) {
                subMenus.add(n);
            }
        }
        return subMenus;
    }
}
