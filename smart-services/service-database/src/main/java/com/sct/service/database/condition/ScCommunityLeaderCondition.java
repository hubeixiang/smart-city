package com.sct.service.database.condition;

public class ScCommunityLeaderCondition implements ConditionQuery {
    public static ScCommunityLeaderCondition of() {
        return new ScCommunityLeaderCondition();
    }
    private String name;
    private Integer officeStatues;
    private Integer communityId;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOfficeStatues() {
        return officeStatues;
    }

    public void setOfficeStatues(Integer officeStatues) {
        this.officeStatues = officeStatues;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}
