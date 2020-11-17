package com.sct.commons.file.excel.writeexcel;

public class HeadType {
    public final static short COLOR_NONE = -1;
    public final static String CELL_DATATYPE_STRING = "string";
    public final static String CELL_DATATYPE_DATE_STRING = "datestring";
    //datestring:表明是时间字符串.其它的都在excel的单元格中按照string写入
    private String cellType;
    //列头是否需要渲染背景色
    private boolean hasBackgroundColor = false;
    //是否需要设置字体颜色
    private boolean hasFontColor = false;
    //背景色
    private short fillForegroundColor;
    private short fillFontColor;
    //列头的颜色是否影响表格中数据的渲染
    private boolean colorRenderData = false;

    public HeadType() {
        this.cellType = HeadType.CELL_DATATYPE_STRING;
    }

    public HeadType(String cellType) {
        this.cellType = cellType;
    }

    public void setCellStyle(short foregroundColor, short fontColor) {
        setCellStyle(foregroundColor, fontColor, false);
    }

    public void setCellStyle(short foregroundColor, short fontColor, boolean renderData) {
        if (foregroundColor != HeadType.COLOR_NONE) {
            this.hasBackgroundColor = true;
            this.fillForegroundColor = foregroundColor;
        }
        if (fontColor != HeadType.COLOR_NONE) {
            this.hasFontColor = true;
            this.fillFontColor = fontColor;
        }
        if (this.hasBackgroundColor || this.hasFontColor) {
            this.colorRenderData = renderData;
        }
    }

    public String getCellType() {
        return cellType;
    }

    public boolean isHasBackgroundColor() {
        return hasBackgroundColor;
    }

    public short getFillForegroundColor() {
        return fillForegroundColor;
    }

    public boolean isHasFontColor() {
        return hasFontColor;
    }

    public short getFillFontColor() {
        return fillFontColor;
    }

    public boolean isColorRenderData() {
        return colorRenderData;
    }
}
