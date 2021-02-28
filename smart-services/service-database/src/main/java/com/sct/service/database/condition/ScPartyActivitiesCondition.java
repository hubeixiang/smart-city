package com.sct.service.database.condition;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ScPartyActivitiesCondition implements ConditionQuery {
    private List<Integer> ids;
    private String title;
    private List<Integer> topicIdList;
    private List<Integer> partyIdList;
    private List<Integer> activityStatusList;
    private String creatorName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    public static ScPartyActivitiesCondition of() {
        return new ScPartyActivitiesCondition();
    }

    public void checkSQLinjectionException() {
        checkSQLinjectionException(getTitle());
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getTopicIdList() {
        return topicIdList;
    }

    public void setTopicIdList(List<Integer> topicIdList) {
        this.topicIdList = topicIdList;
    }

    public List<Integer> getPartyIdList() {
        return partyIdList;
    }

    public void setPartyIdList(List<Integer> partyIdList) {
        this.partyIdList = partyIdList;
    }

    public List<Integer> getActivityStatusList() {
        return activityStatusList;
    }

    public void setActivityStatusList(List<Integer> activityStatusList) {
        this.activityStatusList = activityStatusList;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
