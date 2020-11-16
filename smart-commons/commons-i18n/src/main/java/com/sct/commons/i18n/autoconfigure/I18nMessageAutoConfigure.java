package com.sct.commons.i18n.autoconfigure;

import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.commons.i18n.message.I18nMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Locale;

@Component
public class I18nMessageAutoConfigure {
    private static Logger logger = LoggerFactory.getLogger(I18nMessageAutoConfigure.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    MessageLocaleProperties messageLocaleProperties;


    public void init() {
        Locale locale = defaultLocale(messageLocaleProperties);
        I18nMessageSource i18nMessageSource = i18nMessageSource(messageSource, locale);
    }

    private Locale defaultLocale(MessageLocaleProperties messageLocaleProperties) {
        if (messageLocaleProperties == null || StringUtils.isEmpty(messageLocaleProperties.getLanguage())) {
            return Locale.getDefault();
        } else {
            return new Locale(messageLocaleProperties.getLanguage(), messageLocaleProperties.getCountry() == null ? "" : messageLocaleProperties.getCountry());
        }
    }

    private I18nMessageSource i18nMessageSource(MessageSource messageSource, Locale defaultLocale) {
        if (messageSource == null) {
            logger.error("Please init i18n MessageSource bean");
        }
        I18nMessageSource i18nMessageSource = new I18nMessageSource();
        i18nMessageSource.setMessageSource(messageSource);
        i18nMessageSource.setDefaultLocale(defaultLocale);

        I18nMessageUtil.getInstance().initI18nMessageSource(i18nMessageSource);
        return i18nMessageSource;
    }
}
