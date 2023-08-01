package com.fei.interfaces.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class GetSysUserRequest implements Serializable {

    private String userName;
}
