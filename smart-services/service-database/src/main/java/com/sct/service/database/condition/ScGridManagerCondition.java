package com.sct.service.database.condition;

public class ScGridManagerCondition implements ConditionQuery {
    public static ScGridManagerCondition of() {
        return new ScGridManagerCondition();
    }

    private String name;
    private Integer gridId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }
}
