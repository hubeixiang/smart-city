package com.sct.application.authorization.properties;

public class ThrottleSignIn {
    //登录重试次数,-1表示不生效
    private int threshold = -1;
    //登录密码错误间隔时间
    private int rangeSeconds = -1;
    //密码错误超过次数后后锁定时间
    private int lockSeconds = -1;
    private int passwordExpireDays = 90;

    public boolean isEnabled() {
        return getThreshold() > 0 && getRangeSeconds() > 0 && getLockSeconds() > 0;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getRangeSeconds() {
        return rangeSeconds;
    }

    public void setRangeSeconds(int rangeSeconds) {
        this.rangeSeconds = rangeSeconds;
    }

    public int getLockSeconds() {
        return lockSeconds;
    }

    public void setLockSeconds(int lockSeconds) {
        this.lockSeconds = lockSeconds;
    }

    public int getPasswordExpireDays() {
        return passwordExpireDays;
    }

    public void setPasswordExpireDays(int passwordExpireDays) {
        this.passwordExpireDays = passwordExpireDays;
    }
}
