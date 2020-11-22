package com.sct.application.authorization.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "ilogin")
public class ILoginProperties {
    //登录页面相关的url定义
    private Loginform loginform = new Loginform();
    //系统使用的用户密码加密方式
    private Password password = new Password();
    //验证码登录设置
    private CaptchaCode captchaCode = new CaptchaCode();
    //短信码登录相关设置
    private SmsCode sms = new SmsCode();
    private SessionManagerConfig sessionManagerConfig = new SessionManagerConfig();
    private Throttle throttle = new Throttle();
    private List<AuthProvider> authProvider = new ArrayList<>();
    //默认提供的oauth2客户端信息
    private OauthClient oauthClient;

    public Loginform getLoginform() {
        return loginform;
    }

    public void setLoginform(Loginform loginform) {
        this.loginform = loginform;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public CaptchaCode getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(CaptchaCode captchaCode) {
        this.captchaCode = captchaCode;
    }

    public SmsCode getSms() {
        return sms;
    }

    public void setSms(SmsCode sms) {
        this.sms = sms;
    }

    public SessionManagerConfig getSessionManagerConfig() {
        return sessionManagerConfig;
    }

    public void setSessionManagerConfig(SessionManagerConfig sessionManagerConfig) {
        this.sessionManagerConfig = sessionManagerConfig;
    }

    public Throttle getThrottle() {
        return throttle;
    }

    public void setThrottle(Throttle throttle) {
        this.throttle = throttle;
    }

    public List<AuthProvider> getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(List<AuthProvider> authProvider) {
        this.authProvider = authProvider;
    }

    public OauthClient getOauthClient() {
        return oauthClient;
    }

    public void setOauthClient(OauthClient oauthClient) {
        this.oauthClient = oauthClient;
    }
}
