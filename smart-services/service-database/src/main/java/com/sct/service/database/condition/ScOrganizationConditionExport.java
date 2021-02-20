package com.sct.service.database.condition;

/**
 * 导出时的参数
 */
public class ScOrganizationConditionExport extends ConditionExport<ScOrganizationCondition> {
    @Override
    public void checkParam(ConditionExport<ScOrganizationCondition> conditions) {
        ScOrganizationCondition.checkSQLinjectionException(conditions.getCondition());
        ExportExcelCondition.checkParam(conditions.getExportCondition());
    }
}
