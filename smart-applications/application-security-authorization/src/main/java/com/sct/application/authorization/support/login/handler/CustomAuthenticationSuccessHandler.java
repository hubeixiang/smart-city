package com.sct.application.authorization.support.login.handler;

import com.sct.application.authorization.support.audit.AuthenticationSuccessAudit;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录成功后处理
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private AuthenticationSuccessAudit authenticationSuccessAudit;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {

        authenticationSuccessAudit.successAudit(request,response,authentication);

        //处理完成后，跳转回原请求URL
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public void setAuthenticationSuccessAudit(AuthenticationSuccessAudit authenticationSuccessAudit) {
        this.authenticationSuccessAudit = authenticationSuccessAudit;
    }
}
