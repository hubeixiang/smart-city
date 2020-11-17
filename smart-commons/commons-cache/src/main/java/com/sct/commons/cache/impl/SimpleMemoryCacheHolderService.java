package com.sct.commons.cache.impl;


import com.sct.commons.cache.entity.CacheEntity;
import com.sct.commons.cache.entity.ICacheExpire;

public class SimpleMemoryCacheHolderService<E extends CacheEntity> extends AbstractCacheHolderService<E> {
    private static final int DEFAULT_EXPIRED_TIME = 180; // seconds.  keep 3 min
    private static SimpleMemoryCacheHolderService instanse = new SimpleMemoryCacheHolderService();

    private SimpleMemoryCacheHolderService() {
        super("simple-memory");
    }

    public static SimpleMemoryCacheHolderService getInstanse() {
        return instanse;
    }

    @Override
    public void register(E status) {
        if (status.isExpired()) {
            ICacheExpire iCacheExpire = createDefaultICacheExpire(true, DEFAULT_EXPIRED_TIME);
            status.setICacheExpire(iCacheExpire);
        }
        register(status, true);
    }
}
