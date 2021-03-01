package com.sct.service.database.extend;

/**
 * 拓展属性
 */
public class ScGridManagerExtendDisplay extends BaseExtend implements ExtendEntity {
    private String communityIdExt;
    private String gridIdExt;
    private String gridNo;

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

    public String getGridNo() {
        return gridNo;
    }

    public void setGridNo(String gridNo) {
        this.gridNo = gridNo;
    }
}
