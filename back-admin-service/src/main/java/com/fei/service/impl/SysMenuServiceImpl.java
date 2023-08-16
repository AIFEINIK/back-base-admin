package com.fei.service.impl;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.request.SearchSysMenuPageRequest;
import com.fei.interfaces.response.SearchSysMenuPageResponse;
import com.fei.model.bo.SysMenuBO;
import com.fei.service.SysMenuService;
import com.fei.service.support.PermissionSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.fei.common.utils.BeanUtils.TransformEnumType;
import static com.fei.common.utils.BeanUtils.batchTransform;

/**
 * @author ZhangPengFei
 * @description 菜单权限Service业务层处理
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private PermissionSupport permissionSupport;

    /**
     * 查询菜单权限列表
     *
     * @param request 查询参数
     * @return 菜单权限分页
     */
    @Override
    public Result<Page<SearchSysMenuPageResponse>> searchSysMenuPage(SearchSysMenuPageRequest request) {
        List<SysMenuBO> sysMenus = permissionSupport.getSysMenusByUserId(request.getUserId());
        return new Result<>(Page.of(sysMenus.size(), batchTransform(SearchSysMenuPageResponse.class,
                    sysMenus, true, TransformEnumType.ENUM_TO_NAME)));
    }
}
