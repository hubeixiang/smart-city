/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sct.application.authorization.autoconfigure.web;


import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.audit.AuthenticationSuccessAudit;
import com.sct.application.authorization.support.entrypoint.CustomLoginUrlAuthenticationEntryPoint;
import com.sct.application.authorization.support.formlogin.CustomFormLoginConfigurer;
import com.sct.application.authorization.support.login.handler.CustomAuthenticationSuccessHandler;
import com.sct.application.authorization.support.login.handler.CustomSimpleUrlAuthenticationFailureHandler;
import com.sct.application.authorization.support.providers.AuthenticationProviderDefine;
import com.sct.application.authorization.support.session.CustomInvalidSessionStrategy;
import com.sct.application.authorization.support.session.CustomSessionInformationExpiredStrategy;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.application.authorization.support.session.CustomSessionRegistryImpl;
import com.sct.application.authorization.util.SecurityServiceConstants;
import com.sct.commons.utils.JsonUtils;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import com.sct.service.sucurity.support.password.factory.SctPasswordEncoderFactories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.GenericApplicationListenerAdapter;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.context.DelegatingApplicationListener;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 验证服务器的安全控制实现
 *
 * @author Joe Grandja
 */
@EnableWebSecurity
public class CustomAuthorizationWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(CustomAuthorizationWebSecurityConfigurer.class);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 登录相关的外部配置
     */
    @Autowired
    private ILoginProperties iLoginProperties;

    /**
     * 依据登录用户id查询
     */
    @Qualifier("authorityUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 登录用户session控制策略
     */
    @Autowired
    private CustomSessionRegistry customSessionRegistry;

    /**
     * 认证成功审计处理
     */
    @Autowired
    private AuthenticationSuccessAudit authenticationSuccessAudit;

    /**
     * 所有提供的认证比对配置
     */
    @Autowired
    private AuthenticationProviderDefineAutoconfigure authenticationProviderDefineAutoconfigure;


    @Autowired
    @Qualifier("customLogoutSuccessHandler")
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomSimpleUrlAuthenticationFailureHandler customSimpleUrlAuthenticationFailureHandler;


    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    @Autowired
    private CustomInvalidSessionStrategy customInvalidSessionStrategy;


    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                //定义不用验证的url
                .antMatchers("/", String.format("/%s/keys", Oauth2Constants.Oauth2_Context_Path), "/favicon.ico", "/**/*.js", "/**/*.css", "/webjars/**", "/welcome", "/welcome0", "/welcome1", "/static/**", "/code/**", "/actuator/**", "/anonymous/**").permitAll()
                //登录与登录失败调转url不用验证
                // 自定义页面的路径不用验证
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/login-embed/loginView").permitAll()
                .antMatchers(HttpMethod.GET, iLoginProperties.getLoginform().getLoginPageUrl()).permitAll()
                // 登录失败跳转不用验证
                .antMatchers(HttpMethod.GET, iLoginProperties.getLoginform().getLoginErrorPageUrl()).permitAll()
                // 服务失败跳转不用验证
                .antMatchers(HttpMethod.GET, "/error").permitAll()
                .antMatchers(HttpMethod.GET, iLoginProperties.getLoginform().getSessionInvalidPageUrl()).permitAll()
                .anyRequest().authenticated();


//        http.addFilterBefore(loginPageControlForLongtimeFilter, SmsCodeFilter.class);
        // 将短信验证码认证配置加到 Spring Security 中
        http.apply(smsCodeAuthenticationSecurityConfig)
                //配置登陆验证成功后处理的映射,未配置时就会默认走登陆框中配置的验证成功后处理器
                .successHandler(customAuthenticationSuccessHandler());


        if (customSessionRegistry.isSessionRegistry()) {
            int maximumSessions = customSessionRegistry.getSessionManagerConfig().getMaxmumSessions();
            boolean maxSessionsPreventsLogin = customSessionRegistry.getSessionManagerConfig().isMaxSessionsPreventsLogin();
            //session 管理,session并发控制
            http.sessionManagement()
//                .invalidSessionUrl("/session/invalid")
                    .invalidSessionStrategy(customInvalidSessionStrategy)
                    // 设置同一个用户只能有一个登陆session
                    .maximumSessions(maximumSessions)
                    // 设置为true，即禁止后面其它人的登录 ,不设置则是后登录导致前登录失效
                    .maxSessionsPreventsLogin(maxSessionsPreventsLogin)
                    //其他地方登录session失效处理策略
                    .expiredSessionStrategy(new CustomSessionInformationExpiredStrategy())
                    //设置自己的sessionRegistry
                    .sessionRegistry(sessionRegistry());
        } else {
            /**
             //session 管理,session超时管理
             http.sessionManagement()
             //                .invalidSessionUrl("/session/invalid")
             .invalidSessionStrategy(customInvalidSessionStrategy);
             //                    设置自己的sessionRegistry
             //                    .sessionRegistry(sessionRegistry());
             **/
            http.sessionManagement()
                    //                .invalidSessionUrl("/session/invalid")
//                    .invalidSessionStrategy(customInvalidSessionStrategy)
                    // 设置不限制用户登录数
                    .maximumSessions(-1)
                    // 设置为true，即禁止后面其它人的登录 ,不设置则是后登录导致前登录失效
//                    .maxSessionsPreventsLogin(maxSessionsPreventsLogin)
                    //其他地方登录session失效处理策略
//                    .expiredSessionStrategy(new CustomSessionInformationExpiredStrategy())
                    //设置自己的sessionRegistry
                    .sessionRegistry(sessionRegistry());
        }


//        http.sessionManagement().addObjectPostProcessor(new ObjectPostProcessor<ConcurrentSessionFilter>() {
//            @Override
//            public ConcurrentSessionFilter postProcess(ConcurrentSessionFilter object) {
//                sessionRegistry = object.get
//                return null;
//            }
//        });
        AuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new CustomLoginUrlAuthenticationEntryPoint(iLoginProperties.getLoginform());
        http.exceptionHandling().authenticationEntryPoint(loginUrlAuthenticationEntryPoint);

        //定义登录操作
        String defaultFailureUrl = iLoginProperties.getLoginform().getLoginErrorPageUrl();
        customSimpleUrlAuthenticationFailureHandler.setDefaultFailureUrl(defaultFailureUrl);
        //使用自定义的用户密码登录处理替换原有的登录处理
        CustomFormLoginConfigurer customFormLoginConfigurer = new CustomFormLoginConfigurer();
        http.apply(customFormLoginConfigurer);
        http.addFilterAt(customFormLoginConfigurer.getCustomAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.getConfigurer(CustomFormLoginConfigurer.class)
//        http.formLogin()
                //定义解析登录时除了用户密码外的验证详细信息
//                .authenticationDetailsSource(new CustomWebAuthenticationDetailsSource())
                //设置自定义的登录页面
                .loginPage(iLoginProperties.getLoginform().getLoginPageUrl())
                //设置自定义登录页面中提交登录信息进行处理的post接口url
                .loginProcessingUrl(iLoginProperties.getLoginform().getDefaultLoginPostUrl())
                //登录失败跳转，指定的路径要能匿名访问
//                .failureUrl("/login-error")
                .failureHandler(customSimpleUrlAuthenticationFailureHandler)
                //登录成功重定向地址(与登录成功调转地址)
//                .successForwardUrl("/index");
                //登录成功后自定义的用户信息记录,比如记录登录用户,登录用户数等等
                //两中,使用其中一种
//                .defaultSuccessUrl("/index")
//                .successHandler(new CustomAuthenticationSuccessHandler());
                .successHandler(customAuthenticationSuccessHandler());

        //登出
        http.logout()
                //用户登出成功处理
                .logoutSuccessHandler(logoutSuccessHandler)
                //退出时session失效
                .invalidateHttpSession(true)
//                退出时删除指定的cookie
                .deleteCookies("JSESSIONID");

        //跨域,使用org.springframework.security.oauth.samples.custom.filter.CustomCorsFilter进行控制
        http.cors();
        http.csrf().disable();

        //custom http session cache
        String loginFormJson = JsonUtils.toJson(iLoginProperties.getLoginform());
        System.out.println(String.format("loginForm:%s", loginFormJson));
    }
    // @formatter:on

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager authenticationManager = super.authenticationManagerBean();
        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return SctPasswordEncoderFactories.getInstance().getPasswordEncoder(iLoginProperties.getPassword().getType());
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        String defaultSuccessUrl = iLoginProperties.getLoginform().getLoginDefaultSucessUrl();
        CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl(defaultSuccessUrl);
        handler.setAlwaysUseDefaultTargetUrl(false);
        handler.setTargetUrlParameter(SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID);
        handler.setAuthenticationSuccessAudit(authenticationSuccessAudit);
        return handler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        authenticationProvider(auth);
    }

    public void authenticationProvider(AuthenticationManagerBuilder auth) {
        List<AuthenticationProviderDefine> list = authenticationProviderDefineAutoconfigure.getInitAuthenticationProviderDefine();
        for (AuthenticationProviderDefine authenticationProviderDefine : list) {
            auth.authenticationProvider(authenticationProviderDefine.authenticationProvider());
        }
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        CustomSessionRegistryImpl sessionRegistry = new CustomSessionRegistryImpl();
        registerDelegateApplicationListener(sessionRegistry);
        return sessionRegistry;
    }

    private void registerDelegateApplicationListener(
            ApplicationListener<?> delegate) {
        if (applicationContext.getBeansOfType(DelegatingApplicationListener.class).isEmpty()) {
            return;
        }
        DelegatingApplicationListener delegating = applicationContext
                .getBean(DelegatingApplicationListener.class);
        SmartApplicationListener smartListener = new GenericApplicationListenerAdapter(
                delegate);
        delegating.addListener(smartListener);
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<HttpSessionEventPublisher> listenerRegBean =
                new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new HttpSessionEventPublisher());
        return listenerRegBean;
    }
}