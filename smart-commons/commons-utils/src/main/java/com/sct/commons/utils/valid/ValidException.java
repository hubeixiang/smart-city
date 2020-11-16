package com.sct.commons.utils.valid;

/**
 * 验证过程中使用到的验证相关的异常
 */
public class ValidException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidException(String message) {
        super(message);
    }

    public ValidException(String message, Throwable t) {
        super(message, t);
    }
}
