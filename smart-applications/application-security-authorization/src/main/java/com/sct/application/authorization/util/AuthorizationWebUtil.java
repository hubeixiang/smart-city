package com.sct.application.authorization.util;

import com.sct.service.users.security.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class AuthorizationWebUtil {
    public static String getPrincipalUser(HttpServletRequest request, Object object) {
        String userId = null;
        if (object != null) {
            if (object instanceof Authentication) {
                Authentication authentication = (Authentication) object;
                Object principal = authentication.getPrincipal();
                userId = getPrincipal(principal);
            } else if (object instanceof Principal) {
                userId = getPrincipal(object);
            } else if (object instanceof User) {
                userId = ((User) object).getUsername();
            } else {
                userId = object.toString();
            }
        } else {
            userId = null;
        }
        return userId;
    }

    private static String getPrincipal(Object principal) {
        String userId;
        if (principal instanceof SecurityUser) {
            userId = ((SecurityUser) principal).getUserId();
        } else if (principal instanceof Principal) {
            userId = ((Principal) principal).getName();
        } else if (principal instanceof User) {
            userId = ((User) principal).getUsername();
        } else {
            userId = principal.toString();
        }
        return userId;
    }
}
