package com.sct.sms.properties;

public class SendProperties {
    //网关签名编码，必填，签名编码由企业在中国移动集团开通帐号分配.发送短信时使用
    private String sendSign;

    public String getSendSign() {
        return sendSign;
    }

    public void setSendSign(String sendSign) {
        this.sendSign = sendSign;
    }
}
