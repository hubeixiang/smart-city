package com.sct.application.authorization.controller;

import com.sct.application.authorization.controller.entity.UserLoginStatus;
import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.commons.utils.JsonUtils;
import com.sct.commons.web.core.response.HttpResultEntity;
import com.sct.service.core.web.controller.WebConstants;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import com.sct.service.users.security.SecurityUser;
import com.sct.service.users.security.SecurityUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户使用登录页面登录时查询登录用户信息相关
 */
@RestController
public class UserWebSecurityAuthorizationController extends BaseLoginController {
    private static Logger logger = LoggerFactory.getLogger(UserWebSecurityAuthorizationController.class);

    @Autowired
    private ILoginProperties iLoginProperties;

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 依据登录用户id查询
     */
    @Qualifier("authorityUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomSessionRegistry customSessionRegistry;

    private void logout(final HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @GetMapping(path = {"/" + Oauth2Constants.Oauth2_Context_Path + "/user/online/count"}, produces = WebConstants.WEB_PRODUCES)
    @ResponseBody
    public HttpEntity<?> online(final HttpServletRequest request,
                                final HttpServletResponse response) {
        String path = "/" + Oauth2Constants.Oauth2_Context_Path + "/user/online/count";
        try {
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
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            return new ResponseEntity<>(HttpResultEntity.of(HttpResultEntity.Code.FAILURE, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/" + Oauth2Constants.Oauth2_Context_Path + "/user/info"}, produces = WebConstants.WEB_PRODUCES)
    public HttpEntity<?> user(final HttpServletRequest request,
                              final HttpServletResponse response) {
        String path = "/" + Oauth2Constants.Oauth2_Context_Path + "/user/info";

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                logout(request);
                logger.error(String.format("%s Missing [%s]", path, "Authentication"));
                throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication"));
            }

            if (authentication.getPrincipal() == null) {
                logout(request);
                logger.error(String.format("%s Missing [%s]", path, "Authentication Principal"));
                throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication Principal"));
            }
            String userId = SecurityUserUtil.findAuthenticationUserId(authentication);
            LocalDateTime loginTime = SecurityUserUtil.findAuthenticationLoginTime(authentication);
            logger.debug("User userId is [{}]", userId);

            UserLoginStatus userLoginStatus = null;
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
                if (userDetails != null && userDetails instanceof SecurityUser) {
                    userLoginStatus = UserLoginStatus.of();
                    assembleUserLoginStatus(userLoginStatus, (SecurityUser) userDetails);
                    userLoginStatus.setLoginTime(loginTime == null ? null : loginTime.toString());
                }
            } catch (RuntimeException e) {
                logger.error(String.format("UserProfileAuthorizationController.getCurrentUser(%s) Exception:%s", authentication, e.getMessage()), e);
                throw new RuntimeException("Failed to retrieve user profile", e);
            }

            if (userLoginStatus == null) {
                logger.info(String.format("/oauth2/user Failed to retrieve user profile."));
                throw new RuntimeException("Failed to retrieve user profile.");
            }

            logger.debug("Final user profile is [{}]", JsonUtils.toJson(userLoginStatus));
            return new ResponseEntity<>(userLoginStatus, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            return new ResponseEntity<>(HttpResultEntity.of(HttpResultEntity.Code.FAILURE, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
