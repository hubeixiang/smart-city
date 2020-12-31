package com.sct.sms.service;


import com.mascloud.model.MoModel;
import com.mascloud.model.StatusReportModel;
import com.mascloud.model.SubmitReportModel;
import com.mascloud.sdkclient.Client;
import com.sct.commons.utils.JsonUtils;
import com.sct.sms.entity.SmsContextParam;
import com.sct.sms.entity.SmsTemplatedParam;
import com.sct.sms.properties.LoginProperties;
import com.sct.sms.properties.SendProperties;
import com.sct.sms.properties.SmsSystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SmsClient {
    private static Logger logger = LoggerFactory.getLogger(SmsClient.class);
    private SmsSystemProperties smsSystemProperties;
    private SendProperties send;
    private Client client = null;

    public SmsClient(SmsSystemProperties smsSystemProperties) {
        this.smsSystemProperties = smsSystemProperties;
        this.send = this.smsSystemProperties.getSend();
    }

    public void init() {
        login();
    }

    public void close() {
        logout();
    }

    public void login() {
        client = Client.getInstance();
        LoginProperties loginProperties = smsSystemProperties.getLogin();
        boolean success = client.login(loginProperties.getUrl(), loginProperties.getUserAccount(), loginProperties.getPassword(), loginProperties.getEcname());
        if (!success) {
            throw new RuntimeException("sms login sign is false");
        }
    }

    public SendResultType send(SmsContextParam smsContextParam) {
        int status = client.sendDSMS(convert(smsContextParam.getMobiles()),
                smsContextParam.getSmsContent(),
                smsContextParam.getAddSerial(),
                smsContextParam.getSmsPriority(),
                send.getSendSign(),
                smsContextParam.getMsgGroup(), smsContextParam.isMo());
        SendResultType sendResultType = SendResultType.getType(status);
        if (sendResultType == null) {
            sendResultType = SendResultType.UNRECOGNIZED_ERROR_NUMBER;
        }
        return sendResultType;
    }

    public SendResultType send(SmsTemplatedParam smsTemplatedParam) {
        int status = client.sendDTMS(convert(smsTemplatedParam.getMobiles()),
                smsTemplatedParam.getAddSerial(),
                smsTemplatedParam.getSmsPriority(),
                send.getSendSign(),
                smsTemplatedParam.getMsgGroup(), smsTemplatedParam.isMo(),
                smsTemplatedParam.getTempId(), convert(smsTemplatedParam.getParams()));
        SendResultType sendResultType = SendResultType.getType(status);
        if (sendResultType == null) {
            sendResultType = SendResultType.UNRECOGNIZED_ERROR_NUMBER;
        }
        return sendResultType;
    }

    public void logout() {
        client.logout();
        List<MoModel> moList = client.getMO();
        for (MoModel mo : moList) {
            // 业务处理代码
            logger.warn(String.format("logout.mo=%s", JsonUtils.toJson(mo)));
        }

        List<SubmitReportModel> subRptList = client.getSubmitReport();
        for (SubmitReportModel subRpt : subRptList) {
            // 业务处理代码
            logger.warn(String.format("logout.submit=%s", JsonUtils.toJson(subRpt)));
        }

        List<StatusReportModel> ssRptList = client.getReport();
        for (StatusReportModel ssRpt : ssRptList) {
            // 业务处理代码
            logger.warn(String.format("logout.status=%s", JsonUtils.toJson(ssRpt)));
        }
    }

    private String[] convert(List<String> list) {
        return list.toArray(new String[list.size()]);
    }
}
