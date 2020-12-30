package com.sct.webtools.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lupeng 2019-10-29
 */
@Component
public class ExecutorServiceContext {
    private static Logger logger = LoggerFactory.getLogger(ExecutorServiceContext.class);

    private ExecutorService executorService = null;

    @Value("${batch.task.timeout:-1}")
    private long batchTaskTimeout;

    @PostConstruct
    public void init() {
        logger.info("batch.task.timeout={}", batchTaskTimeout);
    }

    @Bean
    //@Qualifier(value = "")
    public ExecutorService createExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        logger.info("create bean [ExecutorService]");
        return executorService;
    }

}
