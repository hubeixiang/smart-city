package com.sct.application.authorization.log;

import com.sct.commons.utils.web.WebUtil;
import com.sct.service.database.dict.SsoLogOperationStatus;
import com.sct.service.database.dict.SsoLogType;
import com.sct.service.database.entity.ScSsoLog;
import com.sct.service.main.ScSsoLogServiceImpl;
import com.sct.service.users.security.SecurityUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class AuthorizationEndpointAspect {
    private static Logger logger = LoggerFactory.getLogger(AuthorizationEndpointAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ScSsoLogServiceImpl scSsoLogService;

    /**
     * 环绕通知一般会有返回值 ，Object
     *
     * @param joinPoint 参数用于 对目标执行环绕后，需提供一个返回值
     * @return
     */
    @Around(value = "oauthAuthorize()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean isOk = false;
        ScSsoLog scSsoLog = new ScSsoLog();
        try {
            Object[] objects = joinPoint.getArgs();
            Map<String, Object> model = (Map<String, Object>) objects[0];
            Map<String, String> parameters = (Map<String, String>) objects[1];
            SessionStatus sessionStatus = (SessionStatus) objects[2];
            Principal principal = (Principal) objects[3];

            Set<String> responseTypes = OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.RESPONSE_TYPE));
            if (isTokenResponse(responseTypes)) {
                scSsoLog.setSsoLogType(SsoLogType.CREATE_TICKET.getId());
            } else if (isAuthCodeResponse(responseTypes)) {
                scSsoLog.setSsoLogType(SsoLogType.AUTHORIZATION_CODE.getId());
            }
            String clientId = parameters.get(OAuth2Utils.CLIENT_ID);
            String grantType = parameters.get(OAuth2Utils.GRANT_TYPE);
            scSsoLog.setSsoServiceId(String.format("Oauth:%s,grantType:%s,authorize", clientId, grantType));
            scSsoLog.setUserId(SecurityUserUtil.findAuthenticationUserId(principal));
            scSsoLog.setRequestIp(WebUtil.getIpAddr(request));

            Object target = joinPoint.getTarget();  //拿到被增强目标类（就是所有xxxController类）对象
            String className = target.getClass().getName(); //拿到被增强类名字

            String methodName = joinPoint.getSignature().getName();//拿到 实际被增强方法的签名
            logger.info(className + "." + methodName);
            Object result = joinPoint.proceed();
            isOk = true;
            return result;
        } finally {
            if (isOk) {
                scSsoLog.setOperationStatus(SsoLogOperationStatus.SUCCESS.getId());
            } else {
                scSsoLog.setOperationStatus(SsoLogOperationStatus.FAILED.getId());
            }
            if (StringUtils.isNotEmpty(scSsoLog.getUserId())) {
                scSsoLogService.insert(scSsoLog);
            }
        }
    }

    @Pointcut(value = "execution(* org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.authorize(..))")
    private void oauthAuthorize() {
    }

    private boolean isTokenResponse(Set<String> responseTypes) {
        return responseTypes.contains("token");
    }

    private boolean isAuthCodeResponse(Set<String> responseTypes) {
        return responseTypes.contains("code");
    }
}
