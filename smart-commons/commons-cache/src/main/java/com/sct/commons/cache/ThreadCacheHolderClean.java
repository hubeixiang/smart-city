package com.sct.commons.cache;

import com.sct.commons.cache.impl.DelegateStatusHolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 每种缓存需要定时清理缓存过期数据的holder,都使用此线程进行定期清理过期数据
 */
public class ThreadCacheHolderClean implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ThreadCacheHolderClean.class);
    private final DelegateStatusHolderService delegateStatusHolderService = new DelegateStatusHolderService();
    private final String threadName;

    public ThreadCacheHolderClean(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5 * 60 * 1000);
                if (delegateStatusHolderService != null) {
                    delegateStatusHolderService.clean();
                }
            } catch (InterruptedException e) {
                logger.debug(String.format("threadName = %s clean cache holder Exception:%s", threadName, e.getMessage()));
                break;
            } finally {
                logger.debug(String.format("threadName = %s clean cache holder over", threadName));
            }
        }
    }

    public String getThreadName() {
        return threadName;
    }

    public DelegateStatusHolderService getDelegateStatusHolderService() {
        return delegateStatusHolderService;
    }
}
