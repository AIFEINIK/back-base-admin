package com.fei.domain;

import java.util.List;
import java.util.Objects;

import static com.fei.common.utils.BeanUtils.*;

import com.fei.common.page.PageRequest;
import com.fei.dao.entity.condition.SysOrderSearchCondition;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.fei.dao.mapper.SysOrderMapper;
import com.fei.dao.entity.SysOrder;
import com.fei.model.bo.SysOrderBO;
import com.fei.model.bo.condition.SysOrderSearchConditionBO;

/**
 * @author ZhangPengFei
 * @description 系统订单 Domain层处理
 */
@Component
public class SysOrderDomain {

    @Resource
    private SysOrderMapper sysOrderMapper;

    /**
     * 新增系统订单
     *
     * @param sysOrder 系统订单
     * @return 结果
     */
    public int saveSysOrder(SysOrderBO sysOrder) {
        if (Objects.isNull(sysOrder)) {
            return 0;
        }
        return sysOrderMapper.saveSysOrder(transform(SysOrder.class,
            sysOrder, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 批量新增系统订单
     *
     * @param sysOrders 系统订单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveSysOrder(List<SysOrderBO> sysOrders) {
        if (CollectionUtils.isEmpty(sysOrders)) {
            return 0;
        }
        return sysOrderMapper.batchSaveSysOrder(batchTransform(SysOrder.class,
            sysOrders, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 删除系统订单信息
     *
     * @param orderId 系统订单主键
     * @return 结果
     */
    public int removeSysOrderByOrderId(Long orderId) {
        return sysOrderMapper.deleteSysOrderByOrderId(orderId);
    }

    /**
     * 批量删除系统订单
     *
     * @param orderIds 需要删除的系统订单主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int removeSysOrderByOrderIds(List<Long> orderIds) {
        return sysOrderMapper.deleteSysOrderByOrderIds(orderIds);
    }

    /**
     * 修改系统订单
     *
     * @param sysOrder 系统订单
     * @return 结果
     */
    public int modifySysOrder(SysOrderBO sysOrder) {
        if(Objects.isNull(sysOrder) || Objects.isNull(sysOrder.getOrderId())) {
            return 0;
        }
        return sysOrderMapper.modifySysOrder(transform(SysOrder.class,
            sysOrder, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 批量修改系统订单
     *
     * @param sysOrders 系统订单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchModifySysOrder(List<SysOrderBO> sysOrders) {
        if(CollectionUtils.isEmpty(sysOrders)) {
            return 0;
        }

        if (sysOrders.stream().anyMatch(sysOrder -> Objects.isNull(sysOrder.getOrderId()))) {
            return 0;
        }

        return sysOrderMapper.batchModifySysOrder(batchTransform(SysOrder.class,
            sysOrders, TransformEnumType.ENUM_TO_VALUE));
    }

    /**
     * 查询系统订单
     * 
     * @param orderId 系统订单主键
     * @return 系统订单
     */
    public SysOrderBO getSysOrderByOrderId(Long orderId) {
        SysOrder sysOrder = sysOrderMapper.getSysOrderByOrderId(orderId);
        return transform(SysOrderBO.class, sysOrder, TransformEnumType.VALUE_TO_ENUM);
    }

    /**
     * 查询系统订单列表
     * 
     * @param condition 查询条件
     * @param page 分页
     * @return 系统订单
     */
    public List<SysOrderBO> searchSysOrderWithCondition(SysOrderSearchConditionBO condition, PageRequest page) {
        if (Objects.isNull(page)) {
            page = PageRequest.of(1, PageRequest.DEFAULT_MAX_PAGE_SIZE);
        }
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);

        return batchTransform(SysOrderBO.class,
            sysOrderMapper.searchSysOrderWithCondition(transform(SysOrderSearchCondition.class,
                    condition, TransformEnumType.ENUM_TO_VALUE)), TransformEnumType.VALUE_TO_ENUM);
    }

    /**
     * 查询系统订单总数
     *
     * @param condition 查询条件
     * @return 系统订单总数
     */
    public int searchSysOrderCount(SysOrderSearchConditionBO condition) {
        return sysOrderMapper.searchSysOrderCount(transform(SysOrderSearchCondition.class,
                condition, TransformEnumType.ENUM_TO_VALUE));
    }
}
