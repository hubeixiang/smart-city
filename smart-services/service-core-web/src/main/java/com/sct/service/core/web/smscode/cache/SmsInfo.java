package com.sct.service.core.web.smscode.cache;

public class SmsInfo implements java.io.Serializable {
    private String phone;
    private String code;
    //<=0 永不超时
    private long expireDate;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
