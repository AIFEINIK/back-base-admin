package com.fei.domain;

import java.util.List;
import java.util.Objects;
import static com.fei.common.utils.BeanUtils.*;

import com.fei.dao.entity.condition.SysDictTypeSearchCondition;
import com.github.pagehelper.PageHelper;
import com.fei.common.page.PageRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.fei.dao.mapper.SysDictTypeMapper;
import com.fei.dao.entity.SysDictType;
import com.fei.model.bo.SysDictTypeBO;
import com.fei.model.bo.condition.SysDictTypeSearchConditionBO;

/**
 * @author ZhangPengFei
 * @description 字典类型Domain层处理
 */
@Component
public class SysDictTypeDomain {

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    /**
     * 新增字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    public int saveSysDictType(SysDictTypeBO sysDictType) {
        if (Objects.isNull(sysDictType)) {
            return 0;
        }
        return sysDictTypeMapper.saveSysDictType(transform(SysDictType.class,
            sysDictType, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 批量新增字典类型
     *
     * @param sysDictTypes 字典类型
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveSysDictType(List<SysDictTypeBO> sysDictTypes) {
        if (CollectionUtils.isEmpty(sysDictTypes)) {
            return 0;
        }
        return sysDictTypeMapper.batchSaveSysDictType(batchTransform(SysDictType.class,
            sysDictTypes, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 删除字典类型信息
     *
     * @param dictId 字典类型主键
     * @return 结果
     */
    public int removeSysDictTypeByDictId(Long dictId) {
        return sysDictTypeMapper.deleteSysDictTypeByDictId(dictId);
    }

    /**
     * 批量删除字典类型
     *
     * @param dictIds 需要删除的字典类型主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int removeSysDictTypeByDictIds(List<Long> dictIds) {
        return sysDictTypeMapper.deleteSysDictTypeByDictIds(dictIds);
    }

    /**
     * 修改字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    public int modifySysDictType(SysDictTypeBO sysDictType) {
        if(Objects.isNull(sysDictType) || Objects.isNull(sysDictType.getDictId())) {
            return 0;
        }
        return sysDictTypeMapper.modifySysDictType(transform(SysDictType.class,
            sysDictType, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 批量修改字典类型
     *
     * @param sysDictTypes 字典类型
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchModifySysDictType(List<SysDictTypeBO> sysDictTypes) {
        if(CollectionUtils.isEmpty(sysDictTypes)) {
            return 0;
        }

        if (sysDictTypes.stream().anyMatch(sysDictType -> Objects.isNull(sysDictType.getDictId()))) {
            return 0;
        }

        return sysDictTypeMapper.batchModifySysDictType(batchTransform(SysDictType.class,
            sysDictTypes, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 查询字典类型
     * 
     * @param dictId 字典类型主键
     * @return 字典类型
     */
    public SysDictTypeBO getSysDictTypeByDictId(Long dictId) {
        SysDictType sysDictType = sysDictTypeMapper.getSysDictTypeByDictId(dictId);
        return transform(SysDictTypeBO.class, sysDictType, TransformEnumType.VALUE_TO_ENUM);
    }

    /**
     * 查询字典类型列表
     * 
     * @param condition 查询条件
     * @param page 分页
     * @return 字典类型
     */
    public List<SysDictTypeBO> searchSysDictTypeWithCondition(SysDictTypeSearchConditionBO condition, PageRequest page) {
        if (Objects.isNull(page)) {
            page = PageRequest.of(1, PageRequest.DEFAULT_MAX_PAGE_SIZE);
        }
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);

        return batchTransform(SysDictTypeBO.class,
            sysDictTypeMapper.searchSysDictTypeWithCondition(transform(SysDictTypeSearchCondition.class,
                    condition, TransformEnumType.ENUM_TO_VALUE)), TransformEnumType.VALUE_TO_ENUM);
    }

    /**
     * 查询字典类型总数
     *
     * @param condition 查询条件
     * @return 字典类型总数
     */
    public int searchSysDictTypeCount(SysDictTypeSearchConditionBO condition) {
        return sysDictTypeMapper.searchSysDictTypeCount(transform(SysDictTypeSearchCondition.class,
                condition, TransformEnumType.ENUM_TO_VALUE));
    }
}
