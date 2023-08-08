package com.fei.model.bo.condition;

import lombok.Data;
import com.fei.model.bo.SysOrderBO;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description 系统订单查询条件
 */
@Data
public class SysOrderSearchConditionBO extends SysOrderBO {

    /**
     * 订单ID
     */
    private List<Long> orderIds;
}
