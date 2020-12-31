package com.sct.sms.controller;

import com.sct.commons.web.core.response.HttpResultEntity;
import com.sct.sms.entity.CodeInfo;
import com.sct.sms.entity.SmsContextParam;
import com.sct.sms.entity.SmsTemplatedParam;
import com.sct.sms.exception.SmsLoginException;
import com.sct.sms.service.FactorySmsClient;
import com.sct.sms.service.SendResultType;
import com.sct.sms.service.SmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/send")
@RestController
public class SmsSendController extends AbstractController {
    public final static String addSerial_Verification_code = "add-serial-sct-sms-verification-code";
    public final static String msgGroup_Verification_code = "msg-group-sct-sms-verification-code";
    private static Logger logger = LoggerFactory.getLogger(SmsSendController.class);
    @Autowired
    private FactorySmsClient factorySmsClient;

    @PostMapping("/code")
    public ResponseEntity<?> postCode(@RequestBody CodeInfo codeInfo) {
        long startTime = System.currentTimeMillis();
        String path = String.format("post /sms/code");
        logger.info("{} receive , startTime: {}, param: {}", path, startTime, "");
        ResponseEntity<?> responseEntity = null;
        try {
            SmsClient smsClient = getSmsClient();
            SmsContextParam smsContextParam = SmsContextParam.of(codeInfo.getMobile(), codeInfo.getSmsContext(), addSerial_Verification_code, 5, msgGroup_Verification_code, false);
            SendResultType result = smsClient.send(smsContextParam);
//            SendResultType result = SendResultType.NOT_CERTIFIED;
            if (result.equals(SendResultType.SUCCESS)) {
                responseEntity = ResponseEntity.ok(HttpResultEntity.ok());
            } else {
                responseEntity = ResponseEntity.ok(HttpResultEntity.failure(result.getDescription(), null));
            }
            logger.info("{} response : {} startTime: {} cost: {} ms", path, result, startTime, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }

    @GetMapping("/code")
    public ResponseEntity<?> getCode(String mobile, String smsContext) {
        long startTime = System.currentTimeMillis();
        String path = String.format("get /sms/code");
        logger.info("{} receive , startTime: {}, param: {}", path, startTime, "");
        ResponseEntity<?> responseEntity = null;
        try {
            SmsClient smsClient = getSmsClient();
            SmsContextParam smsContextParam = SmsContextParam.of(mobile, smsContext, addSerial_Verification_code, 5, msgGroup_Verification_code, false);
            SendResultType result = smsClient.send(smsContextParam);
//            SendResultType result = SendResultType.NOT_CERTIFIED;
            if (result.equals(SendResultType.SUCCESS)) {
                responseEntity = ResponseEntity.ok(HttpResultEntity.ok());
            } else {
                responseEntity = ResponseEntity.ok(HttpResultEntity.failure(result.getDescription(), null));
            }
            logger.info("{} response : {} startTime: {} cost: {} ms", path, result, startTime, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }

    @PostMapping("/context")
    public ResponseEntity<?> sendSmsContext(@RequestBody SmsContextParam smsContextParam) {
        long startTime = System.currentTimeMillis();
        String path = String.format("/sms/context");
        logger.info("{} receive , startTime: {}, param: {}", path, startTime, "");
        ResponseEntity<?> responseEntity = null;
        try {
            SmsClient smsClient = getSmsClient();
            validSmsContextParam(smsContextParam);
            SendResultType result = smsClient.send(smsContextParam);
            if (result.equals(SendResultType.SUCCESS)) {
                responseEntity = ResponseEntity.ok(HttpResultEntity.ok());
            } else {
                responseEntity = ResponseEntity.ok(HttpResultEntity.failure(result.getDescription(), null));
            }
            logger.info("{} response : {} startTime: {} cost: {} ms", path, result, startTime, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }

    @PostMapping("/template")
    public ResponseEntity<?> sendSmsTemplatedContext(@RequestBody SmsTemplatedParam smsTemplatedParam) {
        long startTime = System.currentTimeMillis();
        String path = String.format("/sms/template");
        logger.info("{} receive , startTime: {}, param: {}", path, startTime, "");
        ResponseEntity<?> responseEntity = null;
        try {
            SmsClient smsClient = getSmsClient();
            validSmsTemplatedParam(smsTemplatedParam);
            SendResultType result = smsClient.send(smsTemplatedParam);
            if (result.equals(SendResultType.SUCCESS)) {
                responseEntity = ResponseEntity.ok(HttpResultEntity.ok());
            } else {
                responseEntity = ResponseEntity.ok(HttpResultEntity.failure(result.getDescription(), null));
            }
            logger.info("{} response : {} startTime: {} cost: {} ms", path, result, startTime, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("{} response error : code={} txt={} error:", path, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }

    private SmsClient getSmsClient() throws SmsLoginException {
        return factorySmsClient.getObject();
    }
}
