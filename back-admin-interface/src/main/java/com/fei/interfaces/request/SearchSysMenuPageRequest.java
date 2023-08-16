package com.fei.interfaces.request;

import com.fei.common.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description 菜单权限request
 */
@Data
@ApiModel("菜单权限request")
public class SearchSysMenuPageRequest extends PageRequest implements Serializable {

    @ApiModelProperty("菜单ID集合")
    private List<Long> menuIds;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("开始创建时间")
    private Long beginCreateTime;

    @ApiModelProperty("结束创建时间")
    private Long endCreateTime;

    @ApiModelProperty("开始更新时间")
    private Long beginUpdateTime;

    @ApiModelProperty("结束更新时间")
    private Long endUpdateTime;

}
