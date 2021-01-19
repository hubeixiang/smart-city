package com.sct.commons.file.excel.writeexcel;

import com.sct.commons.file.excel.readexcel.ExcelPKGHelper;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sven on 2017/3/27.
 */
public class WriteExcelUtil {
    public static String writeCustomizedImportTemplateExcel07(String dir, String fileName, String sheetName, List<String> headInfo, List<HeadType> headType, List<List<String>> data) throws IOException {
        FileOutputStream fileOut = null;
        try {
            Workbook wb = new WriteImportTemplateExcel2007Util().writeExcel(sheetName, headInfo, headType, data);
            if (!fileName.endsWith(ExcelPKGHelper.EXCEL07_EXTENSION)) {
                fileName = fileName + ExcelPKGHelper.EXCEL07_EXTENSION;
            }
            String dirFileName = dir + File.separator + fileName;
            fileOut = new FileOutputStream(dirFileName);
            wb.write(fileOut);
            fileOut.flush();
            return fileName;
        } catch (IOException e) {
            throw e;
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (Exception x) {
                }
            }
            fileOut = null;
        }
    }

    public static String checkExcelFileName(String fileName) {
        String excelFileName = null;
        if (fileName.endsWith(ExcelPKGHelper.EXCEL07_EXTENSION)) {
            excelFileName = fileName;
        } else if (fileName.endsWith(ExcelPKGHelper.EXCEL03_EXTENSION)) {
            excelFileName = fileName;
        } else {
            excelFileName = fileName + ExcelPKGHelper.EXCEL07_EXTENSION;
        }
        return excelFileName;
    }

    public static String writeSimpleExcel(String dir, String fileName, String sheetName, List<String> headTitle, List<HeadType> headType, List<List<String>> data) throws IOException {
        return writeSimpleExcel(dir, fileName, sheetName, headTitle, headType, data, false);
    }

    public static String writeSimpleExcel(String dir, String fileName, String sheetName, List<String> headTitle, List<HeadType> headType, List<List<String>> data, boolean isMultiSheet) throws IOException {
        String excelFileName = null;
        if (fileName.endsWith(ExcelPKGHelper.EXCEL07_EXTENSION)) {
            excelFileName = WriteExcelUtil.writeSimpleExcel07(dir, fileName, sheetName, headTitle, headType, data, isMultiSheet);
        } else if (fileName.endsWith(ExcelPKGHelper.EXCEL03_EXTENSION)) {
            excelFileName = WriteExcelUtil.writeSimpleExcel03(dir, fileName, sheetName, headTitle, headType, data, isMultiSheet);
        } else {
            //默认使用2007
            excelFileName = WriteExcelUtil.writeSimpleExcel07(dir, fileName, sheetName, headTitle, headType, data, isMultiSheet);
        }
        return excelFileName;
    }

    public static String writeSimpleExcel03(String dir, String fileName, String sheetName, List<String> headTitle, List<HeadType> headType, List<List<String>> data, boolean isMultiSheet) throws IOException {
        FileOutputStream fileOut = null;
        try {
            Workbook wb = createWorkBook(ExcelPKGHelper.EXCEL03_EXTENSION, sheetName, headTitle, headType, data, isMultiSheet);
            if (!fileName.endsWith(ExcelPKGHelper.EXCEL03_EXTENSION)) {
                fileName = fileName + ExcelPKGHelper.EXCEL03_EXTENSION;
            }
            String dirFileName = dir + File.separator + fileName;
            fileOut = new FileOutputStream(dirFileName);
            wb.write(fileOut);
            fileOut.flush();
            return fileName;
        } catch (IOException e) {
            throw e;
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (Exception x) {
                }
            }
            fileOut = null;
        }
    }

    public static String writeSimpleExcel07(String dir, String fileName, String sheetName, List<String> headTitle, List<HeadType> headType, List<List<String>> data, boolean isMultiSheet) throws IOException {
        FileOutputStream fileOut = null;
        try {
            Workbook wb = createWorkBook(ExcelPKGHelper.EXCEL07_EXTENSION, sheetName, headTitle, headType, data, isMultiSheet);
            if (!fileName.endsWith(ExcelPKGHelper.EXCEL07_EXTENSION)) {
                fileName = fileName + ExcelPKGHelper.EXCEL07_EXTENSION;
            }
            String dirFileName = dir + File.separator + fileName;
            fileOut = new FileOutputStream(dirFileName);
            wb.write(fileOut);
            fileOut.flush();
            return fileName;
        } catch (IOException e) {
            throw e;
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (Exception x) {
                }
            }
            fileOut = null;
        }
    }

    /**
     * @param excelType    excel文件类型
     * @param sheetName    第一个sheet的名称
     * @param headInfo     sheet列头信息,必须存在,不能为空
     * @param headType     列头对应的excel格式,可以为空
     * @param contextInfo  excel数据内容
     * @param isMultiSheet 当数据太多时,是否需要分sheet写入文件
     * @return
     * @throws IOException
     */
    private static Workbook createWorkBook(String excelType, String sheetName, List<String> headInfo, List<HeadType> headType, List<List<String>> contextInfo, boolean isMultiSheet) throws IOException {
        Workbook wb = null;
        headType = checkHeadType(headInfo, headType);
        if (excelType.equals(ExcelPKGHelper.EXCEL07_EXTENSION)) {
            wb = new WriteExcel2007Util().writeExcel(sheetName, headInfo, headType, contextInfo, isMultiSheet);
        } else if (excelType.equals(ExcelPKGHelper.EXCEL03_EXTENSION)) {
            wb = new WriteExcel2003Util().writeExcel(sheetName, headInfo, headType, contextInfo, isMultiSheet);
        }
        return wb;
    }

    private static List<HeadType> checkHeadType(List<String> headInfo, List<HeadType> headTypes) {
        if (headTypes == null) {
            headTypes = new ArrayList<>(headInfo.size());
        }
        return headTypes;
    }


    public static void main(String[] args) {
        try {
            String resourceType = "WriteExcelUtil";
            String dir = "E:\\01IntelliJ\\04GitHubIdeaSelf\\smart-city\\logs";
            String fileName = resourceType + ExcelPKGHelper.EXCEL07_EXTENSION;
            String sheetName = "测试第一个SheetName";
            List<String> headTitle = new ArrayList<String>();
            List<HeadType> headTypes = new ArrayList<HeadType>();
            headTitle.add("Board Name");
            HeadType headType1 = new HeadType();
            headType1.setCellStyle((short) Color.RED.getRGB(), HeadType.COLOR_NONE);
            headTypes.add(headType1);
            headTitle.add("SDH Name");
            HeadType headType2 = new HeadType();
            headType2.setCellStyle((short) Color.YELLOW.getRGB(), HeadType.COLOR_NONE);
            headTypes.add(headType2);
            headTitle.add("Military District");
            HeadType headType3 = new HeadType();
            headType3.setCellStyle((short) Color.BLUE.getRGB(), HeadType.COLOR_NONE);
            headTypes.add(headType3);
            headTitle.add("Province");
            HeadType headType4 = new HeadType();
            headType4.setCellStyle((short) Color.RED.getRGB(), HeadType.COLOR_NONE, true);
            headTypes.add(headType4);
            headTitle.add("Vendor");
            HeadType headType5 = new HeadType();
            headType5.setCellStyle((short) Color.RED.getRGB(), (short) Color.YELLOW.getRGB(), true);
            headTypes.add(headType5);
            headTitle.add("Board Type");
            HeadType headType6 = new HeadType();
            headType6.setCellStyle(HeadType.COLOR_NONE, (short) Color.YELLOW.getRGB(), true);
            headTypes.add(headType6);
            headTitle.add("Network Type");
            HeadType headType7 = new HeadType();
            headType7.setCellStyle((short) Color.RED.getRGB(), HeadType.COLOR_NONE);
            headTypes.add(headType7);
            headTitle.add("resourcesid");
            HeadType headType8 = new HeadType();
            headType8.setCellStyle((short) Color.RED.getRGB(), HeadType.COLOR_NONE);
            headTypes.add(headType8);
            List<List<String>> data = new ArrayList<List<String>>();
            List<String> line1 = new ArrayList<String>();
            line1.addAll(headTitle);
            data.add(line1);
            data.add(line1);
            data.add(line1);
            data.add(line1);
            WriteExcelUtil.writeSimpleExcel(dir, fileName, sheetName, headTitle, headTypes, data, false);
            WriteExcelUtil.writeCustomizedImportTemplateExcel07(dir, "Import_" + fileName, sheetName, headTitle, headTypes, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
