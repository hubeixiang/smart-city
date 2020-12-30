package com.sct.commons.excel;

import com.sct.commons.excel.wrapper.CellWrapper;
import com.sct.commons.excel.wrapper.ExcelFileContextWrapper;
import com.sct.commons.excel.wrapper.FileContextWrapper;
import com.sct.commons.excel.wrapper.SheetWrapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel文件操作类
 */
public class ExcelHelper {
    /**
     * Excel(xlsx)模板
     **/
    //private InputStream xlsxTemplate;	// xlsx模板

    private static final String DEFAULT_XLS_TEMPLATE = "tmpl/excel-v3.xls";
    private static final String DEFAULT_XLSX_TEMPLATE = "tmpl/excel-v3.xlsx";
    private static final String XLSX_TEMPLATE_V4 = "tmpl/excel-v4.xlsx";
    private static Logger logger = LoggerFactory.getLogger(ExcelHelper.class);
    /**
     * Excel表头属性名称
     **/
    public String header = "headers";

    /** Excel(xls)模板 **/
    //private InputStream xlsTemplate;	// xls模板
    /**
     * Excel内容属性名称
     **/
    public String content = "contents";
    /**
     * Excel Sheet名称
     **/
    public String sheetName = "sheetNames";
    /**
     * Excel Sheet内容
     **/
    public String sheetContent = "sheetContents";
    private Resource xlsTemplateResource;
    private Resource xlsxTemplateResource;
    private Resource xlsxTemplateV4Resource;

    public ExcelHelper() {
        initTemplate();
    }

    /**
     * *判断是否为空行
     *
     * @param row       行
     * @param columnNum 列数量(使用列头数量)
     * @return
     */
    private static boolean isRowEmpty(Row row, int columnNum) {
        boolean isEmpty = true;
        for (int i = 0; i < columnNum; i++) {
            try {
                Cell cell = row.getCell(i);
                //if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    isEmpty = false;
                }
            } catch (Exception e) {
                logger.error("excel column[{}/{}] error:{}", i, columnNum, e.getMessage());
                throw e;
            }
        }
        return isEmpty;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetContent() {
        return sheetContent;
    }

    public void setSheetContent(String sheetContent) {
        this.sheetContent = sheetContent;
    }

    public Resource getXlsTemplateResource() {
        return xlsTemplateResource;
    }

    public void setXlsTemplateResource(Resource xlsTemplateResource) {
        this.xlsTemplateResource = xlsTemplateResource;
    }

    public Resource getXlsxTemplateResource() {
        return xlsxTemplateResource;
    }

    public void setXlsxTemplateResource(Resource xlsxTemplateResource) {
        this.xlsxTemplateResource = xlsxTemplateResource;
    }

    public Resource getXlsxTemplateV4Resource() {
        return xlsxTemplateV4Resource;
    }

    public void setXlsxTemplateV4Resource(Resource xlsxTemplateV4Resource) {
        this.xlsxTemplateV4Resource = xlsxTemplateV4Resource;
    }

	/*
	public ByteArrayOutputStream write(ExcelFileContextWrapper fileWrapper) throws Exception {
		ByteArrayOutputStream output =  null;
		try {
			List<SheetWrapper> sheetWrappers = fileWrapper.getSheets();
			List<String> sheetNames = new ArrayList<>();
			List<Map<String, Object>> sheetContents = new ArrayList<>();
			for(SheetWrapper sheetWrapper: sheetWrappers) {
				sheetNames.add(sheetWrapper.getName());
				sheetContents.add(getSheetContents(sheetWrapper));
			}
			InputStream input = getTemplateResource(fileWrapper.getName());
			Context context = PoiTransformer.createInitialContext();
			context.putVar(sheetName,    sheetNames);
			context.putVar(sheetContent, sheetContents);
			output = write(input, context);
			logger.info("write file[{}],sheetNames:{},sheetContents.size:{}", fileWrapper.getName(), sheetNames, (sheetContents==null?0:sheetContents.size()));
			return output;
		} catch(Exception e) {
			logger.error("write file[{}] error:{}", fileWrapper.getName(), ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			if(output != null) {
				output.close();
			}
		}
	}
	*/

    private void initTemplate() {
        if (xlsTemplateResource == null) {
            xlsTemplateResource = new ClassPathResource(DEFAULT_XLS_TEMPLATE);
        }
        if (xlsxTemplateResource == null) {
            xlsxTemplateResource = new ClassPathResource(DEFAULT_XLSX_TEMPLATE);
        }
        if (xlsxTemplateV4Resource == null) {
            xlsxTemplateV4Resource = new ClassPathResource(XLSX_TEMPLATE_V4);
        }
    }

    /**
     * 写文件
     *
     * @param excelFileWrapper
     * @return
     * @throws Exception
     */
    public ByteArrayOutputStream write(ExcelFileContextWrapper excelFileWrapper) throws Exception {
        ByteArrayOutputStream output = null;
        try {
            List<SheetWrapper> sheetWrappers = excelFileWrapper.getSheets();
            List<String> sheetNames = new ArrayList<>();
            List<Map<String, Object>> sheetContents = new ArrayList<>();
            for (SheetWrapper sheetWrapper : sheetWrappers) {
                List<String[]> rows = new ArrayList<>();
                if (sheetWrapper.isMergedHeader()) {    // 合并表头
                    List<CellWrapper> headers = sheetWrapper.getComplexHeaders();
                    CellWrapper[][] headerCellArrays = initCellWrapper(headers);
                    sheetWrapper.setMergedCells(headerCellArrays);    // 设置到SheetWrapper中，后面使用

                    List<String[]> headerRows = initHeaderRows(headerCellArrays);
                    rows.addAll(headerRows);                    // 表头
                    rows.addAll(sheetWrapper.getContents());    // 表内容
                } else {                            // 不合并表头
                    rows.add(sheetWrapper.getHeaders());        // 表头
                    rows.addAll(sheetWrapper.getContents());    // 表内容
                }
                sheetNames.add(sheetWrapper.getName());
                sheetContents.add(getSheetContents(rows));
                logger.info("write file[{}][{}] merged:{}", excelFileWrapper.getName(), sheetWrapper.getName(), sheetWrapper.isMergedHeader());
            }
            InputStream input = xlsxTemplateV4Resource.getInputStream();
            Context context = PoiTransformer.createInitialContext();
            context.putVar("sheetNames", sheetNames);
            context.putVar("sheets", sheetContents);
            output = write(input, context);
            output = merge(sheetWrappers, output);
            logger.info("write file[{}] sheetNames:{},sheetContents.size:{}", excelFileWrapper.getName(), sheetNames, (sheetContents == null ? 0 : sheetContents.size()));
            return output;
        } catch (Exception e) {
            logger.error("write file[{}] error:{}", excelFileWrapper.getName(), ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * 合并单元格
     *
     * @param sheetWrappers
     * @param output
     * @return
     * @throws IOException
     */
    public ByteArrayOutputStream merge(List<SheetWrapper> sheetWrappers, ByteArrayOutputStream output) throws IOException {
        InputStream input = new ByteArrayInputStream(output.toByteArray());
        return merge(sheetWrappers, input);
    }

    /**
     * 合并单元格
     *
     * @param sheetWrappers
     * @param input
     * @return
     * @throws IOException
     */
    public ByteArrayOutputStream merge(List<SheetWrapper> sheetWrappers, InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFCellStyle style = initMergedCellStyle(workbook);
            for (SheetWrapper sheetWrapper : sheetWrappers) {
                String sheetName = sheetWrapper.getName();
                XSSFSheet sheet = workbook.getSheet(sheetName);
                CellWrapper[][] mergedCellArrays = sheetWrapper.getMergedCells();
                if (mergedCellArrays == null || mergedCellArrays.length == 0) {
                    continue;
                }
                // 创建单元格、设置样式、设置单元格值、合并单元格
                for (int rowIndex = 0; rowIndex < mergedCellArrays.length; rowIndex++) {
                    CellWrapper[] cellRegionRow = mergedCellArrays[rowIndex];
                    XSSFRow row = sheet.createRow(rowIndex);
                    for (int columnIndex = 0; columnIndex < cellRegionRow.length; columnIndex++) {
                        CellWrapper cellRegion = cellRegionRow[columnIndex];
                        XSSFCell cell = row.createCell(columnIndex);
                        cell.setCellStyle(style);
                        if (cellRegion != null) {
                            cell.setCellValue(cellRegion.getText());
                            mergeCellWrapper(sheet, cellRegion);
                        } else {
                            cell.setCellValue("");
                        }
                    }
                }
            }
            workbook.write(output);
            return output;
        } catch (IOException e) {
            throw e;
        }
    }

    private ByteArrayOutputStream write(InputStream input, Context context) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            JxlsHelper jxlsHelper = JxlsHelper.getInstance();
            Transformer transformer = jxlsHelper.createTransformer(input, output);
            jxlsHelper.setDeleteTemplateSheet(true);
            jxlsHelper.setUseFastFormulaProcessor(false);
            AreaBuilder areaBuilder = jxlsHelper.getAreaBuilder();
            areaBuilder.setTransformer(transformer);
            List<Area> xlsAreaList = areaBuilder.build();
            Area firstArea = xlsAreaList.get(0);
            CellRef targetCellRef = new CellRef("TemplateSheet!A1");
            firstArea.applyAt(targetCellRef, context);
            String sourceSheetName = firstArea.getStartCellRef().getSheetName();
            if (jxlsHelper.isDeleteTemplateSheet()) {
                transformer.deleteSheet(sourceSheetName);
            }
            transformer.write();
            return output;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    private Map<String, Object> getSheetContents(SheetWrapper sheetWrapper) {
        Map<String, Object> sheet = new HashMap<>();
        sheet.put(header, Arrays.asList(sheetWrapper.getHeaders()));
        sheet.put(content, asList(sheetWrapper.getContents()));
        return sheet;
    }

    private Map<String, Object> getSheetContents(List<String[]> rows) {
        Map<String, Object> sheet = new HashMap<>();
        sheet.put("rows", convertRows(rows));
        return sheet;
    }

    private List<List<String>> asList(List<String[]> arrays) {
        List<List<String>> lists = new ArrayList<>();
        if (arrays != null && !arrays.isEmpty()) {
            for (String[] array : arrays) {
                lists.add(Arrays.asList(array));
            }
        }
        return lists;
    }

    private InputStream getTemplateResource(String name) throws IOException {
        InputStream input = null;
        if (name.endsWith(".xlsx")) {
            input = xlsxTemplateResource.getInputStream();
        } else {
            input = xlsTemplateResource.getInputStream();
        }
        return input;
    }

    /**
     * 读取Excel文件
     * <br>获取所有Sheet页
     *
     * @param contextWrapper
     * @return
     * @throws Exception
     */
    public List<SheetWrapper> read(FileContextWrapper contextWrapper) throws Exception {
        InputStream input = null;
        try {
            input = contextWrapper.getInput();
            Workbook workbook = WorkbookFactory.create(input);
            if (workbook == null) {
                return null;
            }
            List<SheetWrapper> sheetWrappers = new ArrayList<>();
            int sheetNum = workbook.getNumberOfSheets();
            logger.info("read [{}] sheets size:{}", contextWrapper.getName(), sheetNum);
            for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName();
                int rowNum = sheet.getPhysicalNumberOfRows();
                if (rowNum == 0) {    // 排除空Sheet
                    continue;
                }
                try {
                    SheetWrapper sheetWrapper = new SheetWrapper(sheet.getSheetName());
                    Row headerRow = sheet.getRow(0);
                    if (headerRow != null) {
                        int columnNum = headerRow.getPhysicalNumberOfCells();
                        logger.info("read [{}][{}] row/column:{}/{}", contextWrapper.getName(), sheetName, rowNum, columnNum);
                        List<String[]> contents = new ArrayList<>();
                        for (int i = 0; i < rowNum; i++) {
                            try {
                                Row row = sheet.getRow(i);
                                if (row != null && !isRowEmpty(row, columnNum)) {
                                    String[] rows = getRows(row, columnNum);
                                    contents.add(rows);
                                }
                            } catch (Exception e) {
                                logger.error("read [{}][{}] row[{}/{}] error:{}", contextWrapper.getName(), sheetName, i, rowNum, e.getMessage());
                                throw e;
                            }
                        }
                        if (contents.size() > 1) {
                            String[] headers = contents.remove(0);
                            sheetWrapper.setHeaders(headers);
                            sheetWrapper.setContents(contents);
                        }
                        sheetWrappers.add(sheetWrapper);
                        logger.info("read [{}][{}] finish", contextWrapper.getName(), sheetWrapper.getName());
                    }
                } catch (Exception e) {
                    logger.error("read [{}][{}] error:{}", contextWrapper.getName(), sheet.getSheetName(), ExceptionUtils.getStackTrace(e));
                    throw e;
                }
            }
            return sheetWrappers;
        } catch (Exception e) {
            logger.error("read [{}] error:{}", contextWrapper.getName(), ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * 读取指定Excel文件Sheet页
     * <br>Sheet页从0开始
     *
     * @param excelFileWrapper
     * @param sheetIndex
     * @return
     * @throws Exception
     */
    public SheetWrapper read(ExcelFileContextWrapper excelFileWrapper, int sheetIndex) throws Exception {
        InputStream input = null;
        try {
            input = excelFileWrapper.getInput();
            Workbook workbook = WorkbookFactory.create(input);
            if (workbook == null) {
                return null;
            }
            SheetWrapper sheetWrapper = null;
            int sheetNum = workbook.getNumberOfSheets();
            logger.info("read [{}] sheets size:{}", excelFileWrapper.getName(), sheetNum);
            if (sheetIndex >= sheetNum) {
                throw new Exception("The sheet index '" + sheetIndex + "' not exists");
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            String sheetName = sheet.getSheetName();
            int rowNum = sheet.getPhysicalNumberOfRows();
            if (rowNum > 0) {    // 排除空Sheet
                try {
                    sheetWrapper = new SheetWrapper(sheet.getSheetName());
                    Row headerRow = sheet.getRow(0);
                    if (headerRow != null) {
                        int columnNum = headerRow.getPhysicalNumberOfCells();
                        logger.info("read [{}][{}] row/column:{}/{}", excelFileWrapper.getName(), sheetName, rowNum, columnNum);
                        List<String[]> contents = new ArrayList<>();
                        for (int i = 0; i < rowNum; i++) {
                            try {
                                Row row = sheet.getRow(i);
                                if (row != null && !isRowEmpty(row, columnNum)) {
                                    String[] rows = getRows(row, columnNum);
                                    contents.add(rows);
                                }
                            } catch (Exception e) {
                                logger.error("read [{}][{}] row[{}/{}] error:{}", excelFileWrapper.getName(), sheetName, i, rowNum, e.getMessage());
                                throw e;
                            }
                        }
                        if (contents.size() > 1) {
                            String[] headers = contents.remove(0);
                            sheetWrapper.setHeaders(headers);
                            sheetWrapper.setContents(contents);
                        }
                        logger.info("read [{}][{}] finish", excelFileWrapper.getName(), sheetWrapper.getName());
                    }
                } catch (Exception e) {
                    logger.error("read [{}][{}] error:{}", excelFileWrapper.getName(), sheet.getSheetName(), ExceptionUtils.getStackTrace(e));
                    throw e;
                }
            }
            return sheetWrapper;
        } catch (Exception e) {
            logger.error("read [{}] error:{}", excelFileWrapper.getName(), ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * @param row
     * @param columnNum
     * @return
     */
    private String[] getRows(Row row, int columnNum) {
        String[] rows = new String[columnNum];
        for (int columnIndex = 0; columnIndex < columnNum; columnIndex++) {
            Cell cell = row.getCell(columnIndex);
            String cellValue = null;
            if (cell != null && (cellValue = getCellValue(cell)) != null) {
                rows[columnIndex] = cellValue.trim();
            } else {
                rows[columnIndex] = "";
            }
        }
        return rows;
    }

    @SuppressWarnings("deprecation")
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        String value = cell.getStringCellValue();
		/*
		CellType type = cell.getCellTypeEnum();
		if(type.equals(CellType.STRING)) {	// 字符串
			value = cell.getStringCellValue();
		} else if(type.equals(CellType.BLANK)) {	// 空白格
			value = cell.getStringCellValue();
		} else if(type.equals(CellType.BOOLEAN)) {	// 布尔型
			value = String.valueOf(cell.getBooleanCellValue());
		} else if(type.equals(CellType.NUMERIC)) {	// 数字
			value = String.valueOf(cell.getNumericCellValue());
		} else {
			RichTextString s = cell.getRichStringCellValue();
			value = s.getString();
		}
		*/
        return value;
    }

    private List<List<String>> convertRows(List<String[]> rowArrays) {
        List<List<String>> rows = new ArrayList<>(rowArrays != null ? rowArrays.size() : 0);
        for (String[] rowArray : rowArrays) {
            rows.add(Arrays.asList(rowArray));
        }
        return rows;
    }

    private List<String[]> initHeaderRows(CellWrapper[][] headerCellWrapperArrays) {
        int headerRowSize = headerCellWrapperArrays != null ? headerCellWrapperArrays.length : 0;
        List<String[]> rows = new ArrayList<>(headerRowSize);
        for (int rowIndex = 0; rowIndex < headerRowSize; rowIndex++) {
            CellWrapper[] headerRow = headerCellWrapperArrays[rowIndex];
            int columnSize = headerRow.length;
            String[] row = new String[columnSize];
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                CellWrapper cellWrapper = headerRow[columnIndex];
                if (cellWrapper != null && cellWrapper.getText() != null) {
                    row[columnIndex] = cellWrapper.getText().trim();
                } else {
                    row[columnIndex] = "";
                }
            }
            rows.add(row);
        }
        return rows;
    }

    private CellWrapper[][] initMergedCell(SheetWrapper sheetWrapper) {
        List<CellWrapper> headers = sheetWrapper.getComplexHeaders();
        CellWrapper[][] mergedCellArrays = initCellWrapper(headers);
        sheetWrapper.setMergedCells(mergedCellArrays);        // 设置到SheetWrapper中，后面使用
        return mergedCellArrays;
    }

    /**
     * 设置行序号、父子关系
     *
     * @param children
     */
    private CellWrapper[][] initCellWrapper(List<CellWrapper> children) {
        if (children != null && !children.isEmpty()) {
            int maxRowSize = getMaxRowSize(children);
            initCellWrapper(null, children, maxRowSize, 0);
        }
        CellWrapper[][] mergedCellArrays = initMergedCells(children);
        return mergedCellArrays;
    }


    private void initCellWrapper(CellWrapper parent, List<CellWrapper> children, int maxRowSize, int prevColumnIndex) {
        if (parent == null) {        // 行号：0
            int sumColumnIndex = 0; // 累计列序号
            for (CellWrapper child : children) {
                int maxColumnSize = getMaxColumnSize(child);
                child.setParent(null);
                child.setRowIndex(0);
                child.setFirstRow(0);
                child.setFirstColumn(sumColumnIndex);
                child.setLastRow(getLastRow(child, maxRowSize));
                child.setLastColumn(child.getFirstColumn() + (maxColumnSize - 1));
                //logger.info("init cell region[{}/{}]:[{},{}][{},{}]", (parent!=null?parent.getText():""), child.getText(), child.getFirstRow(), child.getFirstColumn(), child.getLastRow(), child.getLastColumn());
                initCellWrapper(child, child.getChildren(), maxRowSize, sumColumnIndex);
                sumColumnIndex += maxColumnSize;    // 累计列序号
            }
        } else {                // 行号：父行号+1
            if (children != null && !children.isEmpty()) {   // 存在子节点
                int sumColumnIndex = prevColumnIndex;
                for (CellWrapper child : children) {
                    int maxColumnSize = getMaxColumnSize(child);
                    child.setParent(parent);
                    child.setRowIndex(parent.getRowIndex() + 1);
                    child.setFirstRow(child.getRowIndex());
                    child.setFirstColumn(sumColumnIndex);
                    child.setLastRow(getLastRow(child, maxRowSize));
                    child.setLastColumn(child.getFirstColumn() + (maxColumnSize - 1));

                    //logger.info("init cell region[{}/{}]:[{},{}][{},{}]", (parent!=null?parent.getText():""), child.getText(), child.getFirstRow(), child.getFirstColumn(), child.getLastRow(), child.getLastColumn(), maxRowSize, maxColumnSize, prevColumnIndex, sumColumnIndex);
                    initCellWrapper(child, child.getChildren(), maxRowSize, sumColumnIndex);
                    sumColumnIndex += maxColumnSize;
                }
            }
        }
    }

    private int getLastRow(CellWrapper cell, int maxRowSize) {
        int lastRow = 0;
        int firstRow = cell.getFirstRow();
        if (cell.hasChildren()) {
            lastRow = firstRow;
        } else {
            lastRow = maxRowSize - 1;
        }
        return lastRow;
    }

    /**
     * 初始化合并的单元格
     *
     * @param cellWrapperList
     * @return
     */
    private CellWrapper[][] initMergedCells(List<CellWrapper> cellWrapperList) {
        int maxRowSize = getMaxRowSize(cellWrapperList);
        int maxColumnSize = getMaxColumnSize(cellWrapperList);
        CellWrapper[][] cellWrapperArrays = new CellWrapper[maxRowSize][maxColumnSize];
        initMergedCells(cellWrapperArrays, cellWrapperList);
        return cellWrapperArrays;
    }

    /**
     * 转换合并的单元格
     *
     * @param cellWrapperArrays
     * @param cellWrapperList
     */
    private void initMergedCells(CellWrapper[][] cellWrapperArrays, List<CellWrapper> cellWrapperList) {
        for (CellWrapper cell : cellWrapperList) {
            cellWrapperArrays[cell.getFirstRow()][cell.getFirstColumn()] = cell;
            if (cell.hasChildren()) {
                initMergedCells(cellWrapperArrays, cell.getChildren());
            }
        }
    }

    /**
     * 获取最大行数
     *
     * @param children
     * @return
     */
    private int getMaxRowSize(List<CellWrapper> children) {
        initCellRowIndex(null, children);
        int maxRowSize = 0;
        if (children != null && !children.isEmpty()) {
            maxRowSize = 1;
            for (CellWrapper child : children) {
                int childMaxRowSize = getMaxRowSize(child);
                maxRowSize = Math.max(maxRowSize, childMaxRowSize);
            }
        }
        return maxRowSize; // size: index + 1
    }

    /**
     * 获取指定单元格的行数
     *
     * @param cell
     * @return
     */
    private int getMaxRowSize(CellWrapper cell) {
        int maxRowSize = cell.getRowIndex() + 1;    // size: index + 1
        if (cell.hasChildren()) {
            for (CellWrapper child : cell.getChildren()) {
                int childMaxRowIndex = getMaxRowSize(child);
                maxRowSize = Math.max(maxRowSize, childMaxRowIndex);
                //log.info("{} row size:{}/{}", child.getText(), childMaxRowSize, maxRowSize);
            }
        }
        return maxRowSize;
    }

    /**
     * 获取列数
     *
     * @param list
     * @return
     */
    private int getMaxColumnSize(List<CellWrapper> list) {
        int maxColumnSize = 0;
        for (CellWrapper cell : list) {
            maxColumnSize += getMaxColumnSize(cell);
        }
        return maxColumnSize;
    }

    /**
     * 获取列数
     *
     * @param cell
     * @return
     */
    private int getMaxColumnSize(CellWrapper cell) {
        int maxColumnSize = 0;
        if (cell != null) {
            if (cell.hasChildren()) {
                for (CellWrapper children : cell.getChildren()) {
                    maxColumnSize += getMaxColumnSize(children);
                }
            } else {
                maxColumnSize = 1;
            }
        } else {
            maxColumnSize = 1;
        }
        //log.info("{} column size:{}", cell.getText(), columnSize);
        return maxColumnSize;
    }

    /**
     * 设置行序号、父子关系
     *
     * @param parent
     * @param children
     */
    private void initCellRowIndex(CellWrapper parent, List<CellWrapper> children) {
        if (parent == null) {        // 行号：0
            for (CellWrapper child : children) {
                child.setRowIndex(0);
                initCellRowIndex(child, child.getChildren());
            }
        } else {                // 行号：父行号+1
            if (children != null && !children.isEmpty()) {   // 存在子节点
                for (CellWrapper child : children) {
                    child.setRowIndex(parent.getRowIndex() + 1);
                    initCellRowIndex(child, child.getChildren());
                }
            }
        }
    }

    private XSSFCellStyle initMergedCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 水平/垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);

        // 四周边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }

    private void mergeCellWrapper(XSSFSheet sheet, CellWrapper cellWrapper) {
        int firstRow = cellWrapper.getFirstRow();
        int firstColumn = cellWrapper.getFirstColumn();
        int lastRow = cellWrapper.getLastRow();
        int lastColumn = cellWrapper.getLastColumn();
        if (firstRow != lastRow || firstColumn != lastColumn) {
            try {
                sheet.addMergedRegion(new CellRangeAddress(cellWrapper.getFirstRow(), cellWrapper.getLastRow(), cellWrapper.getFirstColumn(), cellWrapper.getLastColumn()));
            } catch (Exception e) {
                logger.error("merge[{},{}][{},{}]",
                        cellWrapper.getFirstRow(), cellWrapper.getFirstColumn(), cellWrapper.getLastRow(), cellWrapper.getLastColumn());
            }
        }
    }

    private void initMergedHeader(List<SheetWrapper> sheetWrappers) {
        for (SheetWrapper sheetWrapper : sheetWrappers) {
            List<CellWrapper> headers = sheetWrapper.getComplexHeaders();
            CellWrapper[][] mergedCellArrays = initCellWrapper(headers);
            sheetWrapper.setMergedCells(mergedCellArrays);    // 设置到SheetWrapper中，后面使用
        }
    }

}
