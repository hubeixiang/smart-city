package com.sct.service.core.web.response;

/**
 * 所有rest接口异常时,返回的错误描述信息
 */
public class ResponseError<T> {
    private int code;
    private String message;
    private T data;

    public ResponseError() {
    }

    public ResponseError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseError(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
}
