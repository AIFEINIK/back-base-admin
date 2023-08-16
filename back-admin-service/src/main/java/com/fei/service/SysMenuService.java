package com.fei.service;

import com.fei.interfaces.request.SearchSysMenuPageRequest;
import com.fei.interfaces.response.SearchSysMenuPageResponse;
import com.fei.common.page.Page;
import com.fei.common.result.Result;

/**
 * @author ZhangPengFei
 * @description 菜单权限Service接口
 */
public interface SysMenuService {

    /**
     * 查询菜单权限列表
     * 
     * @param request 查询参数
     * @return 菜单权限分页
     */
    Result<Page<SearchSysMenuPageResponse>> searchSysMenuPage(SearchSysMenuPageRequest request);
}
