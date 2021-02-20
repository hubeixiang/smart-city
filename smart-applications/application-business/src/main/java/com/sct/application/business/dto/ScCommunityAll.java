package com.sct.application.business.dto;

import com.sct.service.database.entity.ScCommunity;
import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.database.entity.ScCommunityParty;

import java.io.Serializable;
import java.util.List;

public class ScCommunityAll implements Serializable {
    private ScCommunity scCommunity;
    private ScCommunityParty scCommunityParty;

    public List<ScCommunityLeader> getScCommunityLeaderList() {
        return ScCommunityLeaderList;
    }

    public void setScCommunityLeaderList(List<ScCommunityLeader> scCommunityLeaderList) {
        ScCommunityLeaderList = scCommunityLeaderList;
    }

    private List<ScCommunityLeader> ScCommunityLeaderList;

    public ScCommunityAll() {
    }

    public ScCommunityAll(ScCommunity scCommunity, ScCommunityParty scCommunityParty, List<ScCommunityLeader> scCommunityLeaderList) {
        this.scCommunity = scCommunity;
        this.scCommunityParty = scCommunityParty;
        ScCommunityLeaderList = scCommunityLeaderList;
    }

    public ScCommunity getScCommunity() {
        return scCommunity;
    }

    public void setScCommunity(ScCommunity scCommunity) {
        this.scCommunity = scCommunity;
    }

    public ScCommunityParty getScCommunityParty() {
        return scCommunityParty;
    }

    public void setScCommunityParty(ScCommunityParty scCommunityParty) {
        this.scCommunityParty = scCommunityParty;
    }
}
