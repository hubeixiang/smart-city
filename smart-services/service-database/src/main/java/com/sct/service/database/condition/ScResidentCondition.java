package com.sct.service.database.condition;

public class ScResidentCondition {
    private String name;
    private int sex;
    private String mobile;
    private String cardId;
    private int birthProvinceId;
    private int birthCityId;
    private int birthCountyId;
    private int settleProvinceId;
    private int settleCityId;
    private int settleCountyId;

    public static ScResidentCondition of() {
        return new ScResidentCondition();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getBirthProvinceId() {
        return birthProvinceId;
    }

    public void setBirthProvinceId(int birthProvinceId) {
        this.birthProvinceId = birthProvinceId;
    }

    public int getBirthCityId() {
        return birthCityId;
    }

    public void setBirthCityId(int birthCityId) {
        this.birthCityId = birthCityId;
    }

    public int getBirthCountyId() {
        return birthCountyId;
    }

    public void setBirthCountyId(int birthCountyId) {
        this.birthCountyId = birthCountyId;
    }

    public int getSettleProvinceId() {
        return settleProvinceId;
    }

    public void setSettleProvinceId(int settleProvinceId) {
        this.settleProvinceId = settleProvinceId;
    }

    public int getSettleCityId() {
        return settleCityId;
    }

    public void setSettleCityId(int settleCityId) {
        this.settleCityId = settleCityId;
    }

    public int getSettleCountyId() {
        return settleCountyId;
    }

    public void setSettleCountyId(int settleCountyId) {
        this.settleCountyId = settleCountyId;
    }
}
