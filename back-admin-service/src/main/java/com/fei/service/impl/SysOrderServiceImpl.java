package com.fei.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.fei.domain.SysOrderDomain;
import com.fei.interfaces.request.SearchSysOrderPageRequest;
import com.fei.interfaces.response.SearchSysOrderPageResponse;
import com.fei.model.bo.SysOrderBO;
import com.fei.model.bo.condition.SysOrderSearchConditionBO;
import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.common.page.PageRequest;
import com.fei.service.SysOrderService;
import org.springframework.stereotype.Service;
import static com.fei.common.utils.BeanUtils.*;

/**
 * @author ZhangPengFei
 * @description 系统订单Service业务层处理
 */
@Service
public class SysOrderServiceImpl implements SysOrderService {

    @Resource
    private SysOrderDomain sysOrderDomain;

    /**
     * 查询系统订单列表
     *
     * @param request 查询参数
     * @return 系统订单分页
     */
    @Override
    public Result<Page<SearchSysOrderPageResponse>> searchSysOrderPage(SearchSysOrderPageRequest request) {
        SysOrderSearchConditionBO searchCondition = transform(SysOrderSearchConditionBO.class,
                request, true, TransformEnumType.NAME_TO_ENUM);

        int count = sysOrderDomain.searchSysOrderCount(searchCondition);
        if (count == 0) {
            return Result.success(Page.emptyPage());
        }

        List<SysOrderBO> sysOrders = sysOrderDomain.searchSysOrderWithCondition(searchCondition,
                PageRequest.of(request.getPageNum(), request.getPageSize()));
        return new Result<>(Page.of(count, batchTransform(SearchSysOrderPageResponse.class,
                    sysOrders, true, TransformEnumType.ENUM_TO_NAME)));
    }
}
