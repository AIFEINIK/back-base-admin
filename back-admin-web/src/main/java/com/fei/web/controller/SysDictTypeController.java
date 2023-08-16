package com.fei.web.controller;

import com.fei.interfaces.request.SearchSysDictTypePageRequest;
import com.fei.interfaces.response.SearchSysDictTypePageResponse;
import com.fei.service.SysDictTypeService;
import com.fei.interfaces.facade.SysDictTypeFacade;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import com.fei.common.page.Page;
import com.fei.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangPengFei
 * @description 字典类型Controller
 */
@Api(tags = "字典类型管理")
@RestController
@RequestMapping("/fei/dict/type")
public class SysDictTypeController implements SysDictTypeFacade {

    @Resource
    private SysDictTypeService sysDictTypeService;

    @Override
    @ApiOperation("查询字典类型列表")
    @PostMapping("/searchSysDictTypePage")
    public Result<Page<SearchSysDictTypePageResponse>> searchSysDictTypePage(@RequestBody SearchSysDictTypePageRequest request) {
        return sysDictTypeService.searchSysDictTypePage(request);
    }
}
