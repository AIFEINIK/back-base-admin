package com.fei.web.controller;

import com.fei.interfaces.request.SearchSysOrderPageRequest;
import com.fei.interfaces.response.SearchSysOrderPageResponse;
import com.fei.service.SysOrderService;
import com.fei.interfaces.facade.SysOrderFacade;
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
 * @description 系统订单Controller
 */
@Api(tags = "系统订单管理")
@RestController
@RequestMapping("/system/order")
public class SysOrderController implements SysOrderFacade {

    @Resource
    private SysOrderService sysOrderService;

    @Override
    @ApiOperation("查询系统订单列表")
    @PostMapping("/searchSysOrderPage")
    public Result<Page<SearchSysOrderPageResponse>> searchSysOrderPage(@RequestBody SearchSysOrderPageRequest request) {
        return sysOrderService.searchSysOrderPage(request);
    }
}
