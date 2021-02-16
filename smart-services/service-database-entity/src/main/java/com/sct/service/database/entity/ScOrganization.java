package com.sct.service.database.entity;

import com.sct.service.database.extend.ScOrganizationExtendDisplay;

import java.io.Serializable;
import java.util.Date;

public class ScOrganization extends ScOrganizationExtendDisplay implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.org_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer orgType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer gridId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.legal_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String legalName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.legal_card_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String legalCardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.legal_mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String legalMobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.credit_code
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String creditCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.business_code
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String businessCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.register_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer registerStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.register_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date registerDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.unregister_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date unregisterDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.marker_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer markerStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_organization.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_organization
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.id
     *
     * @return the value of sc_organization.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.id
     *
     * @param id the value for sc_organization.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.name
     *
     * @return the value of sc_organization.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.name
     *
     * @param name the value for sc_organization.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.org_type
     *
     * @return the value of sc_organization.org_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getOrgType() {
        return orgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.org_type
     *
     * @param orgType the value for sc_organization.org_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.community_id
     *
     * @return the value of sc_organization.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.community_id
     *
     * @param communityId the value for sc_organization.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.grid_id
     *
     * @return the value of sc_organization.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getGridId() {
        return gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.grid_id
     *
     * @param gridId the value for sc_organization.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.legal_name
     *
     * @return the value of sc_organization.legal_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.legal_name
     *
     * @param legalName the value for sc_organization.legal_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.legal_card_id
     *
     * @return the value of sc_organization.legal_card_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getLegalCardId() {
        return legalCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.legal_card_id
     *
     * @param legalCardId the value for sc_organization.legal_card_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLegalCardId(String legalCardId) {
        this.legalCardId = legalCardId == null ? null : legalCardId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.legal_mobile
     *
     * @return the value of sc_organization.legal_mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getLegalMobile() {
        return legalMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.legal_mobile
     *
     * @param legalMobile the value for sc_organization.legal_mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLegalMobile(String legalMobile) {
        this.legalMobile = legalMobile == null ? null : legalMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.credit_code
     *
     * @return the value of sc_organization.credit_code
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.credit_code
     *
     * @param creditCode the value for sc_organization.credit_code
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.business_code
     *
     * @return the value of sc_organization.business_code
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.business_code
     *
     * @param businessCode the value for sc_organization.business_code
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.register_status
     *
     * @return the value of sc_organization.register_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getRegisterStatus() {
        return registerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.register_status
     *
     * @param registerStatus the value for sc_organization.register_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.register_date
     *
     * @return the value of sc_organization.register_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.register_date
     *
     * @param registerDate the value for sc_organization.register_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.unregister_date
     *
     * @return the value of sc_organization.unregister_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getUnregisterDate() {
        return unregisterDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.unregister_date
     *
     * @param unregisterDate the value for sc_organization.unregister_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setUnregisterDate(Date unregisterDate) {
        this.unregisterDate = unregisterDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.comment
     *
     * @return the value of sc_organization.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.comment
     *
     * @param comment the value for sc_organization.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.marker_status
     *
     * @return the value of sc_organization.marker_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getMarkerStatus() {
        return markerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.marker_status
     *
     * @param markerStatus the value for sc_organization.marker_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setMarkerStatus(Integer markerStatus) {
        this.markerStatus = markerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.longitude
     *
     * @return the value of sc_organization.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.longitude
     *
     * @param longitude the value for sc_organization.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.latitude
     *
     * @return the value of sc_organization.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.latitude
     *
     * @param latitude the value for sc_organization.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.create_time
     *
     * @return the value of sc_organization.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.create_time
     *
     * @param createTime the value for sc_organization.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.creator_id
     *
     * @return the value of sc_organization.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.creator_id
     *
     * @param creatorId the value for sc_organization.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_organization.modify_time
     *
     * @return the value of sc_organization.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_organization.modify_time
     *
     * @param modifyTime the value for sc_organization.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}