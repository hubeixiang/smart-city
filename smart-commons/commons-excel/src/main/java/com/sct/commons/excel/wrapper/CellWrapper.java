package com.sct.commons.excel.wrapper;


import java.util.List;

/**
 * 单元格包装类
 * <br>包含单元格值、合并单元格范围等
 */
public class CellWrapper {
    private String text;        // 单元格值
    private int rowIndex;       // 行号
    private int firstRow;       // 合并开始行
    private int lastRow;        // 合并结束行
    private int firstColumn;    // 合并开始列
    private int lastColumn;     // 合并结束列

    private CellWrapper parent;             // 父单元格
    private List<CellWrapper> children;     // 子单元格

    public CellWrapper() {
    }

    public CellWrapper(String text) {
        this();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(int firstColumn) {
        this.firstColumn = firstColumn;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(int lastColumn) {
        this.lastColumn = lastColumn;
    }

    public CellWrapper getParent() {
        return parent;
    }

    public void setParent(CellWrapper parent) {
        this.parent = parent;
    }

    public List<CellWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<CellWrapper> children) {
        this.children = children;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public int getChildrenSize() {
        return children != null ? children.size() : 0;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        try {
            buf.append("[").append(text);
            buf.append(",index=" + rowIndex);
            buf.append(",row=" + firstRow + "/" + lastRow);
            buf.append(",column=" + firstColumn + "/" + lastColumn);
            if (hasChildren()) {
                buf.append(",children=" + getChildrenSize());
            }
            buf.append("]");
            return buf.toString();
        } finally {
            buf.setLength(0);
        }
    }

}
