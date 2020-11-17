package com.sct.service.core.web.controller.file;

import com.sct.commons.file.location.FileLocation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "上传成功的文件列表")
public class UploadFiles {
    @ApiModelProperty(value = "上传成功的服务端文件定位信息")
    private List<FileLocation> fileLocations = new ArrayList<>();

    public List<FileLocation> getFileLocations() {
        return fileLocations;
    }

    public void setFileLocations(List<FileLocation> fileLocations) {
        this.fileLocations = fileLocations;
    }
}
