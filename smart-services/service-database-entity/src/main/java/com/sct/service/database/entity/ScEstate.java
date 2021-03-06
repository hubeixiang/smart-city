package com.sct.service.database.entity;

import com.sct.service.database.extend.BaseExtend;
import com.sct.service.database.extend.ScGridEstateExtendDisplay;

import java.io.Serializable;
import java.util.Date;

public class ScEstate extends ScGridEstateExtendDisplay implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.coverd_area
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float coverdArea;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.property_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String propertyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.property_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String propertyLeader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.property_fee
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float propertyFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.property_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer propertyType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.property_mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String propertyMobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_estate.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_estate
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.id
     *
     * @return the value of sc_estate.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.id
     *
     * @param id the value for sc_estate.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.name
     *
     * @return the value of sc_estate.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.name
     *
     * @param name the value for sc_estate.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.coverd_area
     *
     * @return the value of sc_estate.coverd_area
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getCoverdArea() {
        return coverdArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.coverd_area
     *
     * @param coverdArea the value for sc_estate.coverd_area
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCoverdArea(Float coverdArea) {
        this.coverdArea = coverdArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.comment
     *
     * @return the value of sc_estate.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.comment
     *
     * @param comment the value for sc_estate.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.community_id
     *
     * @return the value of sc_estate.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.community_id
     *
     * @param communityId the value for sc_estate.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.property_name
     *
     * @return the value of sc_estate.property_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.property_name
     *
     * @param propertyName the value for sc_estate.property_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.property_leader
     *
     * @return the value of sc_estate.property_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getPropertyLeader() {
        return propertyLeader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.property_leader
     *
     * @param propertyLeader the value for sc_estate.property_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPropertyLeader(String propertyLeader) {
        this.propertyLeader = propertyLeader == null ? null : propertyLeader.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.property_fee
     *
     * @return the value of sc_estate.property_fee
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getPropertyFee() {
        return propertyFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.property_fee
     *
     * @param propertyFee the value for sc_estate.property_fee
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPropertyFee(Float propertyFee) {
        this.propertyFee = propertyFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.property_type
     *
     * @return the value of sc_estate.property_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getPropertyType() {
        return propertyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.property_type
     *
     * @param propertyType the value for sc_estate.property_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.property_mobile
     *
     * @return the value of sc_estate.property_mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getPropertyMobile() {
        return propertyMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.property_mobile
     *
     * @param propertyMobile the value for sc_estate.property_mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPropertyMobile(String propertyMobile) {
        this.propertyMobile = propertyMobile == null ? null : propertyMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.create_time
     *
     * @return the value of sc_estate.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.create_time
     *
     * @param createTime the value for sc_estate.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.creator_id
     *
     * @return the value of sc_estate.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.creator_id
     *
     * @param creatorId the value for sc_estate.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_estate.modify_time
     *
     * @return the value of sc_estate.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_estate.modify_time
     *
     * @param modifyTime the value for sc_estate.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}