package com.sct.commons.cache;

import com.sct.commons.cache.api.IStatusHolderService;

public class ThreadCacheHolderCleanManager {
    private static ThreadCacheHolderCleanManager instance;
    private static ThreadCacheHolderClean threadCacheHolderClean;

    private ThreadCacheHolderCleanManager() {
        threadCacheHolderClean = new ThreadCacheHolderClean("ThreadCacheHolderClean");
        Thread thread = new Thread(threadCacheHolderClean, threadCacheHolderClean.getThreadName());
        thread.start();
    }

    public static ThreadCacheHolderCleanManager getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ThreadCacheHolderCleanManager();
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
