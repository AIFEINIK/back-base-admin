package com.fei.interfaces.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ZhangPengFei
 * @description
 */
@Data
public class GetUserInfoResponse implements Serializable {

    private UserInfoResponse user;
    private Set<String> roles;
    private Set<String> permissions;
}
