package com.sct.service.database.extend;

public class ScPartyActivitiesDisplay extends BaseExtend implements ExtendEntity {
    private String partyIdExt;
    private String topicIdExt;

    public String getPartyIdExt() {
        return partyIdExt;
    }

    public void setPartyIdExt(String partyIdExt) {
        this.partyIdExt = partyIdExt;
    }

    public String getTopicIdExt() {
        return topicIdExt;
    }

    public void setTopicIdExt(String topicIdExt) {
        this.topicIdExt = topicIdExt;
    }
}
