package com.sct.application.authorization.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public final class SecurityServiceConstants {
    public final static String IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID = "iframeSaveRequestLoginID";

    public static final String parserIframeUrl(HttpServletRequest request) {
        String redirect = request.getParameter(SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID);
        if (StringUtils.isNotEmpty(redirect) && request.getMethod().equalsIgnoreCase("GET")) {
            StringBuffer redirectUrl = new StringBuffer();
            redirectUrl.append(redirect);
            Set<String> keys = request.getParameterMap().keySet();
            for (String key : keys) {
                if (key.equalsIgnoreCase(SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID)) {
                    continue;
                }
                if (redirectUrl.length() == 0) {
                    redirectUrl.append(key).append("=").append(request.getParameter(key));
                } else {
                    redirectUrl.append("&").append(key).append("=").append(request.getParameter(key));
                }
            }
            redirect = redirectUrl.toString();
        }
        return redirect;
    }
}
