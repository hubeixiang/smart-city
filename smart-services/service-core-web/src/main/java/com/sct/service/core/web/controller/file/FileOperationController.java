package com.sct.service.core.web.controller.file;

import com.sct.commons.file.location.FileLocation;
import com.sct.commons.file.location.FileLocationManager;
import com.sct.commons.file.location.FileNameDescribe;
import com.sct.commons.utils.JsonUtils;
import com.sct.commons.utils.id.IDGenerator;
import com.sct.service.core.api.service.file.ServiceFileLocationApi;
import com.sct.service.core.web.controller.WebConstants;
import com.sct.service.core.web.controller.base.RestFulResponseError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 只对应file从http的上传下载操作
 */
@Api(tags = "文件上传下载操作")
@RestController
@RequestMapping(value = "/file", produces = {WebConstants.WEB_PRODUCES})
public class FileOperationController {
    private Logger logger = LoggerFactory.getLogger(FileOperationController.class);


    @Autowired
    private ServiceFileLocationApi serviceFileLocationApi;

    @ApiOperation(value = "/upload", notes = "上传文件", httpMethod = "POST", response = UploadFiles.class)
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public HttpEntity<Object> httpUploadFile(HttpServletRequest request) {
        String path = String.format("/upload");
        try {
            UploadFiles uploadFiles = new UploadFiles();
            List<FileLocation> saveFiles = new ArrayList<>();
            saveMultipartHttpServletRequest(request, saveFiles);
            uploadFiles.setFileLocations(saveFiles);
            return new ResponseEntity<>(uploadFiles, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            return new ResponseEntity<>(new RestFulResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "/download", notes = "下载文件", httpMethod = "POST")
    @RequestMapping(value = "/download", method = {RequestMethod.POST})
    public void httpDownloadFile(
            @ApiParam(required = true, name = "fileLocation", value = "请求的参数") @RequestBody FileLocation fileLocation,
            HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        String path = String.format("/download");
        logger.info("{} receive , startTime: {}, param {}", path, startTime, JsonUtils.toJson(fileLocation));
        try {
            download(null, fileLocation, response);
            logger.info("{} response : startTime: {} cost: {} ms", path, startTime, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "/download/rename", notes = "下载文件,并重命名下载的文件名", httpMethod = "POST")
    @RequestMapping(value = "/download/rename", method = {RequestMethod.POST})
    public void httpDownloadFileModifyName(
            @ApiParam(required = true, name = "downloadFile", value = "请求的参数") @RequestBody DownloadFile downloadFile,
            HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        String path = String.format("/download");
        logger.info("{} receive , startTime: {}, param {}", path, startTime, JsonUtils.toJson(downloadFile));
        try {
            download(downloadFile.getDownFileName(), downloadFile.getFileLocation(), response);
            logger.info("{} response : startTime: {} cost: {} ms", path, startTime, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private void download(String filename, FileLocation fileLocation, HttpServletResponse response) {
        if (StringUtils.isEmpty(filename)) {
            filename = FileLocationManager.getInstance().getFileName(fileLocation);
        } else {
            filename = String.format("%s%s", filename, fileLocation.getFileExtension().getExtension());
        }
        if (StringUtils.isEmpty(filename)) {
            throw new RuntimeException("传入下载文件要生成的文件名");
        }

        //下载内容写入response中
        writeToResponse(filename, response, fileLocation);
    }

    private void setHttpServletResponseAttachmentInfo(HttpServletResponse response, String filename)
            throws UnsupportedEncodingException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, "UTF-8") + ";filename*=utf-8''" + java.net.URLEncoder.encode(filename, "UTF-8"));
    }

    private void writeToResponse(String filename, HttpServletResponse response, FileLocation fileLocation) {
        OutputStream out = null;
        InputStream is = null;
        FileInputStream fis = null;
        try {
            String fileUrl = FileLocationManager.getInstance().fileUrl(fileLocation);
            //设置下载编码
            setHttpServletResponseAttachmentInfo(response, filename);
            out = response.getOutputStream();
            fis = new FileInputStream(fileUrl);
            is = new BufferedInputStream(fis);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            logger.error(String.format("write byte to response exception:%s", e.getMessage()), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error(String.format("write byte to response exception:%s", e.getMessage()), e);
                throw new RuntimeException(e);
            }
        }
    }

    private void saveMultipartHttpServletRequest(HttpServletRequest request, List<FileLocation> saveFiles) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> map = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
            String key = entry.getKey();
            MultipartFile multipartFile = entry.getValue();
            logger.debug("key:" + key + ",filename:" + multipartFile.getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            FileNameDescribe fileNameDescribe = FileLocationManager.getInstance().parserFileNameDescribe(fileName);
            if (fileNameDescribe == null) {
                //文件上传文件名错误
            } else {
                try {
                    String uuid = IDGenerator.UUID.generate();
                    File parentFile = new File(serviceFileLocationApi.locationUploadTemporaryDir());

                    String serviceUrl = parentFile.getAbsolutePath();
                    logger.info(String.format("filename[%s],serviceUrl=%s", fileName, serviceUrl));
                    File file = new File(serviceUrl, uuid + fileNameDescribe.getFileExtension().getExtension());

                    multipartFile.transferTo(file);

                    FileLocation fileLocation = FileLocationManager.getInstance().parserFileLocalion(file);
                    if (fileLocation != null) {
                        saveFiles.add(fileLocation);
                    }
                } catch (Exception e) {
                    logger.error("transferToFile Exception:" + e.getMessage(), e);
                    throw new RuntimeException(String.format("文件[%s]保存到服务器上异常:%s", fileName, e.getMessage()));
                }
            }
        }
    }
}
