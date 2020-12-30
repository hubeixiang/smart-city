package com.sct.webtools.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@ApiModel("文件上传保存参数")
public class UploadFilesParam implements Serializable {
    private static final long serialVersionUID = 81717L;
    @ApiModelProperty(value = "上传的文件")
    private List<MultipartFile> files;
    @ApiModelProperty(value = "文件保存的目录")
    private String savePath;
    @ApiModelProperty(value = "文件保存的方式")
    private SaveType saveType;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public SaveType getSaveType() {
        return saveType;
    }

    public void setSaveType(SaveType saveType) {
        this.saveType = saveType;
    }
}
