package com.sct.service.core.web.support.collection;

import java.util.List;

public class ResultVOList implements ResultVO {
    private List<String> columns;
    private List<ResultList> data;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<ResultList> getData() {
        return data;
    }

    public void setData(List<ResultList> data) {
        this.data = data;
    }
}
