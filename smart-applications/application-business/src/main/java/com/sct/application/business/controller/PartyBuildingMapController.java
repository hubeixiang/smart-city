package com.sct.application.business.controller;

import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScPartyMemberCondition;
import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.database.mapper.ScCommunityPartyMapper;
import com.sct.service.database.mapper.ScPartyMemberMapper;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "社区党建->党建地图")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/party/map", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/part/map",})
@RestController
public class PartyBuildingMapController {

    @Autowired
    private ScCommunityPartyMapper scCommunityPartyMapper;

    @Autowired
    private ScPartyMemberMapper scPartyMemberMapper;

    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        //党组织数据
        List<ScCommunityParty> parties = scCommunityPartyMapper.selectAll();
        //党组织成员数据
        int members = scPartyMemberMapper.selectConditionCount(ScPartyMemberCondition.of());
        //组织生活次数（近12月）
        //党课学习次数（近12月）
        //志愿服务次数（近12月）

        return SimpleResourceResponse.of("ok");
    }


}
