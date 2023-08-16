package com.fei.interfaces.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description 字典类型response
 */
@Data
@ApiModel("字典类型response")
public class SearchSysDictTypePageResponse implements Serializable {

    @ApiModelProperty("字典主键")
    private Long dictId;

    @ApiModelProperty("字典名称")
    private String dictName;

    @ApiModelProperty("字典类型")
    private String dictType;

    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("备注")
    private String remark;

}
