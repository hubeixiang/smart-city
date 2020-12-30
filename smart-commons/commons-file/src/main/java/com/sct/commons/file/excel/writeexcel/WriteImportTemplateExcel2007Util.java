package com.sct.commons.file.excel.writeexcel;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2015/11/17.
 */
public class WriteImportTemplateExcel2007Util {
    // 行信息内容样式
    private Map<Integer, XSSFCellStyle> titleStyleMap = new HashMap<Integer, XSSFCellStyle>();//2007格式

    //正常的无颜色等等的样式
    private XSSFCellStyle normalXSSFCellStyle = null;

    //字体是红色的样式
    private XSSFCellStyle redFontXSSFCellStyle = null;

    /**
     * 写excel文件
     *
     * @throws IOException
     */
    public Workbook writeExcel(String sheetName, List<String> headInfo, List<HeadType> headType, List<List<String>> contextInfo) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();//2007格式

        int titleCount = headInfo.size();
        //执行样式初始化
        setExcelStyle(wb, titleCount, headType);

        XSSFSheet sheet = wb.createSheet(sheetName);//2007格式,创建第一个Sheet

        witeTitle(titleCount, sheet, headInfo);

        if (contextInfo == null || contextInfo.size() == 0) {
            return wb;
        }

        int contentCount = contextInfo.size();//总的记录数
        // 写内容
        for (int i = 0; i < contentCount; i++) {
            List<String> contents = contextInfo.get(i);
            if (i == 0 || i == 1) {
                witeNormalData(i, titleCount, sheet, contents);
            } else if (i == 2) {
                witeDataLine3(i, titleCount, sheet, contents);
            } else {
                witeNormalData(i, titleCount, sheet, contents);
            }
        }
        return wb;
    }

    private void witeTitle(int titleCount, XSSFSheet sheet, List<String> headInfo) {
        XSSFRow titleRow = sheet.createRow(0);//2007格式,创建第一行
        for (int k = 0; k < titleCount; k++) {
            XSSFCell cell = titleRow.createCell(k); //2007格式,创建一个单元格
            cell.setCellStyle(titleStyleMap.get(k));//设置标题样式
            cell.setCellType(CellType.STRING);
            cell.setCellValue(new XSSFRichTextString(headInfo.get(k))); // 为单元格赋值
            sheet.setColumnWidth(k, (short) 5000);//设置列宽
        }
        titleRow.setHeightInPoints(20);//20像素
    }

    private void witeNormalData(int line, int titleCount, XSSFSheet sheet, List<String> contents) {
        XSSFRow row = sheet.createRow((line + 1)); // //2007格式,新建一行

        for (int j = 0; j < titleCount; j++) {
            XSSFCell cell = row.createCell(j); // //2007格式,新建一个单元格

            cell.setCellStyle(normalXSSFCellStyle);//设置内容样式
            String cellValue = null;
            if (contents.size() > j) {
                cellValue = contents.get(j);
            }
            if (cellValue == null || cellValue.equals("null")) {
                cellValue = "";
            }

            cell.setCellValue(new XSSFRichTextString(cellValue));
        }
    }

    private void witeDataLine3(int line, int titleCount, XSSFSheet sheet, List<String> contents) {
        //第一个单元格使用红色字体,其余不用渲染其它的颜色
        XSSFRow row = sheet.createRow((line + 1)); // //2007格式,新建一行

        for (int j = 0; j < titleCount; j++) {
            XSSFCell cell = row.createCell(j); // //2007格式,新建一个单元格

            if (j == 0) {
                cell.setCellStyle(redFontXSSFCellStyle);//设置内容样式
            } else {
                cell.setCellStyle(normalXSSFCellStyle);//设置内容样式
            }
            String cellValue = null;
            if (contents.size() > j) {
                cellValue = contents.get(j);
            }
            if (cellValue == null || cellValue.equals("null")) {
                cellValue = "";
            }

            cell.setCellValue(new XSSFRichTextString(cellValue));
        }
    }

    private void setExcelStyle(XSSFWorkbook workBook, int headCount, List<HeadType> headTypes) {
        //标题的样式
        for (int i = 0; i < headCount; i++) {
            HeadType headType = HeadTypeUtil.getHeadType(headTypes, i);
            XSSFCellStyle xssfCellStyle = HeadTypeUtil.createTitleXSSFCellStyle(workBook, headType);
            titleStyleMap.put(i, xssfCellStyle);
        }
        //数据内容的样式
        HeadType normalHeadType = new HeadType();
        normalXSSFCellStyle = HeadTypeUtil.createDataXSSFCellStyle(workBook, normalHeadType);

        HeadType redFontHeadType = new HeadType();
        redFontHeadType.setCellStyle(HeadType.COLOR_NONE, (short) Color.RED.getRGB(), true);
        redFontXSSFCellStyle = HeadTypeUtil.createDataXSSFCellStyle(workBook, redFontHeadType);
        XSSFFont xssfTitleFont = workBook.createFont();
        xssfTitleFont.setFontName("宋体");
        xssfTitleFont.setBold(true);
        xssfTitleFont.setColor((short) Color.RED.getRGB());
        redFontXSSFCellStyle.setFont(xssfTitleFont);
    }
}
