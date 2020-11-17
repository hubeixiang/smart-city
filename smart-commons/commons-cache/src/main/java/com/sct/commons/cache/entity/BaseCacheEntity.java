package com.sct.commons.cache.entity;

public class BaseCacheEntity<T> implements CacheEntity<T> {
    private String cacheKey;
    private T cacheValue;
    private ICacheExpire iCacheExpire;

    @Override
    public String getCacheKey() {
        return cacheKey;
    }

    @Override
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    @Override
    public T getCacheValue() {
        return cacheValue;
    }

    @Override
    public void setCacheValue(T cacheValue) {
        this.cacheValue = cacheValue;
    }

    @Override
    public ICacheExpire getICacheExpire() {
        return iCacheExpire;
    }

    @Override
    public void setICacheExpire(ICacheExpire iCacheExpire) {
        this.iCacheExpire = iCacheExpire;
    }

    @Override
    public void destory() {
    }
}
