package com.fei.interfaces.facade;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.request.SearchSysOrderPageRequest;
import com.fei.interfaces.response.SearchSysOrderPageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangPengFei
 * @description 系统订单接口
 */
public interface SysOrderFacade {

    /**
    * 查询系统订单列表
    * @param request 请求参数
    * @return 分页结果
    */
    @PostMapping("/searchSysOrderPage")
    Result<Page<SearchSysOrderPageResponse>> searchSysOrderPage(@RequestBody SearchSysOrderPageRequest request);
}
