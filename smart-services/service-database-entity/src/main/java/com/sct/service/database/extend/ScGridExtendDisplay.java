package com.sct.service.database.extend;

/**
 * 拓展属性
 */
public class ScGridExtendDisplay implements ExtendEntity {
    private String communityIdExt;
    private String managerLeaderName;
    private String managerLeaderMobile;
    private Integer households;
    private Integer population;

    public Integer getHouseholds() {
        return households;
    }

    public void setHouseholds(Integer households) {
        this.households = households;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

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
