package com.sct.application.business.dto;

import com.sct.service.database.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
@ApiModel(value = "ScGridAll", description = "网格全部信息")
public class ScGridAll implements Serializable {
    @ApiModelProperty(value = "网格信息")
    private ScGrid scGrid;
    @ApiModelProperty(value = "网格范围")
    private ScGridRange scGridRange;
    @ApiModelProperty(value = "网格员列表")
    private List<ScGridManager> scGridManagerList;

    public ScGridAll() {
    }

    public ScGridAll(ScGrid scGrid, ScGridRange scGridRange, List<ScGridManager> scGridManagerList) {
        this.scGrid = scGrid;
        this.scGridRange = scGridRange;
        this.scGridManagerList = scGridManagerList;
    }
    public List<ScGridManager> getScGridManagerList() {
        return scGridManagerList;
    }

    public void setScGridManagerList(List<ScGridManager> scGridManagerList) {
        this.scGridManagerList = scGridManagerList;
    }

    public ScGrid getScGrid() {
        return scGrid;
    }

    public void setScGrid(ScGrid scGrid) {
        this.scGrid = scGrid;
    }

    public ScGridRange getScGridRange() {
        return scGridRange;
    }

    public void setScGridRange(ScGridRange scGridRange) {
        this.scGridRange = scGridRange;
    }


}
