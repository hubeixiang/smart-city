package com.sct.webtools.file.task;


import com.sct.commons.excel.ExcelHelper;
import com.sct.commons.excel.wrapper.ExcelFileContextWrapper;
import com.sct.commons.excel.wrapper.FileContextWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class ExcelWriteTask implements Callable<FileContextWrapper> {
    private static Logger logger = LoggerFactory.getLogger(ExcelWriteTask.class);
    private ExcelHelper excelHelper;
    private ExcelFileContextWrapper excelFileContextWrapper;

    public ExcelWriteTask(ExcelHelper excelHelper, ExcelFileContextWrapper excelFileContextWrapper) {
        this.excelHelper = excelHelper;
        this.excelFileContextWrapper = excelFileContextWrapper;
    }

    @Override
    public FileContextWrapper call() throws Exception {
        excelHelper.write(excelFileContextWrapper);
        logger.info("[export-task] [{}]", excelFileContextWrapper.getName());
        return excelFileContextWrapper;
    }
}
