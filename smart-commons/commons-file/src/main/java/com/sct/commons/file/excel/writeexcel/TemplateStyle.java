package com.sct.commons.file.excel.writeexcel;

import java.util.HashMap;
import java.util.Map;

public class TemplateStyle {
    private String templateExcelName = "";
    private Map<String, HeadType> excelCellStyleMap = new HashMap<String, HeadType>();

    public String getTemplateExcelName() {
        return templateExcelName;
    }

    public void setTemplateExcelName(String templateExcelName) {
        this.templateExcelName = templateExcelName;
    }

    public void clear() {
        excelCellStyleMap.clear();
    }

    public void addHeadType(String fieldName, HeadType headType) {
        excelCellStyleMap.put(fieldName, headType);
    }

    public HeadType getHeadType(String fieldName) {
        return excelCellStyleMap.get(fieldName);
    }
}
