package com.sct.application.authorization.support.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.commons.utils.web.WebUtil;
import com.sct.service.database.dict.SsoLogOperationStatus;
import com.sct.service.database.dict.SsoLogType;
import com.sct.service.database.entity.ScSsoLog;
import com.sct.service.main.ScSsoLogServiceImpl;
import com.sct.service.users.security.SecurityUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessAudit {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationSuccessAudit.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ScSsoLogServiceImpl scSsoLogService;

    @Autowired
    private CustomSessionRegistry customSessionRegistry;

    public void successAudit(HttpServletRequest request,
                             HttpServletResponse response,
                             Authentication authentication) throws JsonProcessingException {
        // 登录成功后，进行数据处理
//        String authenticationStr = objectMapper.writeValueAsString(authentication);
//        System.out.println("用户登录成功:" + authenticationStr);
        //插入登录日志
        insertSuccessLoginLog(request, authentication);
        //登录成功记录session相关信息
        customSessionRegistry.loginSuccessSessionRegistry(request, response, authentication);
    }

    private void insertSuccessLoginLog(HttpServletRequest request, Authentication authentication) {
        String userId = SecurityUserUtil.findAuthenticationUserId(authentication);
        if (StringUtils.isEmpty(userId)) {
            logger.error(String.format("login authentication success .but can't find userId!"));
            return;
        }
        ScSsoLog scSsoLog = new ScSsoLog();
        scSsoLog.setUserId(userId);
        scSsoLog.setRequestIp(WebUtil.getIpAddr(request));
        scSsoLog.setSsoLogType(SsoLogType.LOGIN.getId());
        scSsoLog.setSsoServiceId(null);
        scSsoLog.setOperationStatus(SsoLogOperationStatus.SUCCESS.getId());
        scSsoLogService.insert(scSsoLog);
    }

    private void insertFailureLoginLog(HttpServletRequest request, Authentication authentication, AuthenticationException exception) {
//        SecUserSsoLog secUserSsoLog = new SecUserSsoLog();
//        String userId = SecurityUserUtil.getPrincipalUser(authentication);
//        if (StringUtils.isEmpty(userId)) {
//            return;
//        }
//        secUserSsoLog.setUserId(userId);
//        secUserSsoLog.setRequestIp(WebUtil.getIpAddr(request));
//        secUserSsoLog.setSsoLogType(SsoLogType.LOGIN.getId());
//        secUserSsoLog.setSsoServiceId(null);
//        secUserSsoLog.setOperationStatus(SsoLogOperationStatus.FAILED.getId());
//        String message = exception.getMessage();
//        if (message.length() > 200) {
//            message = message.substring(0, 200);
//        }
//        secUserSsoLog.setRequestUserAgent(message);
//        hiosUserSsoLogService.insertSecUserSsoLog(secUserSsoLog);
    }
}
