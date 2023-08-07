package com.fei.common.support;

import com.alibaba.fastjson2.JSON;
import com.fei.common.cache.redis.RedisCache;
import com.fei.common.constants.CommonConstants;
import com.fei.common.entity.LoginUser;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangPengFei
 * @description
 */
@Component
public class TokenSupport {

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    @Value("${token.expireTime}")
    private int expireTime;

    @Value("${token.secret}")
    private String secret;

    @Resource
    private RedisCache redisCache;
    /**
     * 20分钟
     */
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 创建token
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        String token = UUID.randomUUID().toString();
        loginUser.setToken(token);
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * 60 * 1000L);

        String userKey = getTokenKey(token);
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);

        Map<String, Object> claims = Maps.newHashMap();
        claims.put(CommonConstants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getTokenKey(String uuid) {
        return CommonConstants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 验证token有效期
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新token
     * @param loginUser
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setExpireTime(System.currentTimeMillis() + expireTime * 60 * 1000L);
        String tokenKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(tokenKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        Claims claims = parseToken(token);
        String uuid = (String) claims.get(CommonConstants.LOGIN_USER_KEY);
        return JSON.parseObject(redisCache.getCacheObject(getTokenKey(uuid)), LoginUser.class);
    }

    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取请求token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CommonConstants.TOKEN_PREFIX)) {
            token = token.replace(CommonConstants.TOKEN_PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String tokenKey = getTokenKey(token);
            redisCache.deleteObject(tokenKey);
        }
    }
}
