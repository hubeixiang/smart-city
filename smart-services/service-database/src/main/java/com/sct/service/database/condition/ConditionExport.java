package com.sct.service.database.condition;

import org.springframework.util.Assert;

/**
 * 导出时的参数
 */
public abstract class ConditionExport<E extends ConditionQuery> {
    private E condition;
    private ExportExcelCondition exportCondition;

    public void checkParam() {
        Assert.notNull(condition, "Require Query condition");
        Assert.notNull(exportCondition, "Require Export Column header field");
        ((E) condition).checkParam();
        ((E) condition).checkSQLinjectionException();
        ExportExcelCondition.checkParam(exportCondition);
    }

    public E getCondition() {
        return condition;
    }

    public void setCondition(E condition) {
        this.condition = condition;
    }

    public ExportExcelCondition getExportCondition() {
        return exportCondition;
    }

    public void setExportCondition(ExportExcelCondition exportCondition) {
        this.exportCondition = exportCondition;
    }
}
