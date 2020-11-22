package com.sct.application.authorization.properties;

public class SmsCode {
    //是否生效短信登录
    private boolean enable;
    //是否默认登录方式
    private boolean active = false;
    //短信验证码登录时超时时间,0为不设置
    private int timeout = 0;
    /**
     * 手机号码短信登录时提交短信验证码并进行验证处理登录的url
     */
    private String smsLoginPostUrl = "/login-mobile";
    private String mobileMessageFormat = null;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getSmsLoginPostUrl() {
        return smsLoginPostUrl;
    }

    public void setSmsLoginPostUrl(String smsLoginPostUrl) {
        this.smsLoginPostUrl = smsLoginPostUrl;
    }

    public String getMobileMessageFormat() {
        return mobileMessageFormat;
    }

    public void setMobileMessageFormat(String mobileMessageFormat) {
        this.mobileMessageFormat = mobileMessageFormat;
    }
}