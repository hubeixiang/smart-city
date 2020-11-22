package com.sct.service.core.web.captcha.cache;

public class CaptchaInfo implements java.io.Serializable {
    private String code;
    //<=0 永不超时
    private long expireDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }
}
