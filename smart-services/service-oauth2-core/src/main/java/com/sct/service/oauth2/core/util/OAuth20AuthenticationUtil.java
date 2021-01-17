package com.sct.service.oauth2.core.util;

import com.sct.service.users.security.SecurityUserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public final class OAuth20AuthenticationUtil {
    public final static String findOAuth20ClientId(Object object) {
        String id = null;
        if (object != null && object instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) object;
            OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
            if (oAuth2Request != null) {
                id = oAuth2Request.getClientId();
            }
        }
        return id;
    }

    public final static String findOAuth20UserId(Object object) {
        String id = null;
        if (object != null && object instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) object;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    id = SecurityUserUtil.findPrincipalUserId(principal);
                }
            }
        }
        return id;
    }

    public final static String findOAuth20Token(Object object) {
        String id = null;
        if (object != null && object instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) object;
            Object details = oAuth2Authentication.getDetails();
            if (details != null && details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oAuth20Details = (OAuth2AuthenticationDetails) details;
                id = oAuth20Details.getTokenValue();
            }
        }
        return id;
    }

    public final static String findOAuth20TokenCreateTime(Object object) {
        String id = null;
        if (object != null && object instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) object;
            Object details = oAuth2Authentication.getDetails();
            if (details != null && details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oAuth20Details = (OAuth2AuthenticationDetails) details;
                id = oAuth20Details.getTokenValue();
            }
        }
        return id;
    }
}
