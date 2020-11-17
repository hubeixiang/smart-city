package com.sct.service.core.web.controller.file;

import com.sct.commons.file.location.FileLocation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "下载文件描述")
public class DownloadFile {
    //下载文件生成的文件名
    @ApiModelProperty(value = "下载生成的文件名(不带文件类型后缀,后缀依据服务存放的文件类型生成)")
    private String downFileName;
    @ApiModelProperty(value = "服务端文件定位信息")
    private FileLocation fileLocation;

    public String getDownFileName() {
        return downFileName;
    }

    public void setDownFileName(String downFileName) {
        this.downFileName = downFileName;
    }

    public FileLocation getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(FileLocation fileLocation) {
        this.fileLocation = fileLocation;
    }
}
