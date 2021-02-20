package com.sct.service.database.condition;

/**
 * 导出时的参数
 */
public abstract class ConditionExport<E> {
    private E condition;
    private ExportExcelCondition exportCondition;

    public abstract void checkParam(ConditionExport<E> conditions);

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
