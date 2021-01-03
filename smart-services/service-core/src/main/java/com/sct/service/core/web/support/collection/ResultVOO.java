package com.sct.service.core.web.support.collection;

public class ResultVOO<E> implements ResultVO {
    private E data;

    public static <E> ResultVOO of() {
        ResultVOO resultVOEntity = new ResultVOO();
        return resultVOEntity;
    }

    public static <E> ResultVOO of(E data) {
        ResultVOO resultVOEntity = ResultVOO.of();
        resultVOEntity.setData(data);
        return resultVOEntity;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
