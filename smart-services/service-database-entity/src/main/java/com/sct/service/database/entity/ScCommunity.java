package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScCommunity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.name
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.comment
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.address
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.telephone
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String telephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.pic_url
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String picUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.longitude
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Float longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.latitude
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Float latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.create_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.creator_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community.modify_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_community
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.id
     *
     * @return the value of sc_community.id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.id
     *
     * @param id the value for sc_community.id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.name
     *
     * @return the value of sc_community.name
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.name
     *
     * @param name the value for sc_community.name
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.comment
     *
     * @return the value of sc_community.comment
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.comment
     *
     * @param comment the value for sc_community.comment
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.address
     *
     * @return the value of sc_community.address
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.address
     *
     * @param address the value for sc_community.address
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.telephone
     *
     * @return the value of sc_community.telephone
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.telephone
     *
     * @param telephone the value for sc_community.telephone
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.pic_url
     *
     * @return the value of sc_community.pic_url
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.pic_url
     *
     * @param picUrl the value for sc_community.pic_url
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.longitude
     *
     * @return the value of sc_community.longitude
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.longitude
     *
     * @param longitude the value for sc_community.longitude
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.latitude
     *
     * @return the value of sc_community.latitude
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.latitude
     *
     * @param latitude the value for sc_community.latitude
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.create_time
     *
     * @return the value of sc_community.create_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.create_time
     *
     * @param createTime the value for sc_community.create_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.creator_id
     *
     * @return the value of sc_community.creator_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.creator_id
     *
     * @param creatorId the value for sc_community.creator_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community.modify_time
     *
     * @return the value of sc_community.modify_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community.modify_time
     *
     * @param modifyTime the value for sc_community.modify_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}