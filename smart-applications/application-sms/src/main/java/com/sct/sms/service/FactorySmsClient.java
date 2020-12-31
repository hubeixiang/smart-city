package com.sct.sms.service;

import com.sct.sms.exception.SmsLoginException;
import com.sct.sms.properties.SmsSystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactorySmsClient implements FactoryBean<SmsClient> {
    private static Logger logger = LoggerFactory.getLogger(FactorySmsClient.class);
    private final Object lock = new Object();
    private SmsClient object = null;

    @Autowired
    private SmsSystemProperties smsSystemProperties;

    @Override
    public SmsClient getObject() throws SmsLoginException {
        if (object == null) {
            init();
        }
        return object;
    }

    @Override
    public Class<?> getObjectType() {
        return object == null ? SmsClient.class : object.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private void init() throws SmsLoginException {
        synchronized (lock) {
            if (object != null) {
                return;
            }
            try {
                SmsClient smsClient = new SmsClient(smsSystemProperties);
                smsClient.init();
                object = smsClient;
            } catch (Exception e) {
                throw new SmsLoginException(SmsClient.class.getSimpleName() + "#init exception", e, logger);
            }
        }
    }
}
