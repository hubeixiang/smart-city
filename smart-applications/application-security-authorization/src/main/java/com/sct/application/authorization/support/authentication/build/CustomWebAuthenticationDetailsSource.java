package com.sct.application.authorization.support.authentication.build;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, CustomWebAuthenticationDetails> {

    public CustomWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new CustomWebAuthenticationDetails(context);
    }
}
