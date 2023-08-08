package com.fei.dao.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author ZhangPengFei
 * @description 系统订单对象 sys_order
 */
@Data
public class SysOrder {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 已付
     */
    private Long payment;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 规格名称
     */
    private String skuName;

}