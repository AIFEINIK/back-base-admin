package com.fei.common.cache.localcache;

import com.fei.common.entity.LoginUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangPengFei
 * @description
 */
@Component
public class LoginUserCache extends LocalCache<String, LoginUser> {

    @Value("${token.expireTime}")
    private int expireTime;

    @Override
    protected long getExpire() {
        return expireTime;
    }

    @Override
    protected TimeUnit timeUnit() {
        return TimeUnit.MINUTES;
    }
}
