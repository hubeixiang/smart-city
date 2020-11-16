package com.sct.commons.i18n.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class I18nBeanPostProcessor implements BeanPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(I18nBeanPostProcessor.class);

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof I18nMessageAutoConfigure) {
            ((I18nMessageAutoConfigure) bean).init();
        }
        return bean;
    }
}
