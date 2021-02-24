package com.sct.service.database.condition;

public class ScCommunityPartyCondition implements ConditionQuery {
    public static ScCommunityPartyCondition of() {
        return new ScCommunityPartyCondition();
    }

    private String name;
    private Integer communityId;
    private Integer partyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getPartyType() {
        return partyType;
    }

    public void setPartyType(Integer partyType) {
        this.partyType = partyType;
    }
}
