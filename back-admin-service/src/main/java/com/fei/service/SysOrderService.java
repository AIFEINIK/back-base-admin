package com.fei.service;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.request.SearchSysOrderPageRequest;
import com.fei.interfaces.response.SearchSysOrderPageResponse;

/**
 * @author ZhangPengFei
 * @description 系统订单Service接口
 */
public interface SysOrderService {

    /**
     * 查询系统订单列表
     * 
     * @param request 查询参数
     * @return 系统订单分页
     */
    Result<Page<SearchSysOrderPageResponse>> searchSysOrderPage(SearchSysOrderPageRequest request);
}
