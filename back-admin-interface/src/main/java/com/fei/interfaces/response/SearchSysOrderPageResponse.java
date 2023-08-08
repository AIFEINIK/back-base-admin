package com.fei.interfaces.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description 系统订单response
 */
@Data
@ApiModel("系统订单response")
public class SearchSysOrderPageResponse implements Serializable {

    @ApiModelProperty("支付时间")
    private Long payTime;
}
