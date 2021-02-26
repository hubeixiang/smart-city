package com.sct.application.business.dto;

import com.sct.service.database.entity.ScPartyMember;
import com.sct.service.database.entity.ScPartyMemberRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "ScPartyMemberDocumentary", description = "党员纪实信息")
public class ScPartyMemberDocumentary {
    @ApiModelProperty(value = "党员信息")
    private ScPartyMember scPartyMember;
    @ApiModelProperty(value = "党员纪实信息")
    private List<ScPartyMemberRecord> scPartMemberRecordList;

    public static ScPartyMemberDocumentary of() {
        return new ScPartyMemberDocumentary();
    }

    public ScPartyMember getScPartyMember() {
        return scPartyMember;
    }

    public void setScPartyMember(ScPartyMember scPartyMember) {
        this.scPartyMember = scPartyMember;
    }

    public List<ScPartyMemberRecord> getScPartMemberRecordList() {
        return scPartMemberRecordList;
    }

    public void setScPartMemberRecordList(List<ScPartyMemberRecord> scPartMemberRecordList) {
        this.scPartMemberRecordList = scPartMemberRecordList;
    }
}
