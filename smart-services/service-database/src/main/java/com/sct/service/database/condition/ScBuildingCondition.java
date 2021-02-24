package com.sct.service.database.condition;

public class ScBuildingCondition implements ConditionQuery {

    private Integer communityId;
    private Integer gridId;
    private String name;
    private Integer buildingType;

    public static ScBuildingCondition of() {
        return new ScBuildingCondition();
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

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }


    public Integer getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Integer buildingType) {
        this.buildingType = buildingType;
    }
}
