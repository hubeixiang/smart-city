package com.sct.service.database.condition;

import com.sct.service.database.entity.ScLog;

public class ScLogConditionExport extends ScLog {
    private ScLogCondition condition;
    private ExportExcelCondition exportCondition;

    public static void checkParam(ScLogConditionExport conditions) {
        ScLogCondition.checkParam(conditions.getCondition());
        ExportExcelCondition.checkParam(conditions.getExportCondition());
    }

    public ScLogCondition getCondition() {
        return condition;
    }

    public void setCondition(ScLogCondition condition) {
        this.condition = condition;
    }

    public ExportExcelCondition getExportCondition() {
        return exportCondition;
    }

    public void setExportCondition(ExportExcelCondition exportCondition) {
        this.exportCondition = exportCondition;
    }
}

