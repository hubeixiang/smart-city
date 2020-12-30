package com.sct.commons.excel.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Excel文件包装类
 */
public class ExcelFileContextWrapper extends FileContextWrapper {
    protected List<SheetWrapper> sheets;

    public ExcelFileContextWrapper() {
    }

    public ExcelFileContextWrapper(String name) {
        super(name);
    }

    public ExcelFileContextWrapper(String name, InputStream input) {
        super(name, input);
    }

    public ExcelFileContextWrapper(String name, ByteArrayOutputStream output) {
        super(name, output);
    }

    public static ExcelFileContextWrapper of() {
        return new ExcelFileContextWrapper();
    }

    public static ExcelFileContextWrapper of(String name) {
        ExcelFileContextWrapper excelFileContextWrapper = of();
        excelFileContextWrapper.setName(name);
        return excelFileContextWrapper;
    }

    public static ExcelFileContextWrapper of(FileContextWrapper fileContextWrapper) {
        ExcelFileContextWrapper excelFileContextWrapper = of();
        excelFileContextWrapper.setName(fileContextWrapper.getName());
        excelFileContextWrapper.setInput(fileContextWrapper.getInput());
        excelFileContextWrapper.setOutput(fileContextWrapper.getOutput());
        return excelFileContextWrapper;
    }

    public List<SheetWrapper> getSheets() {
        return sheets;
    }

    public void setSheets(List<SheetWrapper> sheets) {
        this.sheets = sheets;
    }
}
