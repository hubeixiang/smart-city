package com.sct.service.core.web.support.simple;

public final class EmptyResourceResponse implements ResourceResponse {
    public static final EmptyResourceResponse INSTANCE = new EmptyResourceResponse();
    private String state = "empty";

    public EmptyResourceResponse() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
