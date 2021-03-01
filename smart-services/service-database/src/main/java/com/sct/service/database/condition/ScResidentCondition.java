package com.sct.service.database.condition;

public class ScResidentCondition implements ConditionQuery {
    private Integer communityId;
    private Integer gridId;
    private Integer liveType;
    private Integer politicalStatus;
    private Integer registerStatus;
    private String name;
    private String cardId;
    private Integer houseId;
/*    private Integer sex;
    private String mobile;
    private Integer birthProvinceId;
    private Integer birthCityId;
    private Integer birthCountyId;
    private Integer settleProvinceId;
    private Integer settleCityId;
    private Integer settleCountyId;*/


    public static ScResidentCondition of() {
        return new ScResidentCondition();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    public Integer getLiveType() {
        return liveType;
    }

    public void setLiveType(Integer liveType) {
        this.liveType = liveType;
    }

    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    /*public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getBirthProvinceId() {
        return birthProvinceId;
    }

    public void setBirthProvinceId(Integer birthProvinceId) {
        this.birthProvinceId = birthProvinceId;
    }

    public Integer getBirthCityId() {
        return birthCityId;
    }

    public void setBirthCityId(Integer birthCityId) {
        this.birthCityId = birthCityId;
    }

    public Integer getBirthCountyId() {
        return birthCountyId;
    }

    public void setBirthCountyId(Integer birthCountyId) {
        this.birthCountyId = birthCountyId;
    }

    public Integer getSettleProvinceId() {
        return settleProvinceId;
    }

    public void setSettleProvinceId(Integer settleProvinceId) {
        this.settleProvinceId = settleProvinceId;
    }

    public Integer getSettleCityId() {
        return settleCityId;
    }

    public void setSettleCityId(Integer settleCityId) {
        this.settleCityId = settleCityId;
    }

    public Integer getSettleCountyId() {
        return settleCountyId;
    }

    public void setSettleCountyId(Integer settleCountyId) {
        this.settleCountyId = settleCountyId;
    }*/
}
