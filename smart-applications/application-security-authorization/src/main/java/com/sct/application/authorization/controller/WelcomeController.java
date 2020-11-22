package com.sct.application.authorization.controller;

import com.sct.commons.utils.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController extends BaseLoginController{
    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "application authorization welcome:" + System.currentTimeMillis();
    }

    private void appendMessages(Model model, List<String> messages) {
        Object object = model.getAttribute("messages");
        if (object == null) {
            model.addAttribute("messages", messages);
        } else {
            List<String> oldMessage = (List<String>) object;
            oldMessage.addAll(messages);
        }
    }

    @GetMapping("/welcome0")
    public void wellcome0(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = LocalDateTime.now().format(dateTimeFormatter);
        String port = String.format("remoteIp:%s,serverName:%s,serverPort=%s,localServerPort=%s,contextPath=%s,ServletPath=%s", WebUtil.getIpAddr(request), request.getServerName(), request.getServerPort(), request.getLocalPort(), request.getContextPath(), request.getServletPath());
        logger.info("=====welcome0," + port);
        List<String> messages = new ArrayList<>();
        messages.add("welcome0");
        messages.add(date);
        messages.add(port);

        appendMessages(model, messages);
        appendUserName(model);

        String redirectUrl = "/welcome1";

        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    @GetMapping("/welcome1")
    public String welcome1(Model model, HttpServletRequest request) {
        String date = LocalDateTime.now().format(dateTimeFormatter);
        String port = String.format("remoteIp:%s,serverName:%s,serverPort=%s,localServerPort=%s,contextPath=%s,ServletPath=%s", WebUtil.getIpAddr(request), request.getServerName(), request.getServerPort(), request.getLocalPort(), request.getContextPath(), request.getServletPath());
        logger.info("=====welcome1," + port);
        List<String> messages = new ArrayList<>();
        messages.add("welcome1");
        messages.add(date);
        messages.add(port);
        appendMessages(model, messages);
        appendUserName(model);
        return "welcome2";
    }

    @GetMapping("/welcome2")
    public String welcome2(Model model, HttpServletRequest request) {
        String date = LocalDateTime.now().format(dateTimeFormatter);
        String port = String.format("remoteIp:%s,serverName:%s,serverPort=%s,localServerPort=%s,contextPath=%s,ServletPath=%s", WebUtil.getIpAddr(request), request.getServerName(), request.getServerPort(), request.getLocalPort(), request.getContextPath(), request.getServletPath());
        logger.info("=====welcome," + port);
        List<String> messages = new ArrayList<>();
        messages.add("auth server success");
        messages.add(date);
        messages.add(port);
        appendMessages(model, messages);
        appendUserName(model);
        return "welcome";
    }
}
