package com.sct.service.database.extend;

/**
 * 拓展属性
 */
public class ScGridHouseExtendDisplay extends BaseExtend implements ExtendEntity {
    private Integer communityId;
    private String communityIdExt;
    private Integer gridId;
    private String gridIdExt;
    private String gridNo;
    private Integer estateId;
    private String estateIdExt;
    private String buildingIdExt;
    private String ownerIdExt;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityIdExt() {
        return communityIdExt;
    }

    public void setCommunityIdExt(String communityIdExt) {
        this.communityIdExt = communityIdExt;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    public String getGridIdExt() {
        return gridIdExt;
    }

    public void setGridIdExt(String gridIdExt) {
        this.gridIdExt = gridIdExt;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getEstateIdExt() {
        return estateIdExt;
    }

    public void setEstateIdExt(String estateIdExt) {
        this.estateIdExt = estateIdExt;
    }

    public String getBuildingIdExt() {
        return buildingIdExt;
    }

    public void setBuildingIdExt(String buildingIdExt) {
        this.buildingIdExt = buildingIdExt;
    }

    public String getGridNo() {
        return gridNo;
    }

    public void setGridNo(String gridNo) {
        this.gridNo = gridNo;
    }

    public String getOwnerIdExt() {
        return ownerIdExt;
    }

    public void setOwnerIdExt(String ownerIdExt) {
        this.ownerIdExt = ownerIdExt;
    }
}
