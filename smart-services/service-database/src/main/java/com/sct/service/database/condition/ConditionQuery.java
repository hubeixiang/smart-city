package com.sct.service.database.condition;

import com.sct.commons.i18n.I18nMessageUtil;

public interface ConditionQuery {
    /**
     * 检查参数是否合法,不包含sql注入检查
     */
    default void checkParam() {
    }

    /**
     * 检查参数是否有sql注入,并抛出异常
     */
    default void checkSQLinjectionException() {
    }

    default void checkSQLinjectionException(String fragment) {
        if (checkSQLinjection(fragment)) {
            String msg = I18nMessageUtil.getInstance().getMessage("Controller.Paramter.CheckSQLinjection");
            throw new RuntimeException(msg);
        }
    }

    /**
     * 检查是否有sql注入危险
     * 主要检查在mybaits中使用${}引用的变量
     *
     * @param fragment
     * @return true:有sql注入风险
     */
    default boolean checkSQLinjection(String fragment) {
        return NonSQLinjectionUtil.checkSQLinjection(fragment);
    }
}
