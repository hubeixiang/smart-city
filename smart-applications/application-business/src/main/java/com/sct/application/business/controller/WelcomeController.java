package com.sct.application.business.controller;

import com.sct.service.core.api.service.file.ServiceFileLocationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private ServiceFileLocationApi serviceFileLocationApi;

    @Qualifier("authorityUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/welcome")
    public String welcome() {
        return "application business welcome:" + System.currentTimeMillis();
    }

    @GetMapping("/loadUser")
    public UserDetails loadUserByUsername(String userName) {
        return userDetailsService.loadUserByUsername(userName);
    }
}
