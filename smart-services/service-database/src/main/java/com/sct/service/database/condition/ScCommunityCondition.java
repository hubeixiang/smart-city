package com.sct.service.database.condition;

public class ScCommunityCondition implements ConditionQuery {
    public static ScCommunityCondition of() {
        return new ScCommunityCondition();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
