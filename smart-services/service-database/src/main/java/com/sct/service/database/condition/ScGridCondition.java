package com.sct.service.database.condition;

public class ScGridCondition implements ConditionQuery {

    private Integer communityId;
    private String name;

    public static ScGridCondition of() {
        return new ScGridCondition();
    }


    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
