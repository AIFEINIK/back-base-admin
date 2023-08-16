package com.fei.interfaces.request;

import com.fei.common.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangPengFei
 * @description 字典类型request
 */
@Data
@ApiModel("字典类型request")
public class SearchSysDictTypePageRequest extends PageRequest implements Serializable {

    @ApiModelProperty("字典主键集合")
    private List<Long> dictIds;

}
