package com.sct.service.core.web.support.simple;

public class PasswordResource implements ResourceResponse {
    private String password;
    private String oldPassword;

    public static PasswordResource of(String password) {
        PasswordResource passwordResource = new PasswordResource();
        passwordResource.setPassword(password);
        return passwordResource;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
