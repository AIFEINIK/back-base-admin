package com.fei.dao.mapper;

import com.fei.dao.entity.UserInfo;

/**
 * @author ZhangPengFei
 * @description
 */
public interface UserManagerMapper {
    UserInfo getUserInfoByAccount(String account);
}
