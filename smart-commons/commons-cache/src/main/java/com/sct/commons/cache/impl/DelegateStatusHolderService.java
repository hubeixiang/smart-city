package com.sct.commons.cache.impl;

import com.sct.commons.cache.api.IStatusHolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DelegateStatusHolderService {
    private static Logger logger = LoggerFactory.getLogger(DelegateStatusHolderService.class);
    private final List<IStatusHolderService> statusHolderServices = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void add(IStatusHolderService iStatusHolderService) {
        try {
            readWriteLock.writeLock().lock();
            if (!statusHolderServices.contains(iStatusHolderService)) {
                statusHolderServices.add(iStatusHolderService);
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(IStatusHolderService iStatusHolderService) {
        try {
            readWriteLock.writeLock().lock();
            statusHolderServices.remove(iStatusHolderService);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clean() {
        try {
            readWriteLock.readLock().lock();
            for (IStatusHolderService iStatusHolderService : statusHolderServices) {
                try {
                    iStatusHolderService.clean();
                } catch (Throwable t) {
                    logger.error(String.format("IStatusHolderService=%s clean expire data throwable", iStatusHolderService.holderName()), t);
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


}
