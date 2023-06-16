package com.fei.common.support;

import com.fei.common.cache.localcache.LoginUserCache;
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
import java.util.Map;
import java.util.UUID;

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
    private LoginUserCache loginUserCache;

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

        String userKey = getUserCacheKey(token);
        loginUserCache.set(userKey, loginUser);

        Map<String, Object> claims = Maps.newHashMap();
        claims.put(CommonConstants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getUserCacheKey(String token) {
        return CommonConstants.LOGIN_USER_KEY + token;
    }

    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        Claims claims = parseToken(token);
        String uid = (String) claims.get(CommonConstants.LOGIN_USER_KEY);
        return loginUserCache.get(getUserCacheKey(uid));
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

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CommonConstants.TOKEN_PREFIX)) {
            token = token.replace(CommonConstants.TOKEN_PREFIX, StringUtils.EMPTY);
        }
        return token;
    }
}
