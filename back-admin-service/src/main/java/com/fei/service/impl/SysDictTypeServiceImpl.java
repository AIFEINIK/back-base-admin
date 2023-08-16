package com.fei.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.fei.domain.SysDictTypeDomain;
import com.fei.interfaces.request.SearchSysDictTypePageRequest;
import com.fei.interfaces.response.SearchSysDictTypePageResponse;
import com.fei.model.bo.SysDictTypeBO;
import com.fei.model.bo.condition.SysDictTypeSearchConditionBO;
import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.common.page.PageRequest;
import com.fei.service.SysDictTypeService;
import org.springframework.stereotype.Service;
import static com.fei.common.utils.BeanUtils.*;

/**
 * @author ZhangPengFei
 * @description 字典类型Service业务层处理
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

    @Resource
    private SysDictTypeDomain sysDictTypeDomain;

    /**
     * 查询字典类型列表
     *
     * @param request 查询参数
     * @return 字典类型分页
     */
    @Override
    public Result<Page<SearchSysDictTypePageResponse>> searchSysDictTypePage(SearchSysDictTypePageRequest request) {
        SysDictTypeSearchConditionBO searchCondition = transform(SysDictTypeSearchConditionBO.class,
                request, true, TransformEnumType.NAME_TO_ENUM);

        int count = sysDictTypeDomain.searchSysDictTypeCount(searchCondition);
        if (count == 0) {
            return Result.success(Page.emptyPage());
        }

        List<SysDictTypeBO> sysDictTypes = sysDictTypeDomain.searchSysDictTypeWithCondition(searchCondition,
                PageRequest.of(request.getPageNum(), request.getPageSize()));
        return new Result<>(Page.of(count, batchTransform(SearchSysDictTypePageResponse.class,
                    sysDictTypes, true, TransformEnumType.ENUM_TO_NAME)));
    }
}
