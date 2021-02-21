package com.sct.service.database.condition;

public class ScPropertyStaffCondition implements ConditionQuery {

    private Integer staffType;
    private String name;

    public static ScPropertyStaffCondition of() {
        return new ScPropertyStaffCondition();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStaffType() {
        return staffType;
    }

    public void setStaffType(Integer staffType) {
        this.staffType = staffType;
    }
}
