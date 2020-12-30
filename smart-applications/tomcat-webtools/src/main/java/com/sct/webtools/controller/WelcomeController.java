package com.sct.webtools.controller;

import com.sct.webtools.response.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口")
@RestController
public class WelcomeController {
    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @ApiOperation(
            value = "测试服务可用性",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/test",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> test() {
        ResponseEntity<?> responseEntity = null;
        try {
            logger.info("[test]");
            ResultEntity result = ResultEntity.of(ResultEntity.Code.SUCCESS, "服务可用");
            responseEntity = ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("[test] error:{}", ExceptionUtils.getStackTrace(e));
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return responseEntity;
    }

}
