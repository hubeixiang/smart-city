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
@Api(tags = "社区治理->矛盾纠纷")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/community/conflict", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/community/conflict",})
@RestController
public class CommunityConflictController {
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("ok");
    }


}
