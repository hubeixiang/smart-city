package com.sct.application.authorization.support.username.provider;

import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义的身份认证,主要添加前端页面加密后的密码进行解密
 */
public class CustomDaoAuthenticationProvider extends HiosAbstractUserDetailsAuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private UserDetailsChecker successUserDetailsChecker;
    private UserDetailsChecker errorUserDetailsChecker;

    public CustomDaoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserDetailsChecker successUserDetailsChecker, UserDetailsChecker errorUserDetailsChecker) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.successUserDetailsChecker = successUserDetailsChecker;
        this.errorUserDetailsChecker = errorUserDetailsChecker;
    }

    @Override
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Override
    protected UserDetailsChecker getHiosPasswordSuccessUserDetailsChecker() {
        return successUserDetailsChecker;
    }

    @Override
    protected UserDetailsChecker getHiosPasswordErrorUserDetailsChecker() {
        return errorUserDetailsChecker;
    }
}
