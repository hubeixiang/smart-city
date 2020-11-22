package com.sct.application.authorization.support.session;

import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.util.SecurityServiceConstants;
import com.sct.commons.i18n.I18nMessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * session无效处理方法
 * session失效后
 */

@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {
    private static Logger logger = LoggerFactory.getLogger(CustomInvalidSessionStrategy.class);
    private MessageSourceAccessor messageSourceAccessor = null;
    @Autowired
    private ILoginProperties iLoginProperties;
    private String sessionInvalidPageUrl;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @PostConstruct
    public void init() {
        sessionInvalidPageUrl = iLoginProperties.getLoginform().getSessionInvalidPageUrl();
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 自定义session无效处理
        System.out.println("---------------CustomInvalidSessionStrategy");
//        response.setContentType("application/json;charset=UTF-8");
        String message = getMessage("CustomInvalidSessionStrategy.onExpiredSessionDetected");
        boolean isNormalLogout = false;
//        Object object = request.getSession(false).getAttribute("is_normal_logout");
//        if (object != null && object instanceof Boolean) {
//            isNormalLogout = (Boolean) object;
//        }
//        response.getWriter().append(message);
        String redirect_uri = null;
        redirect_uri = getRequestUri(request);
        System.err.println("onInvalidSessionDetected redirect_uri:" + redirect_uri);
        if (!isNormalLogout) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new AuthenticationServiceException(message));
        }
//        response.sendRedirect(location);
//        redirectStrategy.sendRedirect(request, response, redirect_uri);
//        response.getWriter().write("<script>window.parent.location.href = 'default';</script>");

        sendRedirectNew(sessionInvalidPageUrl, request, response);
    }

    private void sendRedirectNew(String defaultFailureUrl, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = defaultFailureUrl;
        String iframeUrl = SecurityServiceConstants.parserIframeUrl(request);
        if (StringUtils.isNotEmpty(iframeUrl)) {
            redirectUrl = String.format("%s?%s=%s", redirectUrl, SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID, iframeUrl);
        }
        logger.debug("onInvalidSessionDetected Redirecting to " + redirectUrl);
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    private String getRequestUri(HttpServletRequest request) {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("get")) {
            String uri = request.getRequestURI();
            Map<String, String[]> parameterMap = request.getParameterMap();
            StringBuffer sb = new StringBuffer();
            for (String key : parameterMap.keySet()) {
                String value = request.getParameter(key);
                if (StringUtils.isNotEmpty(value)) {
                    if (sb.length() == 0) {
                        sb.append(key).append("=").append(value);
                    } else {
                        sb.append("&").append(key).append("=").append(value);
                    }
                }
            }
            if (sb.length() != 0) {
                uri = uri + "?" + sb.toString();
            }
            return uri;
        } else {
            return sessionInvalidPageUrl;
        }
    }

    private String getMessage(String code) {
        return I18nMessageUtil.getInstance().getMessage(code);
    }


}
