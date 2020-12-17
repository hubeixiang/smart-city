package com.sct.service.core.web.support.collection;

import java.util.List;

public class ResultVOMap implements ResultVO{
    private List<String> columns;
    private List<ResultMap> data;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<ResultMap> getData() {
        return data;
    }

    public void setData(List<ResultMap> data) {
        this.data = data;
    }
}
