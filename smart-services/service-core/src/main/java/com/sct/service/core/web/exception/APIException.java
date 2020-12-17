package com.sct.service.core.web.exception;

import com.sct.commons.i18n.I18nMessageUtil;
import org.apache.commons.lang3.StringUtils;

public class APIException extends RuntimeException {
    private int code;
    private String msg;
    private Object data;

    public APIException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static APIException of(int code, Object data) {
        return of(code, null, data);
    }

    public static APIException of(int code, String msg, Object data) {
        if (StringUtils.isEmpty(msg)) {
            try {
                msg = I18nMessageUtil.getInstance().getMessage(String.format("ApiException.%s", code));
            } catch (Throwable t) {
                msg = I18nMessageUtil.getInstance().getMessage("ApiException.Unrecognized");
            }
        }
        return new APIException(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
