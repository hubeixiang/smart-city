package com.sct.webtools.controller;

import com.sct.webtools.constants.ApiConstants;
import com.sct.webtools.file.StaticFileServiceImpl;
import com.sct.webtools.model.DeleteFilesParam;
import com.sct.webtools.model.UploadFilesParam;
import com.sct.webtools.response.ResultEntity;
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

import javax.servlet.http.HttpServletRequest;

@Api("Web静态文件操作")
@RestController("/static")
public class StaticFileWebOptController extends ValidateParameController {
    private static Logger logger = LoggerFactory.getLogger(StaticFileWebOptController.class);

    @Autowired
    private StaticFileServiceImpl staticFileServiceImpl;

    @ApiOperation(value = "文件删除接口")
    @RequestMapping(value = ApiConstants.FILE_API, method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@RequestBody DeleteFilesParam params, HttpServletRequest httpRequest) {
        logger.info("[delete] request:{size={}}", params == null ? 0 : params.getFiles().size());
        ResponseEntity<Object> responseEntity = null;
        try {
            validDeleteFilesParam(params);
            String basePath = initBasePath(httpRequest);
            ResultEntity resultEntity = ResultEntity.ok();
            staticFileServiceImpl.delete(basePath, params.getFiles());
            logger.info("[delete] response:{}", resultEntity);
            responseEntity = ResponseEntity.ok(resultEntity);
        } catch (Exception e) {
            logger.info("[delete] response error:{}", e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }

    @ApiOperation(
            value = "文件上传接口",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestMapping(value = ApiConstants.FILE_UPLOAD_API, method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestBody UploadFilesParam params, HttpServletRequest httpRequest) {
        long begin = System.currentTimeMillis();
        String savePath = params.getSavePath();
        logger.info("[upload] request file.size={},save path={},save type={},hasError:{}"
                , (params.getFiles() == null ? 0 : params.getFiles().size()), savePath, params.getSaveType());
        ResponseEntity<?> responseEntity = null;
        try {
            validUploadFilesParam(params);

            String basePath = initBasePath(httpRequest);
            ResultEntity resultEntity = ResultEntity.ok();
            staticFileServiceImpl.upload(basePath, params);
            logger.info("[upload] finish,cost:{}ms", (System.currentTimeMillis() - begin));
            responseEntity = ResponseEntity.ok(resultEntity);
        } catch (Exception e) {
            logger.error("[upload] response error:{}", ExceptionUtils.getStackTrace(e));
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }
}
