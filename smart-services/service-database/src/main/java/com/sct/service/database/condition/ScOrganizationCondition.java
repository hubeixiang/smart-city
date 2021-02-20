package com.sct.service.database.condition;

import java.util.List;

public class ScOrganizationCondition implements ConditionQuery {
    private List<Integer> communityIds;
    private List<Integer> gridIds;
    private Integer orgType;
    private String name;
    private List<Integer> registerStatusList;

    public void checkSQLinjectionException() {
        checkSQLinjectionException(getName());
    }

    public List<Integer> getCommunityIds() {
        return communityIds;
    }

    public void setCommunityIds(List<Integer> communityIds) {
        this.communityIds = communityIds;
    }

    public List<Integer> getGridIds() {
        return gridIds;
    }

    public void setGridIds(List<Integer> gridIds) {
        this.gridIds = gridIds;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRegisterStatusList() {
        return registerStatusList;
    }

    public void setRegisterStatusList(List<Integer> registerStatusList) {
        this.registerStatusList = registerStatusList;
    }
}
