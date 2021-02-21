package com.sct.service.database.condition;

public class ScEstateCondition implements ConditionQuery {

    private Integer communityId;
    private String name;

    public static ScEstateCondition of() {
        return new ScEstateCondition();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityIds(Integer communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
