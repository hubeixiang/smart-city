package com.sct.application.authorization.support.session;

import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.properties.SessionManagerConfig;
import com.sct.service.users.security.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的登录用户session控制策略
 */
@Service
public class CustomSessionRegistry {
    private static Logger logger = LoggerFactory.getLogger(CustomSessionRegistry.class);
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private ILoginProperties iLoginProperties;

    private SessionManagerConfig sessionManagerConfig;

    /**
     * 是执行session并发控制,或者是session超时策略
     * true: 执行session并发控制
     * false: 执行session超时策略
     */
    private boolean isSessionRegistry = false;
    private int maximumSessions = 0;

    @PostConstruct
    public void init() {
        sessionManagerConfig = iLoginProperties.getSessionManagerConfig();
        if (sessionManagerConfig != null && sessionManagerConfig.getMaxmumSessions() > 0) {
            isSessionRegistry = true;
            maximumSessions = sessionManagerConfig.getMaxmumSessions();
        } else {
            isSessionRegistry = false;
        }
    }

    public SessionManagerConfig getSessionManagerConfig() {
        return sessionManagerConfig;
    }

    public boolean isSessionRegistry() {
        return isSessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    /**
     * 当前用户数
     *
     * @return
     */
    public int online() {
        if (maximumSessions == 1) {
            List<Object> users = sessionRegistry.getAllPrincipals();
            return users.size();
        } else {
            return staticsSessions();
        }
    }

    public Object onlineUsersDetails() {
        //退出成功后删除当前用户session
        Map<String, List<CustomSessionInfo>> detail = new HashMap<>();
        List<Object> o = sessionRegistry.getAllPrincipals();
        for (Object principal : o) {
            if (principal instanceof User) {
                final User loggedUser = (User) principal;
                String key = null;
                if (loggedUser instanceof SecurityUser) {
                    SecurityUser securityUser = (SecurityUser) loggedUser;
                    key = String.format("userId=%s,userName=%s", securityUser.getUserPkId(), securityUser.getSecurityUserName());
                } else {
                    key = String.format("userName=%s", loggedUser.getUsername());
                }
                List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                if (null != sessionsInfo && sessionsInfo.size() > 0) {
                    for (SessionInformation sessionInformation : sessionsInfo) {
                        if (!sessionInformation.isExpired()) {
                            //未过期的信息
                            if (detail.get(key) != null) {
                                CustomSessionInfo customSessionInfo = new CustomSessionInfo();
                                customSessionInfo.setExpired(sessionInformation.isExpired());
                                String dateString = sessionInformation.getLastRequest() == null ? "" : DateFormatUtils.format(sessionInformation.getLastRequest(), "yyyy-MM-dd HH:mm:ss");
                                customSessionInfo.setLastRequest(dateString);
                                customSessionInfo.setSessionId(sessionInformation.getSessionId());
                                detail.get(key).add(customSessionInfo);
                            } else {
                                CustomSessionInfo customSessionInfo = new CustomSessionInfo();
                                customSessionInfo.setExpired(sessionInformation.isExpired());
                                String dateString = sessionInformation.getLastRequest() == null ? "" : DateFormatUtils.format(sessionInformation.getLastRequest(), "yyyy-MM-dd HH:mm:ss");
                                customSessionInfo.setLastRequest(dateString);
                                customSessionInfo.setSessionId(sessionInformation.getSessionId());
                                List<CustomSessionInfo> sessions = new ArrayList<>();
                                detail.put(key, sessions);
                                detail.get(key).add(customSessionInfo);
                            }
                        }
                    }
                }
            }
        }
        return detail;
    }

    /**
     * 登录成功后,除了session外的处理
     *
     * @param request
     * @param response
     * @param authentication
     */
    public void loginSuccessSessionRegistry(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) {
    }

    /**
     * 退出时正常,除了session外的处理
     *
     * @param authentication
     */
    public void logoutSessionRegistry(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) {
        //退出成功后删除当前用户session,sessionId优先从Authentication中保存的sessionId获取
        HttpSession httpSession = request.getSession(false);
        String sessionId = httpSession != null ? httpSession.getId() : null;
        if (authentication != null && authentication.getDetails() != null && authentication.getDetails() instanceof WebAuthenticationDetails) {
            sessionId = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
        }
        if (authentication == null || authentication.getPrincipal() == null) {
            logger.debug("CustomSessionRegistry.logoutSessionRegistry(sessionId=%s) Authentication is null", sessionId);
            if (StringUtils.isEmpty(sessionId)) {
                return;
            }
            List<Object> o = sessionRegistry.getAllPrincipals();
            for (Object principal : o) {
                if (principal instanceof User) {
                    delete(principal, sessionId);
                }
            }
        } else {
            List<Object> o = sessionRegistry.getAllPrincipals();
            Object authPrincipal = authentication.getPrincipal();
            boolean isCustomUser = false;
            SecurityUser authSecurityUser = null;
            if (authPrincipal instanceof SecurityUser) {
                isCustomUser = true;
                authSecurityUser = (SecurityUser) authPrincipal;
            }
            for (Object principal : o) {
                if (principal instanceof User) {
                    final User loggedUser = (User) principal;
                    if (loggedUser instanceof SecurityUser && isCustomUser) {
                        SecurityUser customUser = (SecurityUser) loggedUser;
                        if (authSecurityUser.getUserPkId().equals(customUser.getUserPkId())) {
                            delete(principal, sessionId);
                        }
                    } else {
                        if (authentication.getName().equals(loggedUser.getUsername())) {
                            delete(principal, sessionId);
                        }
                    }
                }
            }
        }
    }

    /**
     * 统计有效session数
     *
     * @return
     */
    private int staticsSessions() {
        int sessions = 0;
        List<Object> o = sessionRegistry.getAllPrincipals();
        for (Object principal : o) {
            List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, true);
            if (sessionsInfo != null && sessionsInfo.size() > 0) {
                for (SessionInformation sessionInformation : sessionsInfo) {
                    if (!sessionInformation.isExpired()) {
                        sessions++;
                    }
                }
            }
        }
        return sessions;
    }

    private void delete(Object principal, String sessionId) {
        List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, true);
        if (null != sessionsInfo && sessionsInfo.size() > 0) {
            if (maximumSessions == 1) {
                for (SessionInformation sessionInformation : sessionsInfo) {
                    sessionInformation.expireNow();
                    sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                }
            } else {
                for (SessionInformation sessionInformation : sessionsInfo) {
                    if (StringUtils.isNotEmpty(sessionId) && sessionInformation.getSessionId().equals(sessionId)) {
                        sessionInformation.expireNow();
                        sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                    } else if (sessionInformation.isExpired()) {
                        sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                    }
                }
            }
        }
    }
}
