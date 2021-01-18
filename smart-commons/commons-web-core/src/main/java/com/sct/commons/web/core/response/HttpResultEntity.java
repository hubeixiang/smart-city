package com.sct.commons.web.core.response;

/**
 * 所有rest接口异常时,返回的错误描述信息
 */
public class HttpResultEntity<T> {
    private int code;
    private String message;
    private T data;

    public HttpResultEntity() {
    }

    public static HttpResultEntity ok() {
        HttpResultEntity httpResultEntity = HttpResultEntity.of(Code.SUCCESS, "SUCCESS");
        return httpResultEntity;
    }

    public static <T> HttpResultEntity<T> ok(T data) {
        HttpResultEntity httpResultEntity = HttpResultEntity.of(Code.SUCCESS, "SUCCESS");
        httpResultEntity.setData(data);
        return httpResultEntity;
    }

    public static <T> HttpResultEntity<T> failure(String message, T data) {
        HttpResultEntity httpResultEntity = HttpResultEntity.of(Code.FAILURE, message, data);
        return httpResultEntity;
    }

    public static <T> HttpResultEntity<T> of(int code, String message, T data) {
        HttpResultEntity httpResultEntity = HttpResultEntity.of(code, message);
        httpResultEntity.data = data;
        return httpResultEntity;
    }

    public static HttpResultEntity of(int code, String message) {
        HttpResultEntity httpResultEntity = HttpResultEntity.of();
        httpResultEntity.code = code;
        httpResultEntity.message = message;
        return httpResultEntity;
    }

    public static HttpResultEntity of() {
        return new HttpResultEntity();
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

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 操作结果状态
     **/
    public static class Code {
        /**
         * 1-操作成功
         **/
        public static final int SUCCESS = 1;
        /**
         * 0-操作失败,还可以按照具体的业务定义其他code值
         **/
        public static final int FAILURE = 0;
    }
}
