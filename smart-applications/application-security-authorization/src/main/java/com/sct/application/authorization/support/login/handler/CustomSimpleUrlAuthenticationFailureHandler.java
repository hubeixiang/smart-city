package com.sct.application.authorization.support.login.handler;

import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.util.SecurityServiceConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录失败处理
 */
@Component
public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static Logger logger = LoggerFactory.getLogger(CustomSimpleUrlAuthenticationFailureHandler.class);
    private String defaultFailureUrl;
    @Autowired
    private ILoginProperties iLoginProperties;
//    @Autowired
//    private HiosUserSsoLogService hiosUserSsoLogService;

    public CustomSimpleUrlAuthenticationFailureHandler() {
    }

    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        super.setDefaultFailureUrl(defaultFailureUrl);
        this.defaultFailureUrl = defaultFailureUrl;
    }

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        insertLoginLog(request, authentication, exception);
//        super.onAuthenticationFailure(request,response,exception);
        if (defaultFailureUrl == null) {
            logger.debug("No failure URL set, sending 401 Unauthorized error");

            response.sendError(HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase());
        } else {
            saveException(request, exception);

            if (isUseForward()) {
                logger.debug("Forwarding to " + defaultFailureUrl);

                request.getRequestDispatcher(defaultFailureUrl)
                        .forward(request, response);
            } else {
                String redirectUrl = defaultFailureUrl;
                String iframeUrl = SecurityServiceConstants.parserIframeUrl(request);
                if (StringUtils.isNotEmpty(iframeUrl)) {
                    redirectUrl = String.format("%s?%s=%s", redirectUrl, SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID, iframeUrl);
                }
                logger.debug("Redirecting to " + redirectUrl);
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            }
        }
    }

    private void insertLoginLog(HttpServletRequest request, Authentication authentication, AuthenticationException exception) {
//        SecUserSsoLog secUserSsoLog = new SecUserSsoLog();
//        String userId = WebUtil.getPrincipalUser(request, authentication);
//        if (StringUtils.isEmpty(userId)) {
//            return;
//        }
//        secUserSsoLog.setUserId(userId);
//        secUserSsoLog.setRequestIp(WebUtil.getIpAddr(request));
//        secUserSsoLog.setSsoLogType(SsoLogType.LOGIN.getId());
//        secUserSsoLog.setSsoServiceId(null);
//        secUserSsoLog.setOperationStatus(SsoLogOperationStatus.FAILED.getId());
//        String message = exception.getMessage();
//        if (message.length() > 200) {
//            message = message.substring(0, 200);
//        }
//        secUserSsoLog.setRequestUserAgent(message);
//        hiosUserSsoLogService.insertSecUserSsoLog(secUserSsoLog);
    }
}
