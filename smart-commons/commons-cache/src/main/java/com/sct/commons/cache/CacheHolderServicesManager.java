package com.sct.commons.cache;

import com.sct.commons.cache.api.IStatusHolderService;

public final class CacheHolderServicesManager {
    private static CacheHolderServicesManager instance;
    private static ThreadCacheHolderClean threadCacheHolderClean;

    private CacheHolderServicesManager() {
        threadCacheHolderClean = new ThreadCacheHolderClean("ThreadCacheHolderClean");
        Thread thread = new Thread(threadCacheHolderClean, threadCacheHolderClean.getThreadName());
        thread.start();
    }

    public static CacheHolderServicesManager getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CacheHolderServicesManager();
        }
    }

    public void add(IStatusHolderService iStatusHolderService) {
        threadCacheHolderClean.getDelegateStatusHolderService().add(iStatusHolderService);
    }

    public void remove(IStatusHolderService iStatusHolderService) {
        threadCacheHolderClean.getDelegateStatusHolderService().remove(iStatusHolderService);
    }

    public void clean() {
        threadCacheHolderClean.getDelegateStatusHolderService().clean();
    }
}
