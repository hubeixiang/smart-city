package com.sct.service.database.condition;

import org.apache.commons.lang3.StringUtils;

public class NonSQLinjectionUtil {
    /**
     * @param condition 检查的条件
     * @return true:有sql注入风险
     */
    public static boolean checkSQLinjection(String condition) {
        if (StringUtils.isNotEmpty(condition)) {
            int index = condition.toLowerCase().indexOf("select ");
            if (index != -1) {
                return true;
            }
        }
        return false;
    }
}
