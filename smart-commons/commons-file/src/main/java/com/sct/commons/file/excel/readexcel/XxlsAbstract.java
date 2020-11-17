package com.sct.commons.file.excel.readexcel;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 * XSSF and SAX (Event API)
 * @author 123 create at 2015年5月26日 下午3:32:01
 *
 */

public abstract class XxlsAbstract extends DefaultHandler implements IExcelPKG {
    private static Logger logger = LoggerFactory.getLogger(XxlsAbstract.class);
    private SharedStringsTable sst;
    private String lastContents;
    private boolean nextIsString;

    private int sheetIndex = -1;
    private List<String> rowlist = new ArrayList<String>();
    private int curRow = 0;        //当前行
    private int curCol = 0;        //当前列索引
    private int preCol = 0;        //上一列列索引
    private int titleRow = 0;    //标题行，一般情况下为0
    private int rowsize = 0;    //列数

    //只遍历一个sheet，其中sheetId为要遍历的sheet索引，从1开始，1-3

    /**
     * @param filename
     * @param sheetId  sheetId为要遍历的sheet索引，从1开始，1-3
     * @throws Exception
     */
    public void processOneSheet(String filename, int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        processOPCPackage(pkg, sheetId);
    }

    public void processOneSheet(InputStream excelio, int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(excelio);
        processOPCPackage(pkg, sheetId);
    }

    /**
     * 遍历 excel 文件
     */
    public void process(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        processOPCPackage(pkg, -1);
    }

    public void process(InputStream excelio) throws Exception {
        OPCPackage pkg = OPCPackage.open(excelio);
        processOPCPackage(pkg, -1);
    }

    private void processOPCPackage(OPCPackage pkg, int sheetId) throws Exception {
        if (sheetId < 0) {
            XSSFReader r = new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();

            XMLReader parser = fetchSheetParser(sst);

            Iterator<InputStream> sheets = r.getSheetsData();
            while (sheets.hasNext()) {
                curRow = 0;
                sheetIndex++;
                InputStream sheet = sheets.next();
                InputSource sheetSource = new InputSource(sheet);
                startSheet();
                parser.parse(sheetSource);
                sheet.close();
            }
        } else {
            XSSFReader r = new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();

            XMLReader parser = fetchSheetParser(sst);

            // rId2 found by processing the Workbook
            // 根据 rId# 或 rSheet# 查找sheet
            InputStream sheet2 = r.getSheet("rId" + sheetId);
            InputSource sheetSource = new InputSource(sheet2);
            sheetIndex = sheetId;
            startSheet();
            parser.parse(sheetSource);
            sheet2.close();
        }
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst)
            throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        //.createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }

    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        // c => 单元格
        if (name.equals("c")) {
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            String rowStr = attributes.getValue("r");
            curCol = this.getRowIndex(rowStr);
            if (cellType != null && cellType.equals("s")) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
        }
        // 置空
        lastContents = "";
    }

    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        if (nextIsString) {
            try {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
                        .toString();
            } catch (Exception e) {
//				e.printStackTrace();
            }
        }

        // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
        // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
        if (name.equals("v")) {
            String value = lastContents.trim();
            value = processEmptyString(value);
            int cols = curCol - preCol;
            if (cols > 1) {
                for (int i = 0; i < cols - 1; i++) {
                    rowlist.add(preCol, "");
                }
            }
            preCol = curCol;
            rowlist.add(curCol - 1, value);
        } else {
            //如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
            if (name.equals("row")) {
                int tmpCols = rowlist.size();
                if (curRow > this.titleRow && tmpCols < this.rowsize) {
                    for (int i = 0; i < this.rowsize - tmpCols; i++) {
                        rowlist.add(rowlist.size(), "");
                    }
                }
                try {
                    optRows(sheetIndex, curRow, rowlist);
                } catch (SQLException e) {
                    logger.error("file line index=[" + curRow + "] exception", e);
                }
                if (curRow == this.titleRow) {
                    this.rowsize = rowlist.size();
                }
                rowlist.clear();
                curRow++;
                curCol = 0;
                preCol = 0;
            }
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

    //得到列索引，每一列c元素的r属性构成为字母加数字的形式，字母组合为列索引，数字组合为行索引，
    //如AB45,表示为第（A-A+1）*26+（B-A+1）*26列，45行
    public int getRowIndex(String rowStr) {
        rowStr = rowStr.replaceAll("[^A-Z]", "");
        float num = 0;
        try {
            byte[] rowAbc = rowStr.getBytes("utf-8");
            int len = rowAbc.length;

            for (int i = 0; i < len; i++) {
                num += (rowAbc[i] - 'A' + 1) * Math.pow(26, len - i - 1);
            }

        } catch (Exception e) {
            logger.error("get line index exception", e);
        }
        return (int) num;
    }

    public int getTitleRow() {
        return titleRow;
    }

    public void setTitleRow(int titleRow) {
        this.titleRow = titleRow;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    private String processEmptyString(String value) {
        return value.trim().equals("") ? "" : value.trim();
    }

    public void close() {
        if (sst != null) sst = null;
        if (rowlist != null) {
            rowlist.clear();
            rowlist = null;
        }
    }
}
