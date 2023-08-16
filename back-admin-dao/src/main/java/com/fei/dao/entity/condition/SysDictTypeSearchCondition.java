package com.fei.dao.entity.condition;

import lombok.Data;
import com.fei.dao.entity.SysDictType;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description 字典类型查询条件
 */
@Data
public class SysDictTypeSearchCondition extends SysDictType {

    /**
     * 字典主键
     */
    private List<Long> dictIds;
}
