package com.sct.service.users.data.entity;

import com.sct.service.users.security.UserEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuthorityUser implements UserEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sec_user
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.user_id
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String userId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.login_id
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String loginId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.user_name
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String userName;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.user_status
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Integer userStatus;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.deleted_time
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Instant deletedTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.password
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String password;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.password_raw
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String passwordRaw;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.create_time
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Instant createTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.modified_time
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Instant modifiedTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.valid_start_time
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private LocalDateTime validStartTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.valid_end_time
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private LocalDateTime validEndTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.pwd_must_change
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Integer pwdMustChange;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.pwd_expire_date
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private LocalDate pwdExpireDate;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.login_failed_times
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Integer loginFailedTimes;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.login_lock_time
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Instant loginLockTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.user_email
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String userEmail;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.user_mobile
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String userMobile;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.user_phone
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String userPhone;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.memo
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private String memo;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.dept_id
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Integer deptId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_user.title_id
     *
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    private Integer titleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.user_id
     *
     * @return the value of sec_user.user_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.user_id
     *
     * @param userId the value for sec_user.user_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.login_id
     *
     * @return the value of sec_user.login_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.login_id
     *
     * @param loginId the value for sec_user.login_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.user_name
     *
     * @return the value of sec_user.user_name
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.user_name
     *
     * @param userName the value for sec_user.user_name
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.user_status
     *
     * @return the value of sec_user.user_status
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.user_status
     *
     * @param userStatus the value for sec_user.user_status
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.deleted_time
     *
     * @return the value of sec_user.deleted_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Instant getDeletedTime() {
        return deletedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.deleted_time
     *
     * @param deletedTime the value for sec_user.deleted_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setDeletedTime(Instant deletedTime) {
        this.deletedTime = deletedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.password
     *
     * @return the value of sec_user.password
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.password
     *
     * @param password the value for sec_user.password
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.password_raw
     *
     * @return the value of sec_user.password_raw
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getPasswordRaw() {
        return passwordRaw;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.password_raw
     *
     * @param passwordRaw the value for sec_user.password_raw
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setPasswordRaw(String passwordRaw) {
        this.passwordRaw = passwordRaw == null ? null : passwordRaw.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.create_time
     *
     * @return the value of sec_user.create_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Instant getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.create_time
     *
     * @param createTime the value for sec_user.create_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.modified_time
     *
     * @return the value of sec_user.modified_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Instant getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.modified_time
     *
     * @param modifiedTime the value for sec_user.modified_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.valid_start_time
     *
     * @return the value of sec_user.valid_start_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public LocalDateTime getValidStartTime() {
        return validStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.valid_start_time
     *
     * @param validStartTime the value for sec_user.valid_start_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setValidStartTime(LocalDateTime validStartTime) {
        this.validStartTime = validStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.valid_end_time
     *
     * @return the value of sec_user.valid_end_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public LocalDateTime getValidEndTime() {
        return validEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.valid_end_time
     *
     * @param validEndTime the value for sec_user.valid_end_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setValidEndTime(LocalDateTime validEndTime) {
        this.validEndTime = validEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.pwd_must_change
     *
     * @return the value of sec_user.pwd_must_change
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Integer getPwdMustChange() {
        return pwdMustChange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.pwd_must_change
     *
     * @param pwdMustChange the value for sec_user.pwd_must_change
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setPwdMustChange(Integer pwdMustChange) {
        this.pwdMustChange = pwdMustChange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.pwd_expire_date
     *
     * @return the value of sec_user.pwd_expire_date
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public LocalDate getPwdExpireDate() {
        return pwdExpireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.pwd_expire_date
     *
     * @param pwdExpireDate the value for sec_user.pwd_expire_date
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setPwdExpireDate(LocalDate pwdExpireDate) {
        this.pwdExpireDate = pwdExpireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.login_failed_times
     *
     * @return the value of sec_user.login_failed_times
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Integer getLoginFailedTimes() {
        return loginFailedTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.login_failed_times
     *
     * @param loginFailedTimes the value for sec_user.login_failed_times
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setLoginFailedTimes(Integer loginFailedTimes) {
        this.loginFailedTimes = loginFailedTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.login_lock_time
     *
     * @return the value of sec_user.login_lock_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Instant getLoginLockTime() {
        return loginLockTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.login_lock_time
     *
     * @param loginLockTime the value for sec_user.login_lock_time
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setLoginLockTime(Instant loginLockTime) {
        this.loginLockTime = loginLockTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.user_email
     *
     * @return the value of sec_user.user_email
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.user_email
     *
     * @param userEmail the value for sec_user.user_email
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.user_mobile
     *
     * @return the value of sec_user.user_mobile
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.user_mobile
     *
     * @param userMobile the value for sec_user.user_mobile
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.user_phone
     *
     * @return the value of sec_user.user_phone
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.user_phone
     *
     * @param userPhone the value for sec_user.user_phone
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.memo
     *
     * @return the value of sec_user.memo
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.memo
     *
     * @param memo the value for sec_user.memo
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.dept_id
     *
     * @return the value of sec_user.dept_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.dept_id
     *
     * @param deptId the value for sec_user.dept_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_user.title_id
     *
     * @return the value of sec_user.title_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public Integer getTitleId() {
        return titleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_user.title_id
     *
     * @param titleId the value for sec_user.title_id
     * @mbg.generated Wed Aug 12 10:18:23 CST 2020
     */
    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }


    //继承的安全相关的需要实现的方法,默认都是正常的,正则验证使用外部的 UserDetailsChecker 进行验证
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
