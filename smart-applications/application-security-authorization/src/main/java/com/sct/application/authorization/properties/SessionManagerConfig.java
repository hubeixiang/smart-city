package com.sct.application.authorization.properties;

public class SessionManagerConfig {
    private int maxmumSessions = 0;
    private boolean maxSessionsPreventsLogin = false;
    /**
     * 当用户登录安全与oauth2的resource server在同一各服务中
     */
    private boolean securityWithResourceServer = true;

    public int getMaxmumSessions() {
        return maxmumSessions;
    }

    public void setMaxmumSessions(int maxmumSessions) {
        this.maxmumSessions = maxmumSessions;
    }

    public boolean isMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }

    public boolean isSecurityWithResourceServer() {
        return securityWithResourceServer;
    }

    public void setSecurityWithResourceServer(boolean securityWithResourceServer) {
        this.securityWithResourceServer = securityWithResourceServer;
    }
}
