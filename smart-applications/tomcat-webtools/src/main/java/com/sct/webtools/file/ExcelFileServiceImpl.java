package com.sct.webtools.file;

import com.sct.commons.excel.ExcelHelper;
import com.sct.commons.excel.util.ExcelZipUtil;
import com.sct.commons.excel.wrapper.ExcelFileContextWrapper;
import com.sct.commons.excel.wrapper.FileContextWrapper;
import com.sct.commons.excel.wrapper.SheetWrapper;
import com.sct.webtools.file.task.ExcelWriteTask;
import com.sct.webtools.model.ExcelContext;
import com.sct.webtools.model.UploadExcelFilesParam;
import com.sct.webtools.util.WrapperUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class ExcelFileServiceImpl extends AbstractFileService {
    private static Logger logger = LoggerFactory.getLogger(ExcelFileServiceImpl.class);
    @Resource
    protected ExcelHelper helper;
    @Resource
    protected ExecutorService executorService;

    public List<ExcelContext> uploadExcelFileAndParser2Context(List<FileContextWrapper> files) throws Exception {
        List<ExcelFileContextWrapper> excelFileContextWrappers = new ArrayList<>();
        for (FileContextWrapper contextWrapper : files) {
            List<SheetWrapper> fileSheetWrappers = helper.read(contextWrapper);
            logger.info("parse [{}] sheets:{}", contextWrapper.getName(), fileSheetWrappers);
            ExcelFileContextWrapper excelFileContextWrapper = ExcelFileContextWrapper.of(contextWrapper);
            excelFileContextWrapper.setSheets(fileSheetWrappers);
            excelFileContextWrappers.add(excelFileContextWrapper);
        }
        List<ExcelContext> contextList = convert(excelFileContextWrappers);
        return contextList;
    }

    public FileContextWrapper uploadContextAndParser2ExcelFile(UploadExcelFilesParam params) throws Exception {
        long begin = System.currentTimeMillis();
        //读取提交的信息,转换为excel文件内容
        List<ExcelFileContextWrapper> excelFileContextWrappers = WrapperUtil.toExcelFileWrapper(params.getList());

        List<Callable<FileContextWrapper>> tasks = new ArrayList<>();
        for (ExcelFileContextWrapper sheetContext : excelFileContextWrappers) {
            ExcelWriteTask task = new ExcelWriteTask(helper, sheetContext);
            tasks.add(task);
        }

        List<Future<FileContextWrapper>> futureList = executorService.invokeAll(tasks);
        for (Future<FileContextWrapper> future : futureList) {
            try {
                FileContextWrapper result = future.get();
                logger.info("[export] batch export [{}] finish", result.getName());
            } catch (Exception e) {
                logger.error("[export] batch export error:{}", ExceptionUtils.getStackTrace(e));
                throw e;
            }
        }

        FileContextWrapper fileWrapper = new FileContextWrapper();
        logger.info("[export] batch export result:{}", excelFileContextWrappers.size());
        if (excelFileContextWrappers.size() == 1) { // 单文件，不压缩
            fileWrapper = excelFileContextWrappers.get(0);
        } else { // 多文件，压缩
            //ByteArrayOutputStream output = FileUtil.compress(fileWrappers);
            ByteArrayOutputStream output = ExcelZipUtil.zip(excelFileContextWrappers);
            fileWrapper.setName(getZipName());
            fileWrapper.setOutput(output);
        }
        logger.info("[export] batch finish,cost:{}ms", (System.currentTimeMillis() - begin));
        return fileWrapper;
    }

    private List<ExcelContext> convert(List<ExcelFileContextWrapper> excelFileContextWrappers) throws Exception {
        List<ExcelContext> contextList = new ArrayList<>();
        for (ExcelFileContextWrapper excelFileContextWrapper : excelFileContextWrappers) {
            List<ExcelContext.SheetContext> sheetModelList = new ArrayList<>();
            List<SheetWrapper> fileSheets = excelFileContextWrapper.getSheets();
            for (SheetWrapper fileSheetWrapper : fileSheets) {
                ExcelContext.SheetContext sheetModel = new ExcelContext.SheetContext(fileSheetWrapper.getName());
                sheetModel.setHeader(Arrays.asList(fileSheetWrapper.getHeaders()));
                List<String[]> sheetContents = fileSheetWrapper.getContents();
                List<List<String>> contents = new ArrayList<>();
                for (String[] sheetContent : sheetContents) {
                    contents.add(Arrays.asList(sheetContent));
                }
                sheetModel.setContent(contents);
                sheetModelList.add(sheetModel);
            }
            ExcelContext excelContext = new ExcelContext();
            excelContext.setName(excelFileContextWrapper.getName());
            excelContext.setSheets(sheetModelList);
            contextList.add(excelContext);
        }
        return contextList;
    }

    public String getZipName() {
        return "导出文件_" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".zip";
    }
}
