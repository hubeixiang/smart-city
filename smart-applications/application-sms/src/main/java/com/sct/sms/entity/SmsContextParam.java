package com.sct.sms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

@ApiModel("普通短信发送参数")
public class SmsContextParam extends SmsParam {
    @ApiModelProperty("短信内容")
    private String smsContent;

    public static SmsContextParam of(String mobile, String smsContext, String addSerial, int smsPriority, String msgGroup, boolean isMo) {
        return of(Arrays.asList(mobile), smsContext, addSerial, smsPriority, msgGroup, isMo);
    }

    public static SmsContextParam of(List<String> mobiles, String smsContext, String addSerial, int smsPriority, String msgGroup, boolean isMo) {
        SmsContextParam smsContextParam = new SmsContextParam();
        smsContextParam.setMobiles(mobiles);
        smsContextParam.setSmsContent(smsContext);
        of(smsContextParam, addSerial, smsPriority, msgGroup, isMo);
        return smsContextParam;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
