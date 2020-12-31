package com.sct.sms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("模板短信发送参数")
public class SmsTemplatedParam extends SmsParam {
    @ApiModelProperty("模版ID，模板由用户在中国移动集团提供的客户业务平台上，由客户自己增加短信模版的信息.")
    private String tempId;
    @ApiModelProperty("模版参数，字符串数组.")
    private List<String> params;

    public static SmsTemplatedParam of(List<String> mobiles, String tempId, List<String> params, String addSerial, int smsPriority, String msgGroup, boolean isMo) {
        SmsTemplatedParam smsTemplatedParam = new SmsTemplatedParam();
        smsTemplatedParam.setMobiles(mobiles);
        smsTemplatedParam.setTempId(tempId);
        smsTemplatedParam.setParams(params == null ? new ArrayList<>() : params);
        of(smsTemplatedParam, addSerial, smsPriority, msgGroup, isMo);
        return smsTemplatedParam;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
