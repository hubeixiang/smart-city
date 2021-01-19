package com.sct.service.database.condition;

import org.springframework.util.CollectionUtils;

public final class ExportExcelConditionUtil {
    public static boolean filterFieldMapping(ExportExcelCondition fieldMappingCondition) {
        if (fieldMappingCondition != null && !CollectionUtils.isEmpty(fieldMappingCondition.getMappings())) {
            return true;
        }
        return false;
    }
}
