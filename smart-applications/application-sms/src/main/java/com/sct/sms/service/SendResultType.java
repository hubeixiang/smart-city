package com.sct.sms.service;

import com.sct.sms.annotation.IdEnum;

public enum SendResultType implements IdEnum<SendResultType> {
    SUCCESS(1, "短信发送成功"),
    CONTEXT_EMPTY(101, "短信内容为空"),
    MOBILE_EMPTY(102, "号码数组为空"),
    MOBILES_ARRAY_EMPTY(103, "号码数组为空数组"),
    ILLEGAL_NUMBER(104, "批次短信的号码中存在非法号码， SDK带有号码的验证处理。"),
    NOT_CERTIFIED(105, "未进行身份认证或认证失败，用户请确认输入的用户名，密码和企业名是否正确。"),
    SIGNATURE_IS_EMPTY(106, "网关签名为空， 用户需要填写网关签名编号"),
    OTHER_ERROR(107, "其它错误"),
    JSM_EXCEPTION(108, "JMS异常， 需要联系移动集团维护人员"),
    MOBILE_DUPLICATE(109, "批次短信号码中存在重复号码"),
    UNRECOGNIZED_ERROR_NUMBER(110, "未识别的错误号");

    private int id;
    private String description;


    SendResultType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static SendResultType getType(Integer id) {
        return IdEnum.getType(SendResultType.class, id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
