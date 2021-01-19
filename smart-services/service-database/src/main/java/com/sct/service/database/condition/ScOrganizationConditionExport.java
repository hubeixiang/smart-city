package com.sct.service.database.condition;

/**
 * 导出时的参数
 */
public class ScOrganizationConditionExport {
    private ScOrganizationCondition condition;
    private ExportExcelCondition exportCondition;

    public static void checkParam(ScOrganizationConditionExport conditions) {
        ScOrganizationCondition.checkSQLinjectionException(conditions.getCondition());
        ExportExcelCondition.checkParam(conditions.getExportCondition());
    }

    public ScOrganizationCondition getCondition() {
        return condition;
    }

    public void setCondition(ScOrganizationCondition condition) {
        this.condition = condition;
    }

    public ExportExcelCondition getExportCondition() {
        return exportCondition;
    }

    public void setExportCondition(ExportExcelCondition exportCondition) {
        this.exportCondition = exportCondition;
    }
}
