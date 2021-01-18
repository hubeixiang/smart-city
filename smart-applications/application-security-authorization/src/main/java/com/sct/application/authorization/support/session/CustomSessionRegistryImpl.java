package com.sct.application.authorization.support.session;
/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @see org.springframework.security.core.session.SessionRegistryImpl
 *
 * 修改了监听的事件,新增了SessionFixationProtectionEvent事件
 */
public class CustomSessionRegistryImpl implements SessionRegistry,
        ApplicationListener<ApplicationEvent> {

    // ~ Instance fields
    // ================================================================================================

    protected final Log logger = LogFactory.getLog(org.springframework.security.core.session.SessionRegistryImpl.class);

    /**
     * <principal:Object,SessionIdSet>
     */
    private final ConcurrentMap<Object, Set<String>> principals;
    /**
     * <sessionId:Object,SessionInformation>
     */
    private final Map<String, SessionInformation> sessionIds;

    // ~ Methods
    // ========================================================================================================

    public CustomSessionRegistryImpl() {
        this.principals = new ConcurrentHashMap<>();
        this.sessionIds = new ConcurrentHashMap<>();
    }

    public CustomSessionRegistryImpl(ConcurrentMap<Object, Set<String>> principals, Map<String, SessionInformation> sessionIds) {
        this.principals = principals;
        this.sessionIds = sessionIds;
    }

    public List<Object> getAllPrincipals() {
        return new ArrayList<>(principals.keySet());
    }

    public List<SessionInformation> getAllSessions(Object principal,
                                                   boolean includeExpiredSessions) {
        final Set<String> sessionsUsedByPrincipal = principals.get(principal);

        if (sessionsUsedByPrincipal == null) {
            return Collections.emptyList();
        }

        List<SessionInformation> list = new ArrayList<>(
                sessionsUsedByPrincipal.size());

        for (String sessionId : sessionsUsedByPrincipal) {
            SessionInformation sessionInformation = getSessionInformation(sessionId);

            if (sessionInformation == null) {
                continue;
            }

            if (includeExpiredSessions || !sessionInformation.isExpired()) {
                list.add(sessionInformation);
            }
        }

        return list;
    }

    public SessionInformation getSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");

        return sessionIds.get(sessionId);
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof SessionDestroyedEvent) {
            System.out.println("onApplicationEvent---->SessionDestroyedEvent");
            String sessionId = ((SessionDestroyedEvent) event).getId();
            removeSessionInformation(sessionId);
        } else if (event instanceof SessionFixationProtectionEvent) {
            SessionFixationProtectionEvent protectionEvent = (SessionFixationProtectionEvent) event;
            String oldSessionId = protectionEvent.getOldSessionId();
            String newSessionId = protectionEvent.getNewSessionId();
            SessionInformation sessionInformation = getSessionInformation(oldSessionId);
            if (sessionInformation != null) {
                //有旧的session,表明当前的session经过修改
                Authentication authentication = protectionEvent.getAuthentication();
                String info = String.format("ok-----onApplicationEvent---->SessionFixationProtectionEvent(old:%s,new:%s,hasOldSession(%s),%s)", oldSessionId, newSessionId, sessionInformation, authentication);
                System.out.println(info);
                logger.info(info);
                SessionInformation newSessionInformation = copySessionInformation(sessionInformation, newSessionId);
                registerNewSession(newSessionInformation);
                removeSessionInformation(oldSessionId);
            } else {
                String info = String.format("none---onApplicationEvent====>SessionFixationProtectionEvent(old:%s,new:%s,hasOldSession(%s))", oldSessionId, newSessionId, sessionInformation);
                System.out.println(info);
                logger.warn(info);
            }
        }
    }

    private SessionInformation copySessionInformation(SessionInformation sessionInformation, String newSessionId) {
        SessionInformation newSessionInformation = new SessionInformation(sessionInformation.getPrincipal(), newSessionId, new Date());
        return newSessionInformation;
    }

    public void refreshLastRequest(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");

        SessionInformation info = getSessionInformation(sessionId);

        if (info != null) {
            info.refreshLastRequest();
        }
    }

    public void registerNewSession(String sessionId, Object principal) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        Assert.notNull(principal, "Principal required as per interface contract");
        SessionInformation sessionInformation = new SessionInformation(principal, sessionId, new Date());

        registerNewSession(sessionInformation);
    }

    private void registerNewSession(SessionInformation sessionInformation) {
        Assert.notNull(sessionInformation, "SessionInformation required as per interface contract");
        String sessionId = sessionInformation.getSessionId();
        Object principal = sessionInformation.getPrincipal();

        if (getSessionInformation(sessionId) != null) {
            removeSessionInformation(sessionId);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Registering session " + sessionId + ", for principal "
                    + principal);
        }

        sessionIds.put(sessionId, sessionInformation);

        principals.compute(principal, (key, sessionsUsedByPrincipal) -> {
            if (sessionsUsedByPrincipal == null) {
                sessionsUsedByPrincipal = new CopyOnWriteArraySet<>();
            }
            sessionsUsedByPrincipal.add(sessionId);

            if (logger.isTraceEnabled()) {
                logger.trace("Sessions used by '" + principal + "' : "
                        + sessionsUsedByPrincipal);
            }
            return sessionsUsedByPrincipal;
        });
    }

    public void removeSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");

        SessionInformation info = getSessionInformation(sessionId);

        if (info == null) {
            return;
        }

        if (logger.isTraceEnabled()) {
            logger.debug("Removing session " + sessionId
                    + " from set of registered sessions");
        }

        sessionIds.remove(sessionId);

        principals.computeIfPresent(info.getPrincipal(), (key, sessionsUsedByPrincipal) -> {
            if (logger.isDebugEnabled()) {
                logger.debug("Removing session " + sessionId
                        + " from principal's set of registered sessions");
            }

            sessionsUsedByPrincipal.remove(sessionId);

            if (sessionsUsedByPrincipal.isEmpty()) {
                // No need to keep object in principals Map anymore
                if (logger.isDebugEnabled()) {
                    logger.debug("Removing principal " + info.getPrincipal()
                            + " from registry");
                }
                sessionsUsedByPrincipal = null;
            }

            if (logger.isTraceEnabled()) {
                logger.trace("Sessions used by '" + info.getPrincipal() + "' : "
                        + sessionsUsedByPrincipal);
            }
            return sessionsUsedByPrincipal;
        });
    }

}
