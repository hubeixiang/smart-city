package com.sct.application.authorization.properties;

public class CaptchaCode {
    //验证码登录时超时时间,0为不设置
    private int timeout = 0;
    //登录是否使用图片验证码
    private boolean enable = true;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
