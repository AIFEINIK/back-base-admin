package com.fei.dao.entity.condition;

import lombok.Data;
import com.fei.dao.entity.SysOrder;

import java.util.List;

/**
 * @author ZhangPengFei
 * @description 系统订单查询条件
 */
@Data
public class SysOrderSearchCondition extends SysOrder {

    /**
     * 订单ID
     */
    private List<Long> orderIds;
}
