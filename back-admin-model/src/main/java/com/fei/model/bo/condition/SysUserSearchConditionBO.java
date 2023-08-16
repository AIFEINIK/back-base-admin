package com.fei.model.bo.condition;

import lombok.Data;

import java.util.Date;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class SysUserSearchConditionBO {

    private String userName;
    private Date beginCreateTime;
    private Date endCreateTime;
}
