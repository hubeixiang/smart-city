package com.sct.application.security.runner;

import com.sct.service.main.ScDictServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCMDRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(ApplicationCMDRunner.class);

    @Autowired
    private ScDictServiceImpl scDictService;

    @Override
    public void run(String... args) throws Exception {
        try {
            scDictService.init();
        } catch (Throwable t) {
            logger.error(String.format("ApplicationCMDRunner run Throwable:%s", t.getMessage()), t);
        }
    }
}
