package com.sct.application.authorization.controller;

import com.sct.service.core.web.captcha.CaptchaCodeInfo;
import com.sct.service.core.web.captcha.CaptchaCodeUtil;
import com.sct.service.core.web.smscode.SmsGenerateUtil;
import com.sct.service.core.web.smscode.cache.SmsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Controller
public class WelcomeLoginController extends BaseLoginController {
    private static Logger logger = LoggerFactory.getLogger(WelcomeLoginController.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String loginPageUrl;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @PostConstruct
    public void init() {
        loginPageUrl = iLoginProperties.getLoginform().getLoginPageUrl();
        if (loginPageUrl.startsWith("/")) {
            loginPageUrl = loginPageUrl.substring(1);
        }
    }

    @GetMapping("anonymous/online")
    @ResponseBody
    public int onlineUsers() {
        return customSessionRegistry.online();
    }

    @GetMapping("anonymous/online/details")
    @ResponseBody
    public Object onlineUsersDetails() {
        return customSessionRegistry.onlineUsersDetails();
    }

    @GetMapping("session/invalid")
    public String sessionInvalid() {
        return "redirect:/index";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        String date = DateUtils.format(new Date(), Locale.CHINA);
        String[] messages = new String[]{"index auth server success", date};
        model.addAttribute("messages", messages);
        appendUserName(model);
        appendOauthUrl(model, request);
        String tips = getMessage("WelcomeLoginController.online_user_tips");
        int count = onlineUsers();
        model.addAttribute("online_user_tips", String.format("%s(%s)", tips, count));
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        appendUserName(model);
        String date = LocalDateTime.now().format(dateTimeFormatter);
        model.addAttribute("online_user_tips", getMessage("WelcomeLoginController.online_user_tips"));
        model.addAttribute("current_date", date);
        return "home";
    }

    @GetMapping("/code/captcha")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CaptchaCodeInfo captchaCodeInfo = CaptchaCodeUtil.createVerifyCodeInfo();
        CaptchaCodeUtil.cacheSimpleObject(captchaCodeInfo, iLoginProperties.getCaptchaCode().getTimeout());
        CaptchaCodeUtil.outputPicture(captchaCodeInfo.getBufferedImage(), resp.getOutputStream());
    }

    @GetMapping("/code/sms")
    @ResponseBody
    public String createSmsCode(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        if (org.apache.commons.lang3.StringUtils.isEmpty(mobile)) {
            throw new RuntimeException(getMessage("screen.sms.code.send.mobile_not_null"));
        }
        SmsInfo smsInfo = SmsGenerateUtil.createSmsCode(mobile);
        SmsGenerateUtil.cacheSimpleObject(smsInfo, iLoginProperties.getSms().getTimeout());

        String tipSms = iLoginProperties.getSms().getMobileMessageFormat();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(tipSms)) {
            tipSms = String.format(tipSms, smsInfo.getCode());
        } else {
            tipSms = smsInfo.getCode();
        }
        return tipSms;
    }


    @GetMapping("/error")
    public String error(Model model, HttpServletRequest request) {
        model.addAttribute("error", true);
        Object exception = request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Object info = null;
        if (exception instanceof SessionAuthenticationException) {
            info = getMessage("WelcomeLoginController.user_alread_login");
        } else {
            info = exception;
        }
        if (info instanceof Exception) {
            model.addAttribute("errorInfo", ((Exception) info).getMessage());
        } else {
            model.addAttribute("errorInfo", info);
        }
        appendCommonLoginTips(model);
        return "error";
    }


}
