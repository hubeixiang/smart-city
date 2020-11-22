package com.sct.commons.cache;

import com.sct.commons.cache.entity.BaseCacheEntity;
import com.sct.commons.cache.entity.CacheEntity;
import com.sct.commons.cache.entity.ICacheExpire;
import com.sct.commons.cache.entity.StringCacheEntity;
import com.sct.commons.cache.impl.SimpleMemoryCacheHolderService;

public final class SimpleMemoryCacheHolderServicesManager {
    private static final SimpleMemoryCacheHolderService memoryCacheHolder = SimpleMemoryCacheHolderService.getInstanse();
    private static SimpleMemoryCacheHolderServicesManager instance = null;

    private SimpleMemoryCacheHolderServicesManager() {
        CacheHolderServicesManager.getInstance().add(memoryCacheHolder);
    }

    public static SimpleMemoryCacheHolderServicesManager getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new SimpleMemoryCacheHolderServicesManager();
        }
    }

    public boolean exists(String key) {
        return memoryCacheHolder.isExists(key);
    }

    public void setString(String key, String value) {
        StringCacheEntity stringCacheEntity = new StringCacheEntity();
        stringCacheEntity.setCacheKey(key);
        stringCacheEntity.setCacheValue(value);
        memoryCacheHolder.register(stringCacheEntity, false);
    }

    public StringCacheEntity getString(String key) {
        CacheEntity cacheEntity = memoryCacheHolder.get(key);
        if (cacheEntity != null) {
            return (StringCacheEntity) cacheEntity;
        }
        return null;
    }

    public void setBaseCacheEntity(BaseCacheEntity baseCacheEntity, int timeout) {
        if (timeout > 0) {
            //比指定超时时间多5分钟,设置为自动清除缓存时间
            timeout = timeout + 300;
            ICacheExpire iCacheExpire = memoryCacheHolder.createDefaultICacheExpire(true, timeout);
            baseCacheEntity.setICacheExpire(iCacheExpire);
            memoryCacheHolder.register(baseCacheEntity);
        } else {
            memoryCacheHolder.register(baseCacheEntity, false);
        }
    }

    public BaseCacheEntity getBaseCacheEntity(String key) {
        CacheEntity cacheEntity = memoryCacheHolder.get(key);
        if (cacheEntity != null) {
            return (BaseCacheEntity) cacheEntity;
        }
        return null;
    }

    public void remove(String key) {
        memoryCacheHolder.clean(key);
    }
}
