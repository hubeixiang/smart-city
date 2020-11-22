package com.sct.application.authorization.support.entrypoint;


import com.sct.application.authorization.properties.Loginform;
import com.sct.application.authorization.util.SecurityServiceConstants;
import com.sct.commons.utils.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthenticationEntryPoint 默认实现是 LoginUrlAuthenticationEntryPoint, 该类的处理是转发或重定向到登录页面
 * 我们自定义的不同地方在于转发或者重定向时将要转发或者重新的源地址附带到登陆地址后面
 */
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private static Logger logger = LoggerFactory.getLogger(CustomLoginUrlAuthenticationEntryPoint.class);
    private RequestCache requestCache = new HttpSessionRequestCache();
    private Loginform loginformProperties = null;

    /**
     * @param loginformProperties URL where the login page can be found. Should either be
     *                            relative to the web-app context path (include a leading {@code /}) or an absolute
     *                            URL.
     */
    public CustomLoginUrlAuthenticationEntryPoint(Loginform loginformProperties) {
        super(loginformProperties.getLoginPageUrl());
        this.loginformProperties = loginformProperties;
    }

    protected String determineUrlToUseForThisRequest(HttpServletRequest request,
                                                     HttpServletResponse response, AuthenticationException exception) {

        String defaultLoginFormUrl = getLoginFormUrl();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String baseUrl = WebUtil.getBasePath(request);
        String requestUri = request.getRequestURI();
        String requestUrl = request.getRequestURL().toString();
        String savedRequestRedi = savedRequest == null ? "" : savedRequest.getRedirectUrl();
        logger.info(String.format("determineUrlToUseForThisRequest(%s,%s,%s,defaultLoginFormUrl=%s,%s)", baseUrl, requestUri, requestUrl, defaultLoginFormUrl, savedRequestRedi));
        if (savedRequest == null) {
            logger.info(String.format("determineUrlToUseForThisRequest(forceHttps=%s,useForward=%s,savedRequest is null)", isForceHttps(), isUseForward()));
            return defaultLoginFormUrl;
        }
        logger.info(String.format("determineUrlToUseForThisRequest(forceHttps=%s,useForward=%s,savedRequest is not null)", isForceHttps(), isUseForward()));
        String redirectUrl = savedRequest.getRedirectUrl();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(redirectUrl)) {
            String xdefaultLoginFormUrl = String.format("%s?%s=%s", defaultLoginFormUrl, SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID, redirectUrl);
            logger.info("determineUrlToUseForThisRequest:" + xdefaultLoginFormUrl);
            defaultLoginFormUrl = xdefaultLoginFormUrl;
        }
        return defaultLoginFormUrl;
    }
}
