package com.sct.sms.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SmsParam {
    @ApiModelProperty("短信发送号码,单批次发送每批为5000条")
    private List<String> mobiles;
    @ApiModelProperty("扩展码，可选项，用于上行短信定位")
    private String addSerial;
    @ApiModelProperty("短信优先级，取值1-5，填其余值，系统默认选择1.优先级1为最低，5为最高")
    private int smsPriority;
    @ApiModelProperty("发送数据批次号，32位世界上唯一编码，由字母和数字组成。用户可以采用自定义的数据批次产生算法，标定每次下发的数据的批号。 如果不填写该参数，SDK为满足发送服务器的管理需要，会自动生成一个批次号，但是客户端取状态报告时无法分辨短信的状态报告批次。 建议填写")
    private String msgGroup;
    @ApiModelProperty("是否需要上行，值true或者 false.目前云MAS平台默认推送上行")
    //MT  Message Terminal——下行短信，即向手机终端发送短信
    //MO  Message Original——上行短信，即手机用户向特服号码发送短信
    private boolean isMo = false;

    public static void of(SmsParam smsParam, String addSerial, int smsPriority, String msgGroup, boolean isMo) {
        smsParam.setAddSerial(addSerial);
        if (smsPriority >= 1 && smsPriority <= 5) {
            smsParam.setSmsPriority(smsPriority);
        } else {
            smsParam.setSmsPriority(1);
        }
        smsParam.setMsgGroup(msgGroup);
        smsParam.setMo(isMo);
    }

    public List<String> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<String> mobiles) {
        this.mobiles = mobiles;
    }

    public String getAddSerial() {
        return addSerial;
    }

    public void setAddSerial(String addSerial) {
        this.addSerial = addSerial;
    }

    public int getSmsPriority() {
        return smsPriority;
    }

    public void setSmsPriority(int smsPriority) {
        this.smsPriority = smsPriority;
    }

    public String getMsgGroup() {
        return msgGroup;
    }

    public void setMsgGroup(String msgGroup) {
        this.msgGroup = msgGroup;
    }

    public boolean isMo() {
        return isMo;
    }

    public void setMo(boolean mo) {
        isMo = mo;
    }
}
