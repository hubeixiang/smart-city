package com.sct.service.database.condition;

import com.sct.commons.i18n.I18nMessageUtil;

import java.util.List;

public class ScOrganizationCondition {
    private List<Integer> communityIds;
    private List<Integer> gridIds;
    private Integer orgType;
    private String name;
    private List<Integer> registerStatusList;

    public static void checkSQLinjectionException(ScOrganizationCondition condition) {
        if (checkSQLinjection(condition)) {
            String msg = I18nMessageUtil.getInstance().getMessage("Controller.Paramter.CheckSQLinjection");
            throw new RuntimeException(msg);
        }
    }

    /**
     * 检查是否有sql注入危险
     * 主要检查在mybaits中使用${}引用的变量
     *
     * @param condition
     * @return true:有sql注入风险
     */
    public static boolean checkSQLinjection(ScOrganizationCondition condition) {
        return NonSQLinjectionUtil.checkSQLinjection(condition.getName());
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
