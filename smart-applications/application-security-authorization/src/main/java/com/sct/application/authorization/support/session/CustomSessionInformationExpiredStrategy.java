package com.sct.application.authorization.support.session;

import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.commons.i18n.I18nMessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 如果设置的session并发策略为一个账户第二次登陆会将第一次给踢下来
 * 则第一次登陆的用户再访问我们的项目时会进入到该类
 * event里封装了request、response信息
 */
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Autowired
    private ILoginProperties iLoginProperties;
    private String failurePage;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @PostConstruct
    public void init() {
        failurePage = iLoginProperties.getLoginform().getLoginErrorPageUrl();
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//        HttpServletResponse response = event.getResponse();
//        response.setContentType("application/json;charset=UTF-8");
        String message = getMessage("CustomSessionInformationExpiredStrategy.onExpiredSessionDetected");
//        response.getWriter().write(message);
        event.getRequest().getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new AuthenticationServiceException(message));
//        event.getResponse().sendRedirect(location);
        String redirect_uri = null;
        redirect_uri = getRequestUri(event.getRequest());
        redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), redirect_uri);
//        event.getResponse().getWriter().write("<script>window.parent.location.href = 'default';</script>");
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
            return failurePage;
        }
    }

    private String getMessage(String code) {
        return I18nMessageUtil.getInstance().getMessage(code);
    }
}
