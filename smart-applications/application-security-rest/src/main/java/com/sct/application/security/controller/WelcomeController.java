package com.sct.application.security.controller;

import com.sct.service.oauth2.core.constants.Oauth2Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "application rest welcome:" + System.currentTimeMillis();
    }

    @GetMapping("/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/welcome")
    public String resourceserverWelcome() {
        return "application resource server rest welcome:" + System.currentTimeMillis();
    }
}
