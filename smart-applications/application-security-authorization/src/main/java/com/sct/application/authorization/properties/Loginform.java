package com.sct.application.authorization.properties;

public class Loginform {
    //登录页面
    private int timeout = 10 * 60;
    /**
     * 默认的登录页面url
     */
    private String loginPageUrl = "/";
    /**
     * 登录错误调转页面url
     */
    private String loginErrorPageUrl = "/";
    /**
     * 登录成功默认调转页面url
     */
    private String loginDefaultSucessUrl = "/";

    /**
     * 默认登录时,提交登录信息,服务进行处理的url
     * 只是用户密码，或者手机号码短信登录
     */
    private String defaultLoginPostUrl = "/login";

    /**
     * 默认session超时时处理调转的页面
     */
    private String sessionInvalidPageUrl = "/login-error";

    /**
     * 登录完毕后,获取当前登录用户信息的路径配置
     */
    /**
     * oauth2登录后获取用户信息的url
     */
    private String oauthLoginUser = "";
    /**
     * 正常web登录后获取用户信息的url
     */
    private String webLoginUser = "";


    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
    }

    public String getLoginErrorPageUrl() {
        return loginErrorPageUrl;
    }

    public void setLoginErrorPageUrl(String loginErrorPageUrl) {
        this.loginErrorPageUrl = loginErrorPageUrl;
    }

    public String getLoginDefaultSucessUrl() {
        return loginDefaultSucessUrl;
    }

    public void setLoginDefaultSucessUrl(String loginDefaultSucessUrl) {
        this.loginDefaultSucessUrl = loginDefaultSucessUrl;
    }

    public String getDefaultLoginPostUrl() {
        return defaultLoginPostUrl;
    }

    public void setDefaultLoginPostUrl(String defaultLoginPostUrl) {
        this.defaultLoginPostUrl = defaultLoginPostUrl;
    }

    public String getSessionInvalidPageUrl() {
        return sessionInvalidPageUrl;
    }

    public void setSessionInvalidPageUrl(String sessionInvalidPageUrl) {
        this.sessionInvalidPageUrl = sessionInvalidPageUrl;
    }

    public String getOauthLoginUser() {
        return oauthLoginUser;
    }

    public void setOauthLoginUser(String oauthLoginUser) {
        this.oauthLoginUser = oauthLoginUser;
    }

    public String getWebLoginUser() {
        return webLoginUser;
    }

    public void setWebLoginUser(String webLoginUser) {
        this.webLoginUser = webLoginUser;
    }
}
