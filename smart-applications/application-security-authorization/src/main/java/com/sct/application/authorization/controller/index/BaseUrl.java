package com.sct.application.authorization.controller.index;

import org.springframework.util.StringUtils;

public class BaseUrl {
    private String httpPath;
    private String contextPath;

    public BaseUrl(String httpPath, String contextPath) {
        this.httpPath = httpPath;
        this.contextPath = contextPath;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getBaseUrl() {
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s", httpPath);
        } else {
            uri = String.format("%s/%s", httpPath, contextPath);
        }
        return uri;
    }

    public String getBaseUrl(String path) {
        return String.format("%s/%s", getBaseUrl(), path);
    }


    public String getBaseUrl(String uri, String key, String value) {
        return String.format("%s?%s=%s", uri, key, value);
    }
}
