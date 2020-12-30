package com.sct.webtools.response;

/**
 * 所有rest接口异常时,返回的错误描述信息
 */
public class ResultEntity<T> {
    private int code;
    private String message;
    private T data;

    public ResultEntity() {
    }

    public static ResultEntity ok() {
        ResultEntity resultEntity = ResultEntity.of(Code.SUCCESS, "SUCCESS");
        return resultEntity;
    }

    public static <T> ResultEntity<T> of(int code, String message, T data) {
        ResultEntity resultEntity = ResultEntity.of(code, message);
        resultEntity.data = data;
        return resultEntity;
    }

    public static ResultEntity of(int code, String message) {
        ResultEntity resultEntity = ResultEntity.of();
        resultEntity.code = code;
        resultEntity.message = message;
        return resultEntity;
    }

    public static ResultEntity of() {
        return new ResultEntity();
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
         * 0-操作失败
         **/
        public static final int FAILURE = 0;
    }
}
