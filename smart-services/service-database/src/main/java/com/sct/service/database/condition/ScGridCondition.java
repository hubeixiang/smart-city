package com.sct.service.database.condition;

import com.sct.commons.i18n.I18nMessageUtil;

import java.util.List;

public class ScGridCondition {
    private Integer communityId;
    private String name;

    public static void checkSQLinjectionException(ScGridCondition condition) {
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
    public static boolean checkSQLinjection(ScGridCondition condition) {
        return NonSQLinjectionUtil.checkSQLinjection(condition.getName());
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityIds(Integer communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
