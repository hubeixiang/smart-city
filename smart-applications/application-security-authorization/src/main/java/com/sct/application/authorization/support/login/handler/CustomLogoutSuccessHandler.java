package com.sct.application.authorization.support.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.additional.LoginAdditional;
import com.sct.application.authorization.support.additional.LoginType;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.service.users.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理
 */
@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    private boolean useReferer = false;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomSessionRegistry customSessionRegistry;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ILoginProperties iLoginProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomLogoutSuccessHandler() {
    }

    @PostConstruct
    public void init() {
        //        String defaultTargetUrl = "/login?logout";
        String defaultTargetUrl = iLoginProperties.getLoginform().getLoginPageUrl();
        defaultTargetUrl = String.format("%s?logout", defaultTargetUrl);
        //登出成功时重定向的url
        setDefaultTargetUrl(defaultTargetUrl);
        //退出时给定了退出调转的地址
        setTargetUrlParameter("service");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws ServletException, IOException {
        insertLoginLog(request, authentication);
        // 登录成功后，进行数据处理
        if (authentication != null) {
            System.out.println("用户登出成功啦");
            String authenticationStr = objectMapper.writeValueAsString(authentication);
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                authenticationStr = "用户密码登录:" + authenticationStr;
            }
            System.out.println("用户登出信息打印：" + authenticationStr);

        } else {
            System.out.println("session超时用户登出");
        }

        customSessionRegistry.logoutSessionRegistry(request, response, authentication);
        LoginAdditional loginAdditional = getCurrentLoginType(authentication);
        if (isLogout_for_current(loginAdditional, LoginType.nmhighwaycas)) {
            //TODO 没有这种方式
        } else {
            super.onLogoutSuccess(request, response, authentication);
        }
    }

    private LoginAdditional getCurrentLoginType(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SecurityUser) {
                SecurityUser securityUser = (SecurityUser) principal;
                Object loginAdditional = securityUser.getLoginAdditional();
                if (loginAdditional != null && loginAdditional instanceof LoginAdditional) {
                    LoginAdditional loginType = (LoginAdditional) loginAdditional;
                    return loginType;
                }
            }
        }
        return null;
    }

    private boolean isLogout_for_current(LoginAdditional loginAdditional, LoginType specLoginType) {
        if (loginAdditional != null) {
            LoginType currentLoginType = loginAdditional.getLoginType();
            if (currentLoginType != null && currentLoginType.equals(specLoginType)) {
                return true;
            }
        }
        return false;
    }

    private void insertLoginLog(HttpServletRequest request, Authentication authentication) {
//        SecUserSsoLog secUserSsoLog = new SecUserSsoLog();
//        String userId = WebUtil.getPrincipalUser(request, authentication);
//        if (StringUtils.isEmpty(userId)) {
//            return;
//        }
//        secUserSsoLog.setUserId(userId);
//        secUserSsoLog.setRequestIp(WebUtil.getIpAddr(request));
//        secUserSsoLog.setSsoLogType(SsoLogType.LOGOUT.getId());
//        secUserSsoLog.setSsoServiceId(null);
//        secUserSsoLog.setOperationStatus(SsoLogOperationStatus.SUCCESS.getId());
//        hiosUserSsoLogService.insertSecUserSsoLog(secUserSsoLog);
    }


}