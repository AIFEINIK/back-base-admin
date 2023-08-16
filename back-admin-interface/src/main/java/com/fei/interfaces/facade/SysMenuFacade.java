package com.fei.interfaces.facade;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.request.SearchSysMenuPageRequest;
import com.fei.interfaces.response.SearchSysMenuPageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangPengFei
 * @description 菜单权限接口
 */
public interface SysMenuFacade {

    /**
    * 查询菜单权限列表
    * @param request 请求参数
    * @return 分页结果
    */
    @PostMapping("/searchSysMenuPage")
    Result<Page<SearchSysMenuPageResponse>> searchSysMenuPage(@RequestBody SearchSysMenuPageRequest request);
}
