package com.sct.application.business.controller;

import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Api(tags = "社区治理->应急管理->领导机构")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/community/emergency/leader", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/community/emergency/leader",})
@RestController
public class CommunityEmergencyLeadershipController {
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("ok");
    }


}
