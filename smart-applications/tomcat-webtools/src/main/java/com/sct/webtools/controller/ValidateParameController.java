package com.sct.webtools.controller;

import com.sct.webtools.model.DeleteFilesParam;
import com.sct.webtools.model.ExcelContext;
import com.sct.webtools.model.SaveType;
import com.sct.webtools.model.UploadExcelFilesParam;
import com.sct.webtools.model.UploadFilesParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public abstract class ValidateParameController {

    protected String initBasePath(HttpServletRequest httpRequest) {
        String basePath = httpRequest.getServletContext().getRealPath(File.separator) + File.separator + "..";
        return basePath;
    }

    protected void validDeleteFilesParam(DeleteFilesParam deleteFilesParam) {
        if (deleteFilesParam == null) {
            throw new RuntimeException("delete param can't null");
        }
        if (CollectionUtils.isEmpty(deleteFilesParam.getFiles())) {
            throw new RuntimeException("delete files can't empty");
        }
    }

    protected void validUploadFilesParam(UploadFilesParam uploadFilesParam) {
        if (uploadFilesParam == null) {
            throw new RuntimeException("upload param can't null");
        }
        if (CollectionUtils.isEmpty(uploadFilesParam.getFiles())) {
            throw new RuntimeException("upload files can't empty");
        }
        if (StringUtils.isNotEmpty(uploadFilesParam.getSavePath())) {
            //验证上传的目录,保证不设置非法目录
            validPath(uploadFilesParam.getSavePath());
        }
        if (uploadFilesParam.getSaveType() == null) {
            uploadFilesParam.setSaveType(SaveType.tomcat);
        }
    }

    protected void validUploadExcelFilesParam(UploadExcelFilesParam params) {
        if (params == null) {
            throw new RuntimeException("export excel files param can't null");
        }
        if (CollectionUtils.isEmpty(params.getList())) {
            throw new RuntimeException("export files list can't empty");
        }
        for (ExcelContext context : params.getList()) {
            validExcelContext(context);
        }
    }

    protected void validExcelContext(ExcelContext params) {
        if (params == null) {
            throw new RuntimeException("excel context param can't null");
        }
        if (StringUtils.isEmpty(params.getName())) {
            throw new RuntimeException("export file name can't empty");
        }
        if (CollectionUtils.isEmpty(params.getSheets())) {
            throw new RuntimeException("export file context can't empty");
        }
    }

    private void validPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            String formatPath = formatPath(path);
            if (formatPath.contains("../..") || formatPath.contains("..\\..")) {
                throw new RuntimeException("invalid path");
            }
        }
    }

    private String formatPath(String path) {
        if (StringUtils.isEmpty(path)) {
            return path;
        }
        File file = new File(path);
        return file.getPath();
    }
}
