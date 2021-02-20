package com.sct.service.database.condition;

import java.util.List;

public class ScRoleCondition implements ConditionQuery {
    private String roleName;
    private Integer roleType;
    private List<Integer> gridLevels;
    private List<Integer> communityIds;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Integer> getGridLevels() {
        return gridLevels;
    }

    public void setGridLevels(List<Integer> gridLevels) {
        this.gridLevels = gridLevels;
    }

    public List<Integer> getCommunityIds() {
        return communityIds;
    }

    public void setCommunityIds(List<Integer> communityIds) {
        this.communityIds = communityIds;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
