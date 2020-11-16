package com.sct.commons.i18n;

import com.sct.commons.i18n.message.I18nMessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 对外提供的资源访问工具类
 */
public class I18nMessageUtil {
    private static I18nMessageUtil instance;
    private I18nMessageSource i18nMessageSource = null;

    private I18nMessageUtil() {
    }

    public static I18nMessageUtil getInstance() {
        if (instance != null) {
            return instance;
        }
        return createInstance();
    }

    private static synchronized I18nMessageUtil createInstance() {
        instance = new I18nMessageUtil();
        return instance;
    }

    public void initI18nMessageSource(I18nMessageSource i18nMessageSource) {
        this.i18nMessageSource = i18nMessageSource;
    }

    public String getRequestMessage(String key, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            return this.i18nMessageSource.getMessage(key, params);
        } else {
            return this.i18nMessageSource.getMessage(key, params, locale);
        }
    }

    public String getMessage(String key, Object... params) {
        return this.i18nMessageSource.getMessage(key, params);
    }

    public String getMessage(String key, Locale locale, Object... params) {
        return this.i18nMessageSource.getMessage(key, params, locale);
    }

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return this.i18nMessageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return this.i18nMessageSource.getMessage(code, args, locale);
    }

    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return this.i18nMessageSource.getMessage(resolvable, locale);
    }

    public String getExampleMessage() {
        return this.i18nMessageSource.getMessage(ExampleConstants.TIPS_EXAMPLE_TIP);
    }

    public String getExampleMessage(Locale locale) {
        return this.i18nMessageSource.getMessage(ExampleConstants.TIPS_EXAMPLE_TIP, locale);
    }
}
