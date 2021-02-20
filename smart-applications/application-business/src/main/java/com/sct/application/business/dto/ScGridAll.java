package com.sct.application.business.dto;

import com.sct.service.database.entity.*;

import java.io.Serializable;
import java.util.List;

public class ScGridAll implements Serializable {
    private ScGrid scGrid;
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
