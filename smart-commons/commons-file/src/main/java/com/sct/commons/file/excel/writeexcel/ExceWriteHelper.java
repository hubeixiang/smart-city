package com.sct.commons.file.excel.writeexcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sven
 * @date 2019/4/19 14:48
 */
public class ExceWriteHelper {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String exportFilePath = "D:\\01xStaff\\exce_test_tmplate";
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("2");
        data.add("2");
        data.add("2");
        data.add("2");
        data.add("2");
        data.add("2");
        data.add("3");
        process2003(exportFilePath, data);
        process2007(exportFilePath, data);
    }

    public static void process2003(String exportFilePath, List<?> data) throws IOException {
        exportFilePath = exportFilePath + ".xls";
        addExcel2003(exportFilePath, data);
    }

    public static void process2007(String exportFilePath, List<?> data) throws IOException, InvalidFormatException {
        exportFilePath = exportFilePath + ".xlsx";
        addExcel2007(exportFilePath, data);
    }

    public static void addExcel2003(String excelPath, List<?> list) throws IOException {
        FileInputStream fs = new FileInputStream(excelPath);//获取excel
        POIFSFileSystem ps = new POIFSFileSystem(fs);//获取excel信息
        HSSFWorkbook wb = new HSSFWorkbook(ps);
        HSSFSheet sheet = wb.getSheetAt(0);//获取工作表
        HSSFRow row = sheet.getRow(0);//获取第一行(即:字段列头,便于赋值)
        System.out.println("2003:" + sheet.getLastRowNum() + "空" + row.getLastCellNum());//分别得到最后一行行号,和一条记录的最后一个单元格
        FileOutputStream out = new FileOutputStream(excelPath);//向excel中添加数据
        row = sheet.createRow(sheet.getLastRowNum() + 1);//在现有行号后追加数据
        for (int i = 0; i < list.size(); i++) {
            String str = String.valueOf(list.get(i));
            row.createCell(i).setCellValue(str);//设置单元格的数据
        }
        out.flush();
        wb.write(out);
        wb.close();
    }

    public static void addExcel2007(String excelPath, List<?> list) throws IOException, InvalidFormatException {
        FileInputStream fs = new FileInputStream(excelPath);//获取excel
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        fs.close();
        XSSFSheet sheet = wb.getSheetAt(0);//获取工作表
        XSSFRow row = sheet.getRow(0);//获取第一行(即:字段列头,便于赋值)
        System.out.println("2007:" + sheet.getLastRowNum() + "空" + row.getLastCellNum());//分别得到最后一行行号,和一条记录的最后一个单元格
        FileOutputStream out = new FileOutputStream(excelPath);//向excel中添加数据
        row = sheet.createRow(sheet.getLastRowNum() + 1);//在现有行号后追加数据
        for (int i = 0; i < list.size(); i++) {
            String str = String.valueOf(list.get(i));
            row.createCell(i).setCellValue(str);//设置单元格的数据
        }
        out.flush();
        wb.write(out);
        wb.close();
        /**
         if(fs != null)
         fs.close();
         if(out != null)
         out.close();
         **/
    }

    public static void addHSSFRichTextString(HSSFWorkbook workbook, HSSFRow row, int colIndex) {
        HSSFFont redFont = (HSSFFont) workbook.createFont();
        redFont.setColor((short)Color.RED.getRGB());// 红色
        HSSFFont blueFont = (HSSFFont) workbook.createFont();
        redFont.setColor((short)Color.BLUE.getRGB());// 蓝色
//创建一个单元格
        HSSFCell hssfCell = row.createCell(colIndex);
//创建富文本字符串，内容为：Hello，World！
        HSSFRichTextString richString = new HSSFRichTextString("Hello, World!");
//对"Hello,"设置redFont字体
        richString.applyFont(0, 6, redFont);
//对"World!"设置blueFont字体
        richString.applyFont(6, 13, blueFont);
//将富文本字符串设置到单元格中
        hssfCell.setCellValue(richString);
    }
}
