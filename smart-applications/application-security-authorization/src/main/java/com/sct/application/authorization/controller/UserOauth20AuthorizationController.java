package com.sct.application.authorization.controller;

import com.sct.application.authorization.controller.entity.UserLoginStatus;
import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.commons.utils.JsonUtils;
import com.sct.commons.web.core.response.HttpResultEntity;
import com.sct.service.core.web.controller.WebConstants;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import com.sct.service.oauth2.core.util.OAuth20AuthenticationUtil;
import com.sct.service.users.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户使用oauth2.0登录认证后,使用token查询登录用户信息
 */
@RestController
public class UserOauth20AuthorizationController extends BaseLoginController {
    private static Logger logger = LoggerFactory.getLogger(UserOauth20AuthorizationController.class);

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

    @GetMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/user/online/count"}, produces = WebConstants.WEB_PRODUCES)
    @ResponseBody
    public HttpEntity<?> online(final HttpServletRequest request,
                                final HttpServletResponse response, Principal principal) {
        String path = "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/user/online/count";
        try {
            if (principal == null) {
                logout(request);
                throw new AuthenticationServiceException(String.format("%s Missing [%s]", path, "Authentication Principal"));
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

    @GetMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/user/info"}, produces = WebConstants.WEB_PRODUCES)
    public HttpEntity<?> user(final HttpServletRequest request,
                              final HttpServletResponse response, Principal principal) {
        String path = "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/user/info";
        logger.info(String.format(path));
        try {
            if (principal == null) {
                logout(request);
                logger.info(String.format("%s Missing [%s]", path, "Authentication"));
                throw new AuthenticationServiceException(String.format("Missing [%s]", "Authentication"));
            }
            String userId = OAuth20AuthenticationUtil.findOAuth20UserId(principal);
            String loginTime = OAuth20AuthenticationUtil.findOAuth20TokenCreateTime(principal);
            logger.debug("User userId is [{}]", userId);

            UserLoginStatus userLoginStatus = null;
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
                if (userDetails != null && userDetails instanceof SecurityUser) {
                    userLoginStatus = UserLoginStatus.of();
                    assembleUserLoginStatus(userLoginStatus, (SecurityUser) userDetails);
                    userLoginStatus.setLoginTime(loginTime);
                }
            } catch (RuntimeException e) {
                logger.error(String.format("UserProfileAuthorizationController.getCurrentUser(%s) Exception:%s", principal, e.getMessage()), e);
                throw new RuntimeException("Failed to retrieve user profile", e);
            }

            if (userLoginStatus == null) {
                logger.info(String.format("%s Failed to retrieve user profile.", path));
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

    protected LocalDateTime getLoginTime(Object object) {
        return LocalDateTime.now();
    }
}
