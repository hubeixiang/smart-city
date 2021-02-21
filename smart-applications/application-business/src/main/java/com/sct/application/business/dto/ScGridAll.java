package com.sct.application.business.dto;

import com.sct.service.database.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("网格全部信息")
public class ScGridAll implements Serializable {
    @ApiModelProperty("网格信息")
    private ScGrid scGrid;
    @ApiModelProperty("网格范围")
    private ScGridRange scGridRange;

    public List<ScGridManager> getScGridManagerList() {
        return scGridManagerList;
    }

    public void setScGridManagerList(List<ScGridManager> scGridManagerList) {
        this.scGridManagerList = scGridManagerList;
    }

    private List<ScGridManager> scGridManagerList;

    public ScGridAll() {
    }

    public ScGridAll(ScGrid scGrid, ScGridRange scGridRange, List<ScGridManager> scGridManagerList) {
        this.scGrid = scGrid;
        this.scGridRange = scGridRange;
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
