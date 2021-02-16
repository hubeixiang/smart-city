package com.sct.service.database.entity;

import com.sct.service.users.security.UserEntity;

import java.io.Serializable;
import java.util.Date;

public class ScUser implements UserEntity,Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.wx_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String wxId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.user_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.user_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.password
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.email
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.card_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String cardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.valid_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Boolean validStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_user.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_user
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.id
     *
     * @return the value of sc_user.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.id
     *
     * @param id the value for sc_user.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.wx_id
     *
     * @return the value of sc_user.wx_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.wx_id
     *
     * @param wxId the value for sc_user.wx_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.user_id
     *
     * @return the value of sc_user.user_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.user_id
     *
     * @param userId the value for sc_user.user_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.user_name
     *
     * @return the value of sc_user.user_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.user_name
     *
     * @param userName the value for sc_user.user_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.password
     *
     * @return the value of sc_user.password
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.password
     *
     * @param password the value for sc_user.password
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.mobile
     *
     * @return the value of sc_user.mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.mobile
     *
     * @param mobile the value for sc_user.mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.email
     *
     * @return the value of sc_user.email
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.email
     *
     * @param email the value for sc_user.email
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.card_id
     *
     * @return the value of sc_user.card_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.card_id
     *
     * @param cardId the value for sc_user.card_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.valid_status
     *
     * @return the value of sc_user.valid_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Boolean getValidStatus() {
        return validStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.valid_status
     *
     * @param validStatus the value for sc_user.valid_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setValidStatus(Boolean validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.create_time
     *
     * @return the value of sc_user.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.create_time
     *
     * @param createTime the value for sc_user.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.modify_time
     *
     * @return the value of sc_user.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.modify_time
     *
     * @param modifyTime the value for sc_user.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_user.comment
     *
     * @return the value of sc_user.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_user.comment
     *
     * @param comment the value for sc_user.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}