package com.sct.webtools.util;

import com.sct.commons.excel.wrapper.CellWrapper;
import com.sct.commons.excel.wrapper.ExcelFileContextWrapper;
import com.sct.commons.excel.wrapper.SheetWrapper;
import com.sct.webtools.model.ExcelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件Bean工具类
 *
 * @author Lupeng 2019-11-07
 */
public class WrapperUtil {
    private static Logger logger = LoggerFactory.getLogger(WrapperUtil.class);


    public static List<ExcelFileContextWrapper> toExcelFileWrapper(List<ExcelContext> modelList) {
        List<ExcelFileContextWrapper> excelFileContextWrappers = new ArrayList<>();
        for (ExcelContext context : modelList) {
            ExcelFileContextWrapper excelFileContextWrapper = ExcelFileContextWrapper.of(context.getName());
            if (!context.getSheets().isEmpty()) {
                List<SheetWrapper> sheetWrappers = toSheetWrapper(context);
                excelFileContextWrapper.setSheets(sheetWrappers);
            }
            excelFileContextWrappers.add(excelFileContextWrapper);
        }
        return excelFileContextWrappers;
    }

    public static List<SheetWrapper> toSheetWrapper(ExcelContext excelContext) {
        List<SheetWrapper> sheetWrappers = new ArrayList<>();
        for (ExcelContext.SheetContext sheetContext : excelContext.getSheets()) {
            SheetWrapper sheetWrapper = toSheetWrapper(sheetContext);
            sheetWrappers.add(sheetWrapper);
        }
        return sheetWrappers;
    }

    public static SheetWrapper toSheetWrapper(ExcelContext.SheetContext sheetContext) {
        SheetWrapper sheetWrapper = new SheetWrapper(sheetContext.getName());
        if (sheetContext.getMergedHeader() == 1) {        // 先设置此值，后面使用
            sheetWrapper.setMergedHeader(true);
        } else {
            sheetWrapper.setMergedHeader(false);
        }
        List<Object> headers = sheetContext.getHeader();
        initSheetHeader(sheetWrapper, headers);

        List<List<String>> contents = sheetContext.getContent();
        if (contents != null) {
            List<String[]> list = new ArrayList<>();
            for (List<String> content : contents) {
                list.add(content.toArray(new String[0]));
            }
            sheetWrapper.setContents(list);
        }
        return sheetWrapper;
    }

    private static void initSheetHeader(SheetWrapper sheetWrapper, List<Object> headers) {
        if (headers == null || headers.isEmpty()) {
            return;
        }

        if (sheetWrapper.isMergedHeader()) {        // 复杂标题
            try {
                List<Map<String, Object>> list = convert(headers);
                List<CellWrapper> complexHeaders = initComplexHeader(list);
                sheetWrapper.setComplexHeaders(complexHeaders);
				/*
				String json = JacksonJsonUtil.writeObject(headers);
				List<CellWrapper> complexHeaders = JacksonJsonUtil.readObjectList(json, CellWrapper.class);
				sheetWrapper.setComplexHeaders(complexHeaders);
				 */
            } catch (Exception e) {
                logger.error("init sheet header error:", e);
            }
        } else {                                // 简单标题
            sheetWrapper.setHeaders(headers.toArray(new String[0]));
        }
    }

    private static List<Map<String, Object>> convert(List<Object> objects) {
        List<Map<String, Object>> list = new ArrayList<>(objects != null ? objects.size() : 0);
        for (Object object : objects) {
            if (object instanceof Map) {
                list.add((Map<String, Object>) object);
            }
        }
        return list;
    }

    private static List<CellWrapper> initComplexHeader(List<Map<String, Object>> childrenList) {
        List<CellWrapper> children = new ArrayList<>(childrenList != null ? childrenList.size() : 0);
        if (childrenList != null && !childrenList.isEmpty()) {
            for (Map<String, Object> childMap : childrenList) {
                String text = (String) childMap.get("text");
                List<Map<String, Object>> subChildrenList = (List<Map<String, Object>>) childMap.get("children");
                CellWrapper child = new CellWrapper(text);
                if (subChildrenList != null && !subChildrenList.isEmpty()) {
                    List<CellWrapper> subChildren = initComplexHeader(subChildrenList);
                    child.setChildren(subChildren);
                }
                children.add(child);
            }
        }
        return children;
    }

}
