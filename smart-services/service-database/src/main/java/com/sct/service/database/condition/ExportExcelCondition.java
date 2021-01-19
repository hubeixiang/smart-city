package com.sct.service.database.condition;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class ExportExcelCondition {
    //列头映射
    private List<FieldMapping> mappings = new ArrayList<>();
    //是否需要压缩为zip
    private boolean isZip = false;
    //生成的excel 的sheet名称
    private String sheetName;
    //生成的excel文件,数据太多时是否自动拆分生成多个sheet
    private boolean isMultiSheet = false;

    public static void checkParam(ExportExcelCondition exportCondition) {
        Assert.notNull(exportCondition.getSheetName(), "Require sheetName");
        Assert.isTrue(ExportExcelConditionUtil.filterFieldMapping(exportCondition), "Require export mappings");
    }

    public List<FieldMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<FieldMapping> mappings) {
        this.mappings = mappings;
    }

    public boolean isZip() {
        return isZip;
    }

    public void setZip(boolean zip) {
        isZip = zip;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public boolean isMultiSheet() {
        return isMultiSheet;
    }

    public void setMultiSheet(boolean multiSheet) {
        isMultiSheet = multiSheet;
    }
}
