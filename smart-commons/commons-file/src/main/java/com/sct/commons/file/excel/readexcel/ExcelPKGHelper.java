package com.sct.commons.file.excel.readexcel;

import com.sct.commons.file.FileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月27日 上午9:08:00
 *
 */

public class ExcelPKGHelper implements Runnable {
    private Logger clog = LoggerFactory.getLogger(getClass());
    private IExcelPKG excelPkg = null;
    private HeadCfg headCfg = null;
    private String filename = null;
    private InputStream excelio = null;
    private List<String> timeHeadList = null;
    // excel2003 扩展名
    public static final String EXCEL03_EXTENSION = FileConstants.FILE_SUFFIX_XLS;
    // excel2007 扩展名
    public static final String EXCEL07_EXTENSION = FileConstants.FILE_SUFFIX_XLSX;

    public static void main(String[] args) throws Exception {
        // String path = "E:/06-TEL/01-导入数据/广东/";
        // String file = "惠州基站导入.xlsx";
        // String file = "enb_TEST1.xlsx";
        // String file = "惠州基站导入.xlsx";
        // String file = "enb_TEST1.xlsx";
        String path = "D:/";
        String file = "sit_import.xlsx";
//		 String file = "SIT_ALL.xlsx";
        // String file = "SIT_ALL.xls";
        // String file = "SIT_ALL.xlsm";
//		 String file = "TEST_TIME.xlsx";
        // String file = "ENB_ALL.xlsx";
        // String file = "SIT_ALL.xlsx";
        // String file = "SIT_ALL.xls";
        // String file = "SIT_ALL.xlsm";
//		String file = "TEST_TIME.xls";
//		ExcelPKGHelper helper = new ExcelPKGHelper(path + file);
        ExcelPKGHelper helper = new ExcelPKGHelper(new FileInputStream(path + file), path + file);
        HeadCfg headCfg = new HeadCfg();
        headCfg.setLineNumber(0);
        headCfg.setStartColumn(0);
        helper.setHeadCfg(headCfg);
        List<String> timeHeadList = new ArrayList<String>();
//		timeHeadList.add("正常日期");
        helper.setTimeHeadInfo(timeHeadList);
        long start = System.currentTimeMillis();
        System.out.println("Start Time[" + start + "]");
        helper.process();
        System.out.println("End   Time[" + System.currentTimeMillis() + "],total time[" + (System.currentTimeMillis() - start) / 1000 + "]秒");
        System.out.println("=============================================");
        System.out.println(helper.getHeadList().toString());
        System.out.println("=============================================");
        System.out.println("=============================================");
        for (List<String> list : helper.getExcelValueList())
            System.out.println(list.toString());
        System.out.println("=============================================");
//		for (List<String> list : helper.getExcelValueList()) {
//			System.out.println(list.toString());
//		}
    }

    public ExcelPKGHelper(String filename) {
        this.filename = filename;
        // 处理 excel2003 文件
        if (this.filename.endsWith(EXCEL03_EXTENSION)) {
            excelPkg = new Excel2003PKG();
            // 处理excel2007文件
        } else if (this.filename.endsWith(EXCEL07_EXTENSION)) {
            excelPkg = new Excel2007PKG();
        }
    }

    public ExcelPKGHelper(InputStream excelio, String filename) {
        this.filename = filename;
        this.excelio = excelio;
        // 处理 excel2003 文件
        if (this.filename.endsWith(EXCEL03_EXTENSION)) {
            excelPkg = new Excel2003PKG();
            // 处理excel2007文件
        } else if (this.filename.endsWith(EXCEL07_EXTENSION)) {
            excelPkg = new Excel2007PKG();
        } else {
            throw new ExcelException("读取的的文件必须是excel2003/2007");
        }
    }

    @Override
    public void run() {
        process();
    }

    public void process() {
        long totalsec = 0;
        try {
            long start = System.currentTimeMillis();
            clog.debug("Start Time[" + start + "]");
            excelPkg.setTimeHeadInfo(timeHeadList);
            if (excelio == null) {
                excelPkg.process(filename);
            } else {
                excelPkg.process(excelio);
            }
            totalsec = (System.currentTimeMillis() - start) / 1000;
            clog.debug("End   Time[" + System.currentTimeMillis() + "],total time[" + totalsec + "] second");
        } catch (RuntimeException re) {
            throw new ExcelException(re.getMessage(), re);
        } catch (Exception ee) {
            throw new ExcelException(ee.getMessage(), ee);
        }
    }

    public List<String> getHeadList() {
        return excelPkg.getHeadList();
    }

    public List<List<String>> getExcelValueList() {
        return excelPkg.getExcelValueList();
    }

    public void setHeadCfg(HeadCfg headCfg) {
        this.headCfg = headCfg;
        excelPkg.setHeadCfg(this.headCfg);
    }

    public void setTimeHeadInfo(List<String> timeHeadList) {
        if (timeHeadList == null || timeHeadList.size() == 0) {
            this.timeHeadList = null;
        } else {
            this.timeHeadList = timeHeadList;
        }
    }

    public void close() {
        if (excelPkg != null) {
            excelPkg.close();
            excelPkg = null;
        }
    }
}
