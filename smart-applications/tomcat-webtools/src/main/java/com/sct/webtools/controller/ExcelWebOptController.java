package com.sct.webtools.controller;

import com.sct.commons.excel.wrapper.FileContextWrapper;
import com.sct.commons.file.fileutil.WebHttpServletResponseUtil;
import com.sct.webtools.file.ExcelFileServiceImpl;
import com.sct.webtools.model.ExcelContext;
import com.sct.webtools.model.UploadExcelFilesParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Api("Excel文件相关的web操作")
@RestController("/excel")
public class ExcelWebOptController extends ValidateParameController {
    private static Logger logger = LoggerFactory.getLogger(ExcelWebOptController.class);

    @Autowired
    private ExcelFileServiceImpl excelFileService;

    @ApiOperation(
            value = "上传Excel文件,解析文件内容,返回解析内容",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadExcelFileAndParser2Context(MultipartHttpServletRequest request) {
        List<MultipartFile> files = request.getFiles("files");
        logger.info("[upload and parser] request file.size={}", files == null ? 0 : files.size());
        ResponseEntity<?> responseEntity = null;
        try {
            List<FileContextWrapper> contextWrappers = excelFileService.parserMultipartFiles(files);
            List<ExcelContext> contexts = excelFileService.uploadExcelFileAndParser2Context(contextWrappers);
            responseEntity = ResponseEntity.ok(contexts);
        } catch (Exception e) {
            logger.error("[upload and parser] response error:{}", ExceptionUtils.getStackTrace(e));
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return responseEntity;
    }

    @ApiOperation(value = "提交内容,生成Excel文件,返回下载文件")
    @RequestMapping(value = "/export", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> uploadContextAndParser2ExcelFile(@RequestBody UploadExcelFilesParam params) {
        logger.info("[upload and export] request{size={}}", params == null ? 0 : params.getList().size());
        try {
            validUploadExcelFilesParam(params);
            FileContextWrapper fileContextWrapper = excelFileService.uploadContextAndParser2ExcelFile(params);
            return WebHttpServletResponseUtil.stream(fileContextWrapper.getName(), fileContextWrapper.getOutput().toByteArray());
        } catch (Exception e) {
            logger.error("[upload and export] response error:{}", ExceptionUtils.getStackTrace(e));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
