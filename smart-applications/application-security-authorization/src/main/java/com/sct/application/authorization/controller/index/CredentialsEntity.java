package com.sct.application.authorization.controller.index;

import org.thymeleaf.util.StringUtils;

public class CredentialsEntity {
    private String clientIdKey = "client_id";
    private String clientSecretKey = "client_secret";
    private String scopeKey = "scope";
    private String grantTypeKey = "grant_type";
    private String redirectUriKey = "redirect_uri";
    private String responseTypeKey = "response_type";
    private String clientId = null;
    private String clientSecret = "secret";
    private String scope = "";

    public String getParamClient() {
        return String.format("%s&%s", getParamClientId(), getParamClientSecret());
    }

    public String getParamClientId() {
        return String.format("%s=%s", clientIdKey, clientId);
    }

    public String getParamClientSecret() {
        return String.format("%s=%s", clientSecretKey, clientSecret);
    }

    public String getParamScope() {
        return String.format("%s=%s", scopeKey, scope);
    }

    public String getParamGrantType(String type) {
        return String.format("%s=%s", grantTypeKey, type);
    }

    public String getParamRedirectUri(String redirect) {
        return String.format("%s=%s", redirectUriKey, redirect);
    }

    public String getParamResponseType(String responseType) {
        return String.format("%s=%s", responseTypeKey, responseType);
    }

    public String getParamVar(String key, String value) {
        return String.format("%s=%s", key, value);
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScope(String scopeValue) {
        if (StringUtils.isEmpty(scopeValue)) {
            this.scope = "";
        }
        String[] scopes = scopeValue.split(",");
        StringBuffer sb = new StringBuffer();
        for (String v : scopes) {
            if (v == null || v.trim().length() == 0) {
                continue;
            }
            if (sb.length() == 0) {
                sb.append(v.trim());
            } else {
                sb.append(" ").append(v.trim());
            }

        }
        this.scope = sb.toString();
    }
}
