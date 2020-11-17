package com.sct.commons.file.excel.writeexcel;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2015/11/17.
 */
public class WriteExcel2007Util {
    // 行信息内容样式
    private Map<Integer, XSSFCellStyle> titleStyleMap = new HashMap<Integer, XSSFCellStyle>();//2007格式

    // 特殊的日期字段类型的内容样式
    private Map<Integer, XSSFCellStyle> contentStyleMap = new HashMap<Integer, XSSFCellStyle>();//2007格式

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

        XSSFRow titleRow = sheet.createRow(0);//2007格式,创建第一行

        titleRow.setHeightInPoints(20);//20像素
        // 写标题
        for (int k = 0; k < titleCount; k++) {
            XSSFCell cell = titleRow.createCell(k); //2007格式,创建一个单元格

            cell.setCellStyle(titleStyleMap.get(k));//设置标题样式
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new XSSFRichTextString(headInfo.get(k))); // 为单元格赋值
            sheet.setColumnWidth(k, (short) 5000);//设置列宽
        }
        if (contextInfo == null || contextInfo.size() == 0) {
            return wb;
        }

        int contentCount = contextInfo.size();//总的记录数
        // 写内容
        for (int i = 0; i < contentCount; i++) {
            List<String> contents = contextInfo.get(i);
            XSSFRow row = sheet.createRow((i + 1)); // //2007格式,新建一行

            for (int j = 0; j < titleCount; j++) {
                XSSFCell cell = row.createCell(j); // //2007格式,新建一个单元格

                cell.setCellStyle(contentStyleMap.get(j));//设置内容样式
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
        return wb;
    }

    private void setExcelStyle(XSSFWorkbook workBook, int headCount, List<HeadType> headTypes) {
        //标题的样式
        for (int i = 0; i < headCount; i++) {
            HeadType headType = HeadTypeUtil.getHeadType(headTypes, i);
            XSSFCellStyle xssfCellStyle = HeadTypeUtil.createTitleXSSFCellStyle(workBook, headType);
            titleStyleMap.put(i, xssfCellStyle);
        }
        //数据内容的样式
        for (int i = 0; i < headCount; i++) {
            HeadType headType = HeadTypeUtil.getHeadType(headTypes, i);
            XSSFCellStyle xssfCellStyle = HeadTypeUtil.createDataXSSFCellStyle(workBook, headType);
            contentStyleMap.put(i, xssfCellStyle);
        }
    }
}
