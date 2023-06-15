package com.fei.common.cache.localcache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangPengFei
 * @description
 */
@Component
public class JwtTokenCache extends LocalCache<String, String> {

    @Override
    protected long getExpire() {
        return 8;
    }

    @Override
    protected TimeUnit timeUnit() {
        return TimeUnit.HOURS;
    }
}
