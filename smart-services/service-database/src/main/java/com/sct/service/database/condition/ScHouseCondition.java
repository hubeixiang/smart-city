package com.sct.service.database.condition;

public class ScHouseCondition implements ConditionQuery {

    private Integer communityId;
    private Integer gridId;
    private String ownerIdExt;
    private String buildingIdExt;
    private String unit;
    private Integer houseStatus;
    private Integer houseAttribute;
    private Integer roomNumer;


    public static ScHouseCondition of() {
        return new ScHouseCondition();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    public String getBuildingIdExt() {
        return buildingIdExt;
    }

    public void setBuildingIdExt(String buildingIdExt) {
        this.buildingIdExt = buildingIdExt;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOwnerIdExt() {
        return ownerIdExt;
    }

    public void setOwnerIdExt(String ownerIdExt) {
        this.ownerIdExt = ownerIdExt;
    }

    public Integer getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Integer houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Integer getHouseAttribute() {
        return houseAttribute;
    }

    public void setHouseAttribute(Integer houseAttribute) {
        this.houseAttribute = houseAttribute;
    }

    public Integer getRoomNumer() {
        return roomNumer;
    }

    public void setRoomNumer(Integer roomNumer) {
        this.roomNumer = roomNumer;
    }
}
