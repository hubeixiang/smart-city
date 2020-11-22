package com.sct.application.authorization.properties;

public class Password {
    private boolean enable;
    private boolean active = false;
    /**
     * 密码加密方式
     */
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
