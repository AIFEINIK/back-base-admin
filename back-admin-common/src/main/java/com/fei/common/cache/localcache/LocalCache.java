package com.fei.common.cache.localcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangPengFei
 * @description 本地缓存
 */
@Slf4j
public abstract class LocalCache<K, V> {

    private final CacheLoader<K, V> cacheLoader = new CacheLoader<K, V>() {
        @Override
        public V load(K key) throws Exception {
            return null;
        }
    };

    protected LoadingCache<K, V> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(getExpire() <= 0 ? 5 : getExpire(), Objects.isNull(timeUnit()) ? TimeUnit.MINUTES : timeUnit())
            .build(cacheLoader());

    protected CacheLoader<K, V> cacheLoader() {
        return cacheLoader;
    };

    protected abstract long getExpire();

    protected abstract TimeUnit timeUnit();

    public V get(K key) {
        if (Objects.isNull(key)) {
            return null;
        }

        try {
            return cache.get(key);
        } catch (Exception e) {
            log.warn("local cache get key {} not exists", key);
        }
        return null;
    }

    public void set(K key, V value) {
        cache.put(key, value);
    }

    public void delKey(K key) {
        cache.invalidate(key);
    }
}
