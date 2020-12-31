package com.sct.sms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sms")
@Component
public class SmsSystemProperties {
    private LoginProperties login = new LoginProperties();
    private SendProperties send = new SendProperties();

    public LoginProperties getLogin() {
        return login;
    }

    public void setLogin(LoginProperties login) {
        this.login = login;
    }

    public SendProperties getSend() {
        return send;
    }

    public void setSend(SendProperties send) {
        this.send = send;
    }
}
