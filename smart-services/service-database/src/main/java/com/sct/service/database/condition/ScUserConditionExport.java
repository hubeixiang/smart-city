package com.sct.service.database.condition;

/**
 * 导出时的参数
 */
public class ScUserConditionExport extends ConditionExport<ScUserCondition> {
    @Override
    public void checkParam(ConditionExport<ScUserCondition> conditions) {
        ScUserCondition.checkSQLinjectionException(conditions.getCondition());
        ExportExcelCondition.checkParam(conditions.getExportCondition());
    }
}
