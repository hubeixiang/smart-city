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
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class CheckTokenEndpointAspect {
    private static Logger logger = LoggerFactory.getLogger(CheckTokenEndpointAspect.class);

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
    @Around(value = "checkToken()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean isOk = false;
        ScSsoLog scSsoLog = new ScSsoLog();
        try {
            Object[] objects = joinPoint.getArgs();
            String token = (String) objects[0];

            scSsoLog.setSsoLogType(SsoLogType.VALIDATE_TICKET.getId());
            scSsoLog.setSsoServiceId("checkToken");
            scSsoLog.setUserId(SecurityUserUtil.findAuthenticationUserId(null));
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

    @Pointcut(value = "execution(* org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint.checkToken(..))")
    private void checkToken() {
    }
}
