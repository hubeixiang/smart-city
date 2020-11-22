package com.sct.application.authorization.controller.index;

public class MyUrl {
    private String method = "GET";
    private String uri;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
