package com.sct.service.database.condition;

import java.util.Date;
import java.util.List;

public class ScPartyMemberRecordCondition implements ConditionQuery {
    private Integer partyMemberId;
    private Date recordDateStart;
    private Date recordDateEnd;
    private List<Integer> recordTypes;

    public static ScPartyMemberRecordCondition of() {
        return new ScPartyMemberRecordCondition();
    }


    public Integer getPartyMemberId() {
        return partyMemberId;
    }

    public void setPartyMemberId(Integer partyMemberId) {
        this.partyMemberId = partyMemberId;
    }

    public Date getRecordDateStart() {
        return recordDateStart;
    }

    public void setRecordDateStart(Date recordDateStart) {
        this.recordDateStart = recordDateStart;
    }

    public Date getRecordDateEnd() {
        return recordDateEnd;
    }

    public void setRecordDateEnd(Date recordDateEnd) {
        this.recordDateEnd = recordDateEnd;
    }

    public List<Integer> getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(List<Integer> recordTypes) {
        this.recordTypes = recordTypes;
    }
}
