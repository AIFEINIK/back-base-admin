package com.fei.interfaces.request;

import com.fei.common.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description 系统订单request
 */
@Data
@ApiModel("系统订单request")
public class SearchSysOrderPageRequest extends PageRequest implements Serializable {

    @ApiModelProperty("订单IDs")
    private List<Long> orderIds;

    @ApiModelProperty("开始支付时间")
    private Long beginPayTime;

    @ApiModelProperty("结束支付时间")
    private Long endPayTime;

}
