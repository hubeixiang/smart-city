package com.sct.application.business.dto;

import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.database.entity.ScCommunityParty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "ScCommunityPartyAll", description = "社区党组织全部信息")
public class ScCommunityPartyAll implements Serializable {
    @ApiModelProperty(value = "社区党组织信息")
    private ScCommunityParty scCommunityParty;
    @ApiModelProperty(value = "社区领导班子列表")
    private List<ScCommunityLeader> ScCommunityLeaderList;

    public ScCommunityPartyAll() {
    }

    public ScCommunityPartyAll(ScCommunityParty scCommunityParty, List<ScCommunityLeader> scCommunityLeaderList) {
        this.scCommunityParty = scCommunityParty;
        ScCommunityLeaderList = scCommunityLeaderList;
    }

    public static ScCommunityPartyAll of() {
        return new ScCommunityPartyAll();
    }

    public List<ScCommunityLeader> getScCommunityLeaderList() {
        return ScCommunityLeaderList;
    }

    public void setScCommunityLeaderList(List<ScCommunityLeader> scCommunityLeaderList) {
        ScCommunityLeaderList = scCommunityLeaderList;
    }

    public ScCommunityParty getScCommunityParty() {
        return scCommunityParty;
    }

    public void setScCommunityParty(ScCommunityParty scCommunityParty) {
        this.scCommunityParty = scCommunityParty;
    }
}
