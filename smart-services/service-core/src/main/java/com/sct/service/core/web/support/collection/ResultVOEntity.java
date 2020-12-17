package com.sct.service.core.web.support.collection;

import java.util.ArrayList;
import java.util.List;

public class ResultVOEntity<E> implements ResultVO {
    private List<String> columns = new ArrayList<>();
    private List<E> data = new ArrayList<>();

    public static <E> ResultVOEntity of() {
        ResultVOEntity resultVOEntity = new ResultVOEntity();
        return resultVOEntity;
    }

    public static <E> ResultVOEntity of(List<String> columns, List<E> data) {
        ResultVOEntity resultVOEntity = ResultVOEntity.of();
        resultVOEntity.setColumns(columns);
        resultVOEntity.setData(data);
        return resultVOEntity;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
