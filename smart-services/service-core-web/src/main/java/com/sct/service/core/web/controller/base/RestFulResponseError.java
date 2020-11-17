package com.sct.service.core.web.controller.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 所有rest接口异常时,返回的错误描述信息
 */
@ApiModel(value="通用rest接口异常返回结果")
public class RestFulResponseError {
    @ApiModelProperty(value="异常代码")
    private int code;
    @ApiModelProperty(value="异常具体描述信息")
    private String message;

    public RestFulResponseError() {
    }

    public RestFulResponseError(int code, String message) {
        this.code = code;
        this.message = message;
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
}
