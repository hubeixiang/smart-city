package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScResident implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.name
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.sex
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.nation
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer nation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.mobile
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.card_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private String cardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.birthday
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private String birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.live_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer liveType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.political_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer politicalStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.qualifications
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer qualifications;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.marry_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer marryStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.faith
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer faith;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.work_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer workType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.is_risk
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer isRisk;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.risk_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer riskType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.military_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer militaryStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.is_settle
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer isSettle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.birth_province_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer birthProvinceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.birth_city_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer birthCityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.birth_county_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer birthCountyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.settle_province_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer settleProvinceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.settle_city_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer settleCityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.settle_county_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer settleCountyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.settle_address
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private String settleAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.register_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer registerStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.register_date
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Date registerDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.unregister_date
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Date unregisterDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.comment
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.create_time
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.creator_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_resident.modify_time
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_resident
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.id
     *
     * @return the value of sc_resident.id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.id
     *
     * @param id the value for sc_resident.id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.name
     *
     * @return the value of sc_resident.name
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.name
     *
     * @param name the value for sc_resident.name
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.sex
     *
     * @return the value of sc_resident.sex
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.sex
     *
     * @param sex the value for sc_resident.sex
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.nation
     *
     * @return the value of sc_resident.nation
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getNation() {
        return nation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.nation
     *
     * @param nation the value for sc_resident.nation
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setNation(Integer nation) {
        this.nation = nation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.mobile
     *
     * @return the value of sc_resident.mobile
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.mobile
     *
     * @param mobile the value for sc_resident.mobile
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.card_id
     *
     * @return the value of sc_resident.card_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.card_id
     *
     * @param cardId the value for sc_resident.card_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.birthday
     *
     * @return the value of sc_resident.birthday
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.birthday
     *
     * @param birthday the value for sc_resident.birthday
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.live_type
     *
     * @return the value of sc_resident.live_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getLiveType() {
        return liveType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.live_type
     *
     * @param liveType the value for sc_resident.live_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setLiveType(Integer liveType) {
        this.liveType = liveType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.political_status
     *
     * @return the value of sc_resident.political_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.political_status
     *
     * @param politicalStatus the value for sc_resident.political_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.qualifications
     *
     * @return the value of sc_resident.qualifications
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getQualifications() {
        return qualifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.qualifications
     *
     * @param qualifications the value for sc_resident.qualifications
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setQualifications(Integer qualifications) {
        this.qualifications = qualifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.marry_status
     *
     * @return the value of sc_resident.marry_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getMarryStatus() {
        return marryStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.marry_status
     *
     * @param marryStatus the value for sc_resident.marry_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setMarryStatus(Integer marryStatus) {
        this.marryStatus = marryStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.faith
     *
     * @return the value of sc_resident.faith
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getFaith() {
        return faith;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.faith
     *
     * @param faith the value for sc_resident.faith
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setFaith(Integer faith) {
        this.faith = faith;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.work_type
     *
     * @return the value of sc_resident.work_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getWorkType() {
        return workType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.work_type
     *
     * @param workType the value for sc_resident.work_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.is_risk
     *
     * @return the value of sc_resident.is_risk
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getIsRisk() {
        return isRisk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.is_risk
     *
     * @param isRisk the value for sc_resident.is_risk
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setIsRisk(Integer isRisk) {
        this.isRisk = isRisk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.risk_type
     *
     * @return the value of sc_resident.risk_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getRiskType() {
        return riskType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.risk_type
     *
     * @param riskType the value for sc_resident.risk_type
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setRiskType(Integer riskType) {
        this.riskType = riskType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.military_status
     *
     * @return the value of sc_resident.military_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getMilitaryStatus() {
        return militaryStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.military_status
     *
     * @param militaryStatus the value for sc_resident.military_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setMilitaryStatus(Integer militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.is_settle
     *
     * @return the value of sc_resident.is_settle
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getIsSettle() {
        return isSettle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.is_settle
     *
     * @param isSettle the value for sc_resident.is_settle
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setIsSettle(Integer isSettle) {
        this.isSettle = isSettle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.birth_province_id
     *
     * @return the value of sc_resident.birth_province_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getBirthProvinceId() {
        return birthProvinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.birth_province_id
     *
     * @param birthProvinceId the value for sc_resident.birth_province_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setBirthProvinceId(Integer birthProvinceId) {
        this.birthProvinceId = birthProvinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.birth_city_id
     *
     * @return the value of sc_resident.birth_city_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getBirthCityId() {
        return birthCityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.birth_city_id
     *
     * @param birthCityId the value for sc_resident.birth_city_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setBirthCityId(Integer birthCityId) {
        this.birthCityId = birthCityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.birth_county_id
     *
     * @return the value of sc_resident.birth_county_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getBirthCountyId() {
        return birthCountyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.birth_county_id
     *
     * @param birthCountyId the value for sc_resident.birth_county_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setBirthCountyId(Integer birthCountyId) {
        this.birthCountyId = birthCountyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.settle_province_id
     *
     * @return the value of sc_resident.settle_province_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getSettleProvinceId() {
        return settleProvinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.settle_province_id
     *
     * @param settleProvinceId the value for sc_resident.settle_province_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setSettleProvinceId(Integer settleProvinceId) {
        this.settleProvinceId = settleProvinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.settle_city_id
     *
     * @return the value of sc_resident.settle_city_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getSettleCityId() {
        return settleCityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.settle_city_id
     *
     * @param settleCityId the value for sc_resident.settle_city_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setSettleCityId(Integer settleCityId) {
        this.settleCityId = settleCityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.settle_county_id
     *
     * @return the value of sc_resident.settle_county_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getSettleCountyId() {
        return settleCountyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.settle_county_id
     *
     * @param settleCountyId the value for sc_resident.settle_county_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setSettleCountyId(Integer settleCountyId) {
        this.settleCountyId = settleCountyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.settle_address
     *
     * @return the value of sc_resident.settle_address
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public String getSettleAddress() {
        return settleAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.settle_address
     *
     * @param settleAddress the value for sc_resident.settle_address
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setSettleAddress(String settleAddress) {
        this.settleAddress = settleAddress == null ? null : settleAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.register_status
     *
     * @return the value of sc_resident.register_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getRegisterStatus() {
        return registerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.register_status
     *
     * @param registerStatus the value for sc_resident.register_status
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.register_date
     *
     * @return the value of sc_resident.register_date
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.register_date
     *
     * @param registerDate the value for sc_resident.register_date
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.unregister_date
     *
     * @return the value of sc_resident.unregister_date
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Date getUnregisterDate() {
        return unregisterDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.unregister_date
     *
     * @param unregisterDate the value for sc_resident.unregister_date
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setUnregisterDate(Date unregisterDate) {
        this.unregisterDate = unregisterDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.comment
     *
     * @return the value of sc_resident.comment
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.comment
     *
     * @param comment the value for sc_resident.comment
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.create_time
     *
     * @return the value of sc_resident.create_time
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.create_time
     *
     * @param createTime the value for sc_resident.create_time
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.creator_id
     *
     * @return the value of sc_resident.creator_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.creator_id
     *
     * @param creatorId the value for sc_resident.creator_id
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_resident.modify_time
     *
     * @return the value of sc_resident.modify_time
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_resident.modify_time
     *
     * @param modifyTime the value for sc_resident.modify_time
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}