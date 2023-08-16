package com.fei.service;

import com.fei.interfaces.request.SearchSysDictTypePageRequest;
import com.fei.interfaces.response.SearchSysDictTypePageResponse;
import com.fei.common.page.Page;
import com.fei.common.result.Result;

/**
 * @author ZhangPengFei
 * @description 字典类型Service接口
 */
public interface SysDictTypeService {

    /**
     * 查询字典类型列表
     * 
     * @param request 查询参数
     * @return 字典类型分页
     */
    Result<Page<SearchSysDictTypePageResponse>> searchSysDictTypePage(SearchSysDictTypePageRequest request);
}
