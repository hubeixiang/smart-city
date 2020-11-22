package com.sct.application.authorization.support.providers;

import com.sct.application.authorization.support.username.provider.CustomDaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 默认用户名密码登录
 */
public class CustomDaoAuthenticationProviderConfigurer implements AuthenticationProviderDefine {
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private UserDetailsChecker successUserDetailsChecker;
    private UserDetailsChecker errorUserDetailsChecker;

    public CustomDaoAuthenticationProviderConfigurer(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserDetailsChecker successUserDetailsChecker, UserDetailsChecker errorUserDetailsChecker) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.successUserDetailsChecker = successUserDetailsChecker;
        this.errorUserDetailsChecker = errorUserDetailsChecker;
    }

    @Override
    public Class<?> supportsAuthenticationToken() {
        return UsernamePasswordAuthenticationFilter.class;
    }

    @Override
    public AuthenticationProvider authenticationProvider() {
        CustomDaoAuthenticationProvider customDaoAuthenticationProvider = new CustomDaoAuthenticationProvider(passwordEncoder, userDetailsService, successUserDetailsChecker, errorUserDetailsChecker);
        return customDaoAuthenticationProvider;
    }

}
