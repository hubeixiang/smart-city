package com.sct.application.authorization.support.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.application.authorization.util.AuthorizationWebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessAudit {
    private ObjectMapper objectMapper = new ObjectMapper();

//    @Autowired
//    private HiosUserSsoLogService hiosUserSsoLogService;

    @Autowired
    private CustomSessionRegistry customSessionRegistry;

    public void successAudit(HttpServletRequest request,
                             HttpServletResponse response,
                             Authentication authentication) throws JsonProcessingException {
        // 登录成功后，进行数据处理
        String authenticationStr = objectMapper.writeValueAsString(authentication);
        System.out.println("用户登录成功:" + authenticationStr);
        //插入登录日志
        insertSuccessLoginLog(request, authentication);
        //登录成功记录session相关信息
        customSessionRegistry.loginSuccessSessionRegistry(request, response, authentication);
    }

    private void insertSuccessLoginLog(HttpServletRequest request, Authentication authentication) {
        String userId = AuthorizationWebUtil.getPrincipalUser(request, authentication);
        if (StringUtils.isEmpty(userId)) {
            return;
        }
//        SecUserSsoLog secUserSsoLog = new SecUserSsoLog();
//        secUserSsoLog.setUserId(userId);
//        secUserSsoLog.setRequestIp(WebUtil.getIpAddr(request));
//        secUserSsoLog.setSsoLogType(SsoLogType.LOGIN.getId());
//        secUserSsoLog.setSsoServiceId(null);
//        secUserSsoLog.setOperationStatus(SsoLogOperationStatus.SUCCESS.getId());
//        hiosUserSsoLogService.insertSecUserSsoLog(secUserSsoLog);
    }

    private void insertFailureLoginLog(HttpServletRequest request, Authentication authentication, AuthenticationException exception) {
//        SecUserSsoLog secUserSsoLog = new SecUserSsoLog();
//        String userId = WebUtil.getPrincipalUser(request, authentication);
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
