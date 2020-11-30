package com.sct.application.authorization.controller;

import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.commons.utils.JsonUtils;
import com.sct.service.core.web.controller.WebConstants;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import com.sct.service.users.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserProfileController extends BaseLoginController {
    private static Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private ILoginProperties iLoginProperties;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private CustomSessionRegistry customSessionRegistry;

    private void logout(final HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @GetMapping(path = {"/" + Oauth2Constants.Oauth2_Context_Path + "/user/online/count"}, produces = WebConstants.WEB_PRODUCES)
    @ResponseBody
    public Object online(final HttpServletRequest request,
                         final HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logout(request);
            throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication"));
        }

        if (authentication.getPrincipal() == null) {
            logout(request);
            throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication Principal"));
        }
        Map<String, Integer> map = new HashMap<>();
        int size = customSessionRegistry.online();
        map.put("count", size);
        return map;
    }

    @GetMapping(path = {"/" + Oauth2Constants.Oauth2_Context_Path + "/user/info"}, produces = WebConstants.WEB_PRODUCES)
    public Object user(final HttpServletRequest request,
                       final HttpServletResponse response) {
        logger.info(String.format("/oauth2/user"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logout(request);
            logger.info(String.format("/oauth2/user Missing [%s]", "Authentication"));
            throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication"));
        }

        if (authentication.getPrincipal() == null) {
            logout(request);
            logger.info(String.format("/oauth2/user Missing [%s]", "Authentication Principal"));
            throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication Principal"));
        }
        String userId = getUserId(authentication);
        LocalDateTime loginTime = getLoginTime(authentication);
        logger.debug("User userId is [{}]", userId);

        Object currentUser = null;
        try {
            currentUser = new SecurityUser(null, null, null, null, true, true, true, true, null);
        } catch (RuntimeException e) {
            logger.error(String.format("UserProfileAuthorizationController.getCurrentUser(%s) Exception:%s", authentication, e.getMessage()), e);
            throw new RuntimeException("Failed to retrieve user profile", e);
        }

        if (currentUser == null) {
            logger.info(String.format("/oauth2/user Failed to retrieve user profile."));
            throw new RuntimeException("Failed to retrieve user profile.");
        }

        logger.debug("Final user profile is [{}]", JsonUtils.toJson(currentUser));
        return currentUser;
    }

    protected String getUserId(Authentication authentication) {
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            if (auth2Authentication.getUserAuthentication() == null) {
                //表名是客户端登录,此时获取客户端id,解析客户端id得到用户id
                String clientId = auth2Authentication.getPrincipal().toString();
                String[] sss = clientId.split("_");
                if (sss.length == 1) {
                    return clientId;
                } else {
                    return sss[1];
                }
            }
        }
        Object principal = authentication.getPrincipal();
        String userId = null;
        if (principal instanceof SecurityUser) {
            userId = ((SecurityUser) principal).getUserPkId();
        } else if (principal instanceof org.springframework.security.core.userdetails.User) {
            userId = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        } else {
            userId = principal.toString();
        }
        return userId;
    }

    protected LocalDateTime getLoginTime(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        LocalDateTime loginTime = null;
        if (principal instanceof SecurityUser) {
            loginTime = ((SecurityUser) principal).getLoginTime();
        } else {
            loginTime = LocalDateTime.now();
        }
        return loginTime;
    }
}
