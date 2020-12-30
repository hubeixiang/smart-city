package com.sct.commons.excel.wrapper;


import java.util.Arrays;
import java.util.List;

/**
 * 表格Sheet封装
 */
public class SheetWrapper {
    private static final String DEFAULT_SHEET_NAME = "Sheet1";
    private String name;                // Sheet名称
    private String[] headers;            // 简单表头(单行)
    private List<CellWrapper> complexHeaders;    // 复杂表头(多行合并单元格)
    private List<String[]> contents;            // 表格内容

    private boolean mergedHeader;        // 是否合并表头

    private CellWrapper[][] mergedCells;        // 合并单元格

    public SheetWrapper() {
        this(DEFAULT_SHEET_NAME);
    }

    public SheetWrapper(String name) {
        this.name = name;
        this.mergedHeader = false;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        try {
            buf.append("Sheet[").append(name).append("]");
            buf.append("[headers=").append(headers == null ? "" : Arrays.toString(headers));
            buf.append(",complexHeaders=").append(complexHeaders != null ? complexHeaders : "");
            buf.append(",merged=").append(mergedHeader);
            buf.append(",contents.size=").append(contents == null ? 0 : contents.size());
            buf.append("]");
            return buf.toString();
        } finally {
            buf.setLength(0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<CellWrapper> getComplexHeaders() {
        return complexHeaders;
    }

    public void setComplexHeaders(List<CellWrapper> complexHeaders) {
        this.complexHeaders = complexHeaders;
    }

    public List<String[]> getContents() {
        return contents;
    }

    public void setContents(List<String[]> contents) {
        this.contents = contents;
    }

    public boolean isMergedHeader() {
        return mergedHeader;
    }

    public void setMergedHeader(boolean mergedHeader) {
        this.mergedHeader = mergedHeader;
    }

    public CellWrapper[][] getMergedCells() {
        return mergedCells;
    }

    public void setMergedCells(CellWrapper[][] mergedCells) {
        this.mergedCells = mergedCells;
    }
}
