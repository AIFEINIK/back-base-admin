package com.fei.dao.mapper;

import java.util.List;
import com.fei.dao.entity.SysOrder;
import com.fei.dao.entity.condition.SysOrderSearchCondition;

/**
 * @author ZhangPengFei
 * @description 系统订单Mapper接口
 */
public interface SysOrderMapper {

    /**
     * 新增系统订单
     *
     * @param sysOrder 系统订单
     * @return 结果
     */
    int saveSysOrder(SysOrder sysOrder);

    /**
     * 批量新增系统订单
     *
     * @param sysOrders 系统订单
     * @return 结果
     */
    int batchSaveSysOrder(List<SysOrder> sysOrders);

    /**
     * 删除系统订单
     * 
     * @param orderId 系统订单主键
     * @return 结果
     */
    int deleteSysOrderByOrderId(Long orderId);

    /**
     * 批量删除系统订单
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysOrderByOrderIds(List<Long> orderIds);

    /**
     * 根据主键修改系统订单
     *
     * @param sysOrder 系统订单
     * @return 结果
     */
    int modifySysOrder(SysOrder sysOrder);

    /**
     * 批量根据主键修改系统订单
     *
     * @param sysOrders 系统订单
     * @return 结果
     */
    int batchModifySysOrder(List<SysOrder> sysOrders);

    /**
     * 查询系统订单
     *
     * @param orderId 系统订单主键
     * @return 系统订单
     */
    SysOrder getSysOrderByOrderId(Long orderId);

    /**
     * 查询系统订单列表
     *
     * @param condition 查询条件
     * @return 系统订单集合
     */
    List<SysOrder> searchSysOrderWithCondition(SysOrderSearchCondition condition);

    /**
     * 查询系统订单总数
     *
     * @param condition 查询条件
     * @return 系统订单总数
     */
    int searchSysOrderCount(SysOrderSearchCondition condition);
}
