package com.sct.service.users.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * SecurityUser 转换的相关util工具类
 */
public class SecurityUserUtil {
    public static String findAuthenticationUserId(Object object) {
        String userId = null;
        if (object == null) {
            userId = null;
        } else if (object instanceof Authentication) {
            Authentication authentication = (Authentication) object;
            Object principal = authentication.getPrincipal();
            userId = findPrincipalUserId(principal);
        } else if (object instanceof Principal) {
            userId = findPrincipalUserId(object);
        } else if (object instanceof User) {
            userId = ((User) object).getUsername();
        } else {
            userId = object.toString();
        }
        return userId;
    }

    public static String findPrincipalUserId(Object principal) {
        String userId;
        if (principal instanceof SecurityUser) {
            userId = ((SecurityUser) principal).getUserPkId();
        } else if (principal instanceof Principal) {
            userId = ((Principal) principal).getName();
        } else if (principal instanceof User) {
            userId = ((User) principal).getUsername();
        } else if (principal instanceof String) {
            userId = (String) principal;
        } else {
            userId = principal.toString();
        }
        return userId;
    }

    public static LocalDateTime findAuthenticationLoginTime(Object object) {
        LocalDateTime loginTime = null;
        if (object == null) {
            loginTime = null;
        } else if (object instanceof Authentication) {
            Authentication authentication = (Authentication) object;
            Object principal = authentication.getPrincipal();
            loginTime = findPrincipalLoginTime(principal);
        } else if (object instanceof Principal) {
            loginTime = findPrincipalLoginTime(object);
        }
        return loginTime;
    }

    public static LocalDateTime findPrincipalLoginTime(Object principal) {
        LocalDateTime loginTime = null;
        if (principal instanceof SecurityUser) {
            loginTime = ((SecurityUser) principal).getLoginTime();
        }
        return loginTime;
    }
}
