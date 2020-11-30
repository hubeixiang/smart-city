package com.sct.service.users.data.entity;

import com.sct.service.users.security.UserEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 只存储用户登录认证相关信息,其他信息不保存
 */
public class AuthorityUser implements UserEntity {
    private static final long serialVersionUID = 2L;
    //用户表的主键
    private String pkId;
    //用户登录名
    private String loginId;
    //用户邮箱
    private String userEmail;
    //用户手机号
    private String userMobile;
    //用户联系电话号码,手机或者座机
    private String userPhone;
    //用户关联的微信id
    private String wxId;
    //用户关联的支付宝id;
    private String zfbId;

    //用户展示的名称
    private String userName;
    //用户状态
    private Integer userStatus;
    //用户被删除的时间
    private Instant deletedTime;
    //用户密码不可逆加密方式
    private String password;
    //用户密码,可加密方式加密的密码
    private String passwordRaw;
    //用户创建时间
    private Instant createTime;
    //用户修改时间
    private Instant modifiedTime;
    //用户有效开始时间
    private LocalDateTime validStartTime;
    //用户有效结束时间
    private LocalDateTime validEndTime;
    //用户修改密码次数
    private Integer pwdMustChange;
    //用户密码过期日期
    private LocalDate pwdExpireDate;
    //用户登录失败次数
    private Integer loginFailedTimes;
    //用户登录失败后锁定时间
    private Instant loginLockTime;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getZfbId() {
        return zfbId;
    }

    public void setZfbId(String zfbId) {
        this.zfbId = zfbId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Instant getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Instant deletedTime) {
        this.deletedTime = deletedTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRaw() {
        return passwordRaw;
    }

    public void setPasswordRaw(String passwordRaw) {
        this.passwordRaw = passwordRaw;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public LocalDateTime getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(LocalDateTime validStartTime) {
        this.validStartTime = validStartTime;
    }

    public LocalDateTime getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(LocalDateTime validEndTime) {
        this.validEndTime = validEndTime;
    }

    public Integer getPwdMustChange() {
        return pwdMustChange;
    }

    public void setPwdMustChange(Integer pwdMustChange) {
        this.pwdMustChange = pwdMustChange;
    }

    public LocalDate getPwdExpireDate() {
        return pwdExpireDate;
    }

    public void setPwdExpireDate(LocalDate pwdExpireDate) {
        this.pwdExpireDate = pwdExpireDate;
    }

    public Integer getLoginFailedTimes() {
        return loginFailedTimes;
    }

    public void setLoginFailedTimes(Integer loginFailedTimes) {
        this.loginFailedTimes = loginFailedTimes;
    }

    public Instant getLoginLockTime() {
        return loginLockTime;
    }

    public void setLoginLockTime(Instant loginLockTime) {
        this.loginLockTime = loginLockTime;
    }

    //继承的安全相关的需要实现的方法,默认都是正常的,正则验证使用外部的 UserDetailsChecker 进行验证
    public boolean isAccountNonExpired() {
        LocalDateTime current = LocalDateTime.now();
        if (validStartTime != null) {
            if (validStartTime.isAfter(current)) {
                return false;
            }
        }
        if (validEndTime != null) {
            if (validEndTime.isBefore(current)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAccountNonLocked() {
        return loginLockTime == null || (loginLockTime != null && loginLockTime.isBefore(Instant.now()));
    }

    public boolean isCredentialsNonExpired() {
        return pwdExpireDate == null || (pwdExpireDate != null && pwdExpireDate.isAfter(LocalDate.now()));
    }

    public boolean isEnabled() {
        return deletedTime == null;
    }
}
