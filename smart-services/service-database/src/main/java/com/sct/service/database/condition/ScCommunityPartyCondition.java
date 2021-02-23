package com.sct.service.database.condition;

public class ScCommunityPartyCondition implements ConditionQuery {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ScCommunityPartyCondition of() {
        return new ScCommunityPartyCondition();
    }
}
