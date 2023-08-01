package com.fei.interfaces.request;

import com.fei.common.page.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class SearchSysUserPageRequest extends PageRequest implements Serializable {

    private String account;
    private Long beginCreateTime;
    private Long endCreateTime;
}
