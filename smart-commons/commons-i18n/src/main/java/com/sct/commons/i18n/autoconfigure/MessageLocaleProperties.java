package com.sct.commons.i18n.autoconfigure;

import com.sct.commons.constants.ConstantCommonAutoConfigure;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = ConstantCommonAutoConfigure.I18N.I18N_PROPERTIES)
@Component
public class MessageLocaleProperties {
    private String language;
    private String country;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
