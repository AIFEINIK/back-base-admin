package com.fei.dao.mapper;

import java.util.List;
import com.fei.dao.entity.SysDictType;
import com.fei.dao.entity.condition.SysDictTypeSearchCondition;

/**
 * @author ZhangPengFei
 * @description 字典类型Mapper接口
 */
public interface SysDictTypeMapper {

    /**
     * 新增字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    int saveSysDictType(SysDictType sysDictType);

    /**
     * 批量新增字典类型
     *
     * @param sysDictTypes 字典类型
     * @return 结果
     */
    int batchSaveSysDictType(List<SysDictType> sysDictTypes);

    /**
     * 删除字典类型
     * 
     * @param dictId 字典类型主键
     * @return 结果
     */
    int deleteSysDictTypeByDictId(Long dictId);

    /**
     * 批量删除字典类型
     * 
     * @param dictIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysDictTypeByDictIds(List<Long> dictIds);

    /**
     * 根据主键修改字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    int modifySysDictType(SysDictType sysDictType);

    /**
     * 批量根据主键修改字典类型
     *
     * @param sysDictTypes 字典类型
     * @return 结果
     */
    int batchModifySysDictType(List<SysDictType> sysDictTypes);

    /**
     * 查询字典类型
     *
     * @param dictId 字典类型主键
     * @return 字典类型
     */
    SysDictType getSysDictTypeByDictId(Long dictId);

    /**
     * 查询字典类型列表
     *
     * @param condition 查询条件
     * @return 字典类型集合
     */
    List<SysDictType> searchSysDictTypeWithCondition(SysDictTypeSearchCondition condition);

    /**
     * 查询字典类型总数
     *
     * @param condition 查询条件
     * @return 字典类型总数
     */
    int searchSysDictTypeCount(SysDictTypeSearchCondition condition);
}
