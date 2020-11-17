package com.sct.commons.cache.impl;

import com.sct.commons.cache.ThreadCacheHolderCleanManager;
import com.sct.commons.cache.api.IStatusHolderService;
import com.sct.commons.cache.entity.CacheEntity;
import com.sct.commons.cache.entity.ICacheExpire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractCacheHolderService<E extends CacheEntity> implements IStatusHolderService<E> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCacheHolderService.class);
    private static final int DEFAULT_EXPIRED_TIME = 180; // seconds.  keep 3 min
    private final Map<String, E> Internal_Holder = new Hashtable<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String holderName;

    public AbstractCacheHolderService(String name) {
        ThreadCacheHolderCleanManager.getInstance().add(this);
        this.holderName = name;
    }

    public String holderName() {
        return holderName;
    }

    public void register(E progressStatus, boolean canExpire) {
        Assert.isTrue(progressStatus != null && progressStatus.getCacheKey() != null, "register Entity or Cache Key Can't null!");
        Assert.isTrue(progressStatus != null && progressStatus.getCacheValue() != null, "register Entity or Cache Value Can't null!");
        if (canExpire && (progressStatus.getICacheExpire() == null || progressStatus.getICacheExpire().getExpiredDate() == null)) {
            throw new RuntimeException("Expired Date can't null");
        } else if (progressStatus.isExpired()) {
            throw new RuntimeException("the cache entity has expired");
        }
        add(progressStatus.getCacheKey(), progressStatus);
    }

    protected void add(String key, E status) {
        try {
            readWriteLock.writeLock().lock();
            Internal_Holder.put(key, status);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public boolean isExists(String uuid) {
        try {
            readWriteLock.readLock().lock();
            return Internal_Holder.containsKey(uuid);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public E get(String uuid) {
        try {
            readWriteLock.readLock().lock();
            return Internal_Holder.get(uuid);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void clean(String uuid) {
        E status = null;
        try {
            readWriteLock.writeLock().lock();
            status = Internal_Holder.remove(uuid);
        } finally {
            readWriteLock.writeLock().unlock();
        }
        internalDestory(status);
        clean();
    }

    @Override
    public Collection<E> clean() {
        final Set<E> itemsToRemove = new HashSet<>();
        try {
            readWriteLock.writeLock().lock();
            final List<String> removedKeys = new ArrayList<>();
            final Set<Map.Entry<String, E>> entries = Internal_Holder.entrySet();
            for (final Map.Entry<String, E> entry : entries) {
                if (entry.getValue().isExpired()) {
                    itemsToRemove.add(entry.getValue());
                    removedKeys.add(entry.getKey());
                }
            }
            for (final String key : removedKeys) {
                E status = Internal_Holder.remove(key);
                internalDestory(status);
            }
        } catch (Throwable e) {
            logger.error("Fail to clean expired CacheExpire.", e);
        } finally {
            readWriteLock.writeLock().unlock();
        }
        logger.info("Clean expired CacheExpire: {}", itemsToRemove.size());
        return itemsToRemove;
    }

    private void internalDestory(E status) {
        if (status != null) {
            ICacheExpire iCacheExpire = status.getICacheExpire();
            status.destory();
            if (iCacheExpire != null) {
                iCacheExpire.destory();
            }
        }
    }
}
