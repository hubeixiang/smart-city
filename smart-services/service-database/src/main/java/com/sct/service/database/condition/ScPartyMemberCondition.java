package com.sct.service.database.condition;

import java.util.List;

public class ScPartyMemberCondition implements ConditionQuery {
    private Integer id;
    private String name;
    private String cardId;
    private Integer sex;
    private Integer partyId;
    private Integer communityId;
    private List<Integer> politicalStatusList;
    private List<Integer> partyMemberTypeList;

    public static ScPartyMemberCondition of() {
        return new ScPartyMemberCondition();
    }

    public void checkSQLinjectionException() {
        //名字注意注入检测
        checkSQLinjectionException(getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public List<Integer> getPoliticalStatusList() {
        return politicalStatusList;
    }

    public void setPoliticalStatusList(List<Integer> politicalStatusList) {
        this.politicalStatusList = politicalStatusList;
    }

    public List<Integer> getPartyMemberTypeList() {
        return partyMemberTypeList;
    }

    public void setPartyMemberTypeList(List<Integer> partyMemberTypeList) {
        this.partyMemberTypeList = partyMemberTypeList;
    }
}
