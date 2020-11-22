package com.sct.application.authorization.controller;


import com.sct.application.authorization.controller.tab.WebTab;
import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.util.LoginPageConstants;
import com.sct.application.authorization.util.SecurityServiceConstants;
import com.sct.service.core.web.rsa.RsaKeyGenerateUtil;
import com.sct.service.core.web.rsa.cache.RasKeyCacheInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginPageController extends BaseLoginController {
    private static Logger logger = LoggerFactory.getLogger(WelcomeLoginController.class);
    private String loginPageUrl;

    @Autowired
    private ILoginProperties iLoginProperties;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @PostConstruct
    public void init() {
        loginPageUrl = iLoginProperties.getLoginform().getLoginPageUrl();
        if (loginPageUrl.startsWith("/")) {
            loginPageUrl = loginPageUrl.substring(1);
        }
    }

    @GetMapping("/login-embed/loginView")
    public String loginView(Model model, HttpServletRequest request) {
        appendLoginPageAtt(model, request);
        return "login-embed/loginView";
    }


    @GetMapping("/login-error")
    public String loginError(Model model, HttpServletRequest request) {
        model.addAttribute("loginError", true);
        Object exception = request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Object info = null;
        if (exception == null || ((Exception) exception).getCause() instanceof NullPointerException) {
            info = getMessage("WelcomeLoginController.server_error");
        } else if (exception instanceof SessionAuthenticationException) {
            info = getMessage("WelcomeLoginController.user_alread_login");
        } else {
            info = exception;
        }
        if (info instanceof Exception) {
            model.addAttribute("errorInfo", ((Exception) info).getMessage());
        } else {
            model.addAttribute("errorInfo", info);
        }
        appendUserName(model);
        return detLoginPageFunction(model, request);
    }

    private String detLoginPageFunction(Model model, HttpServletRequest request) {
        if (loginPageUrl.endsWith("login-embed/loginView")) {
            return loginView(model, request);
        } else {
            return loginView(model, request);
        }
    }

    protected void appendLoginPageAtt(Model model, HttpServletRequest request) {
        appendUserName(model);

        String redirectUrl = SecurityServiceConstants.parserIframeUrl(request);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(redirectUrl)) {
//            String basePath = WebUtil.getBasePath(request);
//            if (!redirectUrl.startsWith(basePath)) {
            model.addAttribute(SecurityServiceConstants.IFRAME_SAVE_REQUST_LOGIN_URL_WEB_ID, redirectUrl);
//            }
        }
        //登录方式的tab设置,登录选项卡切换,以及对应的登录提交信息设置
        loginTabAppend(model, request);
    }

    private void loginTabAppend(Model model, HttpServletRequest request) {
        appendCommonLoginTips(model);
        List<WebTab> webTabs = new ArrayList<>();
        {
            //用户密码登录
            if (iLoginProperties.getPassword().isEnable()) {
                WebTab webTab = new WebTab();
                webTab.setActive(iLoginProperties.getPassword().isActive());
                webTab.setHref("#login_user");
                webTab.setTabId("login_user");
                String login_tip = getMessage("WelcomeLoginController.login_user_tip");
                webTab.setTipText(login_tip);
                webTabs.add(webTab);
                model.addAttribute("login_user", true);
                model.addAttribute(String.format("%s_tab_class", "login_user"), "tab-pane fade");
                model.addAttribute("box_Username", getMessage("WelcomeLoginController.box_Username"));
                model.addAttribute("box_Password", getMessage("WelcomeLoginController.box_Password"));
                if (iLoginProperties.getCaptchaCode().isEnable()) {
                    model.addAttribute("box_verifyCode", getMessage("WelcomeLoginController.box_verifyCode"));
                }

                loginPWDSecurity(model);
            } else {
                model.addAttribute("login_user", false);
                model.addAttribute(String.format("%s_tab_class", "login_user"), "tab-pane fade");
            }
        }
        {
            //短信登录
            if (iLoginProperties.getSms().isEnable()) {
                WebTab webTab = new WebTab();
                webTab.setActive(iLoginProperties.getSms().isActive());
                webTab.setHref("#login_sms");
                webTab.setTabId("login_sms");
                String login_tip = getMessage("WelcomeLoginController.login_sms_tip");
                webTab.setTipText(login_tip);
                webTabs.add(webTab);
                model.addAttribute("login_sms", true);
                model.addAttribute("login_sms_tip", getMessage("WelcomeLoginController.login_sms_tip"));
            } else {
                model.addAttribute("login_sms", false);
            }
            model.addAttribute("loginMobile_PhoneTip", getMessage("WelcomeLoginController.loginMobile.PhoneTip"));
            model.addAttribute("loginMobile_PhoneTip_Value", getMessage("WelcomeLoginController.loginMobile.PhoneTip_Value"));
            model.addAttribute("loginMobile_SmsCodeTip", getMessage("WelcomeLoginController.loginMobile.SmsCodeTip"));
            model.addAttribute("loginMobile_SendSmsCodeTip", getMessage("WelcomeLoginController.loginMobile.SendSmsCodeTip"));
            model.addAttribute(String.format("%s_tab_class", "login_sms"), "tab-pane fade");
        }

        appendScreenCommonAttribute(model);

        //核查webTabs中的信息
        //1. webTabs 不能为空
        if (webTabs.size() == 0) {
            logger.error(String.format(getMessage("LoginType.login_type_must_setting")));
            throw new RuntimeException(getMessage("LoginType.login_type_must_setting"));
        }

        //2. webTabs 至少有且只能有一个的active值为true
        //当有多种方式都为active时,只默认第一个acitve生效
        boolean hasActive = false;
        for (WebTab webTab : webTabs) {
            if (hasActive) {
                webTab.setActive(false);
            }
            if (webTab.isActive()) {
                hasActive = true;
            }
            webTab.setActive(webTab.isActive());
            model.addAttribute(String.format("%s_tab_class", webTab.getTabId()), webTab.getTabContentActiveClass());
        }
        if (!hasActive) {
            WebTab webTab = webTabs.get(0);
            webTab.setActive(true);
            model.addAttribute(String.format("%s_tab_class", webTab.getTabId()), webTab.getTabContentActiveClass());
        }
        model.addAttribute("LONGIN_TAB", webTabs);
    }

    private void loginPWDSecurity(Model model) {
        if (iLoginProperties.getPassword().isEnable()) {
            try {
                //生成密钥对(公钥和私钥)
                RasKeyCacheInfo rasKeyCacheInfo = RsaKeyGenerateUtil.createRasKeyCacheInfo();
                RsaKeyGenerateUtil.cacheSimpleObject(rasKeyCacheInfo, iLoginProperties.getCaptchaCode().getTimeout());
                //传输公钥给前端用户
                model.addAttribute(LoginPageConstants.login_password_encrypt_public_key_for_pageid, rasKeyCacheInfo.getPublicKey());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void appendScreenCommonAttribute(Model model) {
        model.addAttribute("screenSmsCodeSendMobile_not_null", getMessage("screen.sms.code.send.mobile_not_null"));
        model.addAttribute("screenSmsCodeSendError", getMessage("screen.sms.code.send.error"));
    }
}
