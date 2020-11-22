package com.sct.service.users.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 安全管理保存在session中的用户实体
 * 此实体继承User,并将User中的username替换为userid
 */
public class SecurityUser extends User {
    private String userId;
    private String loginId;
    private String securityUserName;
    private LocalDateTime loginTime = LocalDateTime.now();
    private UserEntity userEntity;
    //登录时登录附加信息,一般是存储改用户当前的登录方式
    private Object loginAdditional;

    public SecurityUser(String userId, String loginId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this(userId, loginId, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, null);
    }

    public SecurityUser(String userId, String loginId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
            , UserEntity userEntity) {
        super(userId, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.loginId = loginId;
        this.securityUserName = username;
        this.userEntity = userEntity;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SecurityUser) {
            return userId.equals(((SecurityUser) rhs).userId);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("userId: ").append(this.userId).append("; ");
        sb.append("loginId: ").append(this.loginId).append("; ");
        sb.append("securityUserName: ").append(this.securityUserName).append("; ");
        sb.append("userName: ").append(this.getUsername()).append("; ");
        sb.append("loginTime: ").append(this.loginTime).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.isEnabled()).append("; ");
        sb.append("AccountNonExpired: ").append(this.isAccountNonExpired()).append("; ");
        sb.append("credentialsNonExpired: ").append(this.isCredentialsNonExpired()).append("; ");
        sb.append("AccountNonLocked: ").append(this.isAccountNonLocked()).append("; ");
        sb.append("loginAdditional: ").append(this.getLoginAdditional()).append("; ");

        if (!getAuthorities().isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : getAuthorities()) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    public String getUserId() {
        return userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getSecurityUserName() {
        return securityUserName;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Object getLoginAdditional() {
        return loginAdditional;
    }

    public void setLoginAdditional(Object loginAdditional) {
        this.loginAdditional = loginAdditional;
    }
}
