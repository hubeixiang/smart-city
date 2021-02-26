package com.sct.service.database.extend;

/**
 * 拓展属性
 */
public class ScGridBuildingExtendDisplay extends BaseExtend implements ExtendEntity {
    private String communityIdExt;
    private String gridIdExt;

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
}
