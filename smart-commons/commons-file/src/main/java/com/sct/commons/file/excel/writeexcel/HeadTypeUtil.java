package com.sct.commons.file.excel.writeexcel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class HeadTypeUtil {
    public static XSSFCellStyle createTitleXSSFCellStyle(XSSFWorkbook workBook, HeadType headType) {
        XSSFFont xssfTitleFont = workBook.createFont();
        xssfTitleFont.setFontName("宋体");
        xssfTitleFont.setBold(true);
        // 标题列样式
        XSSFCellStyle xssfTitleStyle = workBook.createCellStyle();
        xssfTitleStyle.setBorderTop(BorderStyle.THIN);
        xssfTitleStyle.setBorderBottom(BorderStyle.THIN);
        xssfTitleStyle.setBorderLeft(BorderStyle.THIN);
        xssfTitleStyle.setBorderRight(BorderStyle.THIN);
        xssfTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        xssfTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        if (headType != null) {
            if (headType.isHasFontColor()) {
                xssfTitleFont.setColor(headType.getFillFontColor());
            }
            if (headType.isHasBackgroundColor()) {
                xssfTitleStyle.setFillForegroundColor(headType.getFillForegroundColor());
                xssfTitleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
        }
        xssfTitleStyle.setFont(xssfTitleFont);
        return xssfTitleStyle;
    }

    public static HSSFCellStyle createTitleHSSFCellStyle(HSSFWorkbook workBook, HeadType headType) {
        HSSFFont hssfTitleFont = workBook.createFont();
        hssfTitleFont.setFontName("宋体");
        hssfTitleFont.setBold(true);
        // 标题列样式
        HSSFCellStyle hssfTitleStyle = workBook.createCellStyle();
        hssfTitleStyle.setBorderTop(BorderStyle.THIN);
        hssfTitleStyle.setBorderBottom(BorderStyle.THIN);
        hssfTitleStyle.setBorderLeft(BorderStyle.THIN);
        hssfTitleStyle.setBorderRight(BorderStyle.THIN);
        hssfTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        hssfTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        hssfTitleStyle.setFont(hssfTitleFont);
        if (headType != null) {
            if (headType.isHasFontColor()) {
                hssfTitleFont.setColor(headType.getFillFontColor());
            }
            if (headType.isHasBackgroundColor()) {
                hssfTitleStyle.setFillForegroundColor(headType.getFillForegroundColor());
                hssfTitleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
        }
        hssfTitleStyle.setFont(hssfTitleFont);
        return hssfTitleStyle;
    }

    public static XSSFCellStyle createDataXSSFCellStyle(XSSFWorkbook workBook, HeadType headType) {
        XSSFCellStyle contentStyle = workBook.createCellStyle();
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        if (headType != null && headType.isColorRenderData()) {
            if (headType.isHasFontColor()) {
                XSSFFont xssfTitleFont = workBook.createFont();
                xssfTitleFont.setColor(headType.getFillFontColor());
                contentStyle.setFont(xssfTitleFont);
            }
            if (headType.isHasBackgroundColor()) {
                contentStyle.setFillForegroundColor(headType.getFillForegroundColor());
                contentStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            if (HeadType.CELL_DATATYPE_DATE_STRING.equalsIgnoreCase(headType.getCellType())) {
                contentStyle.setDataFormat(workBook.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
            }
        }
        return contentStyle;
    }

    public static HSSFCellStyle createDataHSSFCellStyle(HSSFWorkbook workBook, HeadType headType) {
        HSSFCellStyle contentStyle = workBook.createCellStyle();
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        if (headType != null && headType.isColorRenderData()) {
            if (headType.isHasFontColor()) {
                HSSFFont hssfTitleFont = workBook.createFont();
                hssfTitleFont.setColor(headType.getFillFontColor());
                contentStyle.setFont(hssfTitleFont);
            }
            if (headType.isHasBackgroundColor()) {
                contentStyle.setFillForegroundColor(headType.getFillForegroundColor());
                contentStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            if (HeadType.CELL_DATATYPE_DATE_STRING.equalsIgnoreCase(headType.getCellType())) {
                contentStyle.setDataFormat(workBook.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
            }
        }
        return contentStyle;
    }

    public static HeadType getHeadType(List<HeadType> headTypes, int index) {
        if (headTypes != null && headTypes.size() > index) {
            return headTypes.get(index);
        } else {
            return null;
        }
    }
}
