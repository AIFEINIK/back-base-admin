package com.fei.interfaces.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class GetUserInfoRequest implements Serializable {

    private String account;
    private String userName;
}
