package com.sct.service.database.extend;

/**
 * 社会组织信息表需要转换映射的属性
 */
public class ScOrganizationExtendDisplay implements ExtendEntity {
    private String orgTypeExt;
    private String communityIdExt;
    private String gridIdExt;
    private String registerStatusExt;
    private String markerStatusExt;

    public String getOrgTypeExt() {
        return orgTypeExt;
    }

    public void setOrgTypeExt(String orgTypeExt) {
        this.orgTypeExt = orgTypeExt;
    }

    public String getCommunityIdExt() {
        return communityIdExt;
    }

    public void setCommunityIdExt(String communityIdExt) {
        this.communityIdExt = communityIdExt;
    }

    public String getGridIdExt() {
        return gridIdExt;
    }

    public void setGridIdExt(String gridIdExt) {
        this.gridIdExt = gridIdExt;
    }

    public String getRegisterStatusExt() {
        return registerStatusExt;
    }

    public void setRegisterStatusExt(String registerStatusExt) {
        this.registerStatusExt = registerStatusExt;
    }

    public String getMarkerStatusExt() {
        return markerStatusExt;
    }

    public void setMarkerStatusExt(String markerStatusExt) {
        this.markerStatusExt = markerStatusExt;
    }
}
