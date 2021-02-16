package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScCommunityParty implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.name_short
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String nameShort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.parent_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String parentName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.party_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer partyType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.party_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer partyLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.contacts
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String contacts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.email
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.is_sub_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer isSubParty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_party.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_community_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.id
     *
     * @return the value of sc_community_party.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.id
     *
     * @param id the value for sc_community_party.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.name
     *
     * @return the value of sc_community_party.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.name
     *
     * @param name the value for sc_community_party.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.name_short
     *
     * @return the value of sc_community_party.name_short
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getNameShort() {
        return nameShort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.name_short
     *
     * @param nameShort the value for sc_community_party.name_short
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setNameShort(String nameShort) {
        this.nameShort = nameShort == null ? null : nameShort.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.parent_name
     *
     * @return the value of sc_community_party.parent_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.parent_name
     *
     * @param parentName the value for sc_community_party.parent_name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.party_type
     *
     * @return the value of sc_community_party.party_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getPartyType() {
        return partyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.party_type
     *
     * @param partyType the value for sc_community_party.party_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPartyType(Integer partyType) {
        this.partyType = partyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.party_level
     *
     * @return the value of sc_community_party.party_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getPartyLevel() {
        return partyLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.party_level
     *
     * @param partyLevel the value for sc_community_party.party_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPartyLevel(Integer partyLevel) {
        this.partyLevel = partyLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.comment
     *
     * @return the value of sc_community_party.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.comment
     *
     * @param comment the value for sc_community_party.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.contacts
     *
     * @return the value of sc_community_party.contacts
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.contacts
     *
     * @param contacts the value for sc_community_party.contacts
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.mobile
     *
     * @return the value of sc_community_party.mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.mobile
     *
     * @param mobile the value for sc_community_party.mobile
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.email
     *
     * @return the value of sc_community_party.email
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.email
     *
     * @param email the value for sc_community_party.email
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.community_id
     *
     * @return the value of sc_community_party.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.community_id
     *
     * @param communityId the value for sc_community_party.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.is_sub_party
     *
     * @return the value of sc_community_party.is_sub_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getIsSubParty() {
        return isSubParty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.is_sub_party
     *
     * @param isSubParty the value for sc_community_party.is_sub_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setIsSubParty(Integer isSubParty) {
        this.isSubParty = isSubParty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.create_time
     *
     * @return the value of sc_community_party.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.create_time
     *
     * @param createTime the value for sc_community_party.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.creator_id
     *
     * @return the value of sc_community_party.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.creator_id
     *
     * @param creatorId the value for sc_community_party.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_party.modify_time
     *
     * @return the value of sc_community_party.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_party.modify_time
     *
     * @param modifyTime the value for sc_community_party.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}