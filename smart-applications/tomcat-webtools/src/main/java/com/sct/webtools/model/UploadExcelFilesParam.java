package com.sct.webtools.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("上传的excel内容参数")
public class UploadExcelFilesParam {
    @ApiModelProperty("文件列表")
    private List<ExcelContext> list;

    public List<ExcelContext> getList() {
        return list;
    }

    public void setList(List<ExcelContext> list) {
        this.list = list;
    }
}
