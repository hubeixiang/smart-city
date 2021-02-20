package com.sct.service.database.extend;

/**
 * 拓展属性
 */
public class ScGridExtendDisplay implements ExtendEntity {
    private String communityIdExt;
    private String managerLeaderName;
    private String managerLeaderMobile;

    public String getManagerLeaderName() {
        return managerLeaderName;
    }

    public void setManagerLeaderName(String managerLeaderName) {
        this.managerLeaderName = managerLeaderName;
    }

    public String getManagerLeaderMobile() {
        return managerLeaderMobile;
    }

    public void setManagerLeaderMobile(String managerLeaderMobile) {
        this.managerLeaderMobile = managerLeaderMobile;
    }


    public String getCommunityIdExt() {
        return communityIdExt;
    }

    public void setCommunityIdExt(String communityIdExt) {
        this.communityIdExt = communityIdExt;
    }

}
