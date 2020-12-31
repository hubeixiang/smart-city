package com.sct.sms.properties;

public class LoginProperties {
    private String url;
    private String userAccount;
    private String password;
    private String ecname;
    //启动时
    private int type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEcname() {
        return ecname;
    }

    public void setEcname(String ecname) {
        this.ecname = ecname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginProperties[url=").append(url)
                .append(",userAccount=").append(userAccount)
                .append(",password=").append(password)
                .append(",ecname=").append(ecname)
                .append(",type=").append(type).append("]");
        return sb.toString();
    }
}
