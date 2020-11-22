package com.sct.application.authorization.autoconfigure.web;

import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.sms.SmsCodeAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Qualifier("authorityUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ILoginProperties iLoginProperties;

    /**
     * 在
     *
     * @see CustomAuthorizationWebSecurityConfigurer 中注入
     */
    private AuthenticationSuccessHandler authenticationSuccessHandler = null;

    private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        authenticationFailureHandler.setDefaultFailureUrl(iLoginProperties.getLoginform().getLoginErrorPageUrl());

        AntPathRequestMatcher requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(iLoginProperties.getSms().getSmsLoginPostUrl(), "POST");
        smsCodeAuthenticationFilter.setRequiresAuthenticationRequestMatcher(requiresAuthenticationRequestMatcher);

        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SessionAuthenticationStrategy sessionAuthenticationStrategy = http
                .getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            smsCodeAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }

//        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
//        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

//        http.authenticationProvider(smsCodeAuthenticationProvider)
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public SmsCodeAuthenticationSecurityConfig successHandler(AuthenticationSuccessHandler successHandler) {
        this.authenticationSuccessHandler = successHandler;
        return this;
    }


}
