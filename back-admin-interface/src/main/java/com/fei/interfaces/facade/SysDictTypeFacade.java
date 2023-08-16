package com.fei.interfaces.facade;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.interfaces.request.SearchSysDictTypePageRequest;
import com.fei.interfaces.response.SearchSysDictTypePageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangPengFei
 * @description 字典类型接口
 */
public interface SysDictTypeFacade {

    /**
    * 查询字典类型列表
    * @param request 请求参数
    * @return 分页结果
    */
    @PostMapping("/searchSysDictTypePage")
    Result<Page<SearchSysDictTypePageResponse>> searchSysDictTypePage(@RequestBody SearchSysDictTypePageRequest request);
}
