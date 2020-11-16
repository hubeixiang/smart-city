package com.sct.commons.i18n.resolver;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

public class MvcLocaleResolver implements LocaleResolver {
    public final static String LocalResolverParam = "lang";

    @Override
    public Locale resolveLocale(javax.servlet.http.HttpServletRequest request) {
        String l = request.getParameter("lang");
        if (!StringUtils.isEmpty((l))) {
            String[] s = l.split("_");
            return new Locale(s[0], s[1]);
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Locale locale) {

    }
}
