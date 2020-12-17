package com.sct.application.security.controller;

import com.sct.service.oauth2.core.constants.Oauth2Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth, "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path})
@RestController
public class WelcomeController extends BaseController {

    @GetMapping("/welcome")
    public String welcome() {
        return "application rest welcome:" + System.currentTimeMillis();
    }
}
