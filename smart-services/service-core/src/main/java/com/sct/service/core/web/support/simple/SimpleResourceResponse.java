package com.sct.service.core.web.support.simple;

public class SimpleResourceResponse<T> implements ResourceResponse {
    private T context;


    public static <T> SimpleResourceResponse of() {
        SimpleResourceResponse simpleResourceResponse = new SimpleResourceResponse();
        return simpleResourceResponse;
    }

    public static <T> SimpleResourceResponse of(T context) {
        SimpleResourceResponse simpleResourceResponse = new SimpleResourceResponse();
        simpleResourceResponse.setContext(context);
        return simpleResourceResponse;
    }

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }
}
