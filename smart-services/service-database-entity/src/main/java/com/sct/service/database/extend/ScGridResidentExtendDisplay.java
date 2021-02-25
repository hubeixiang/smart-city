package com.sct.service.database.extend;

/**
 * 居民人口拓展属性
 */
public class ScGridResidentExtendDisplay implements ExtendEntity {
    private Integer communityId;
    private String communityIdExt;
    private Integer gridId;
    private String gridIdExt;
    private Integer estateId;
    private String estateIdExt;
    private Integer buildingId;
    private String buildingIdExt;

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

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingIdExt() {
        return buildingIdExt;
    }

    public void setBuildingIdExt(String buildingIdExt) {
        this.buildingIdExt = buildingIdExt;
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
}
