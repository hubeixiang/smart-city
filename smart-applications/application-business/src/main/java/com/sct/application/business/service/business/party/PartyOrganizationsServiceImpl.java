package com.sct.application.business.service.business.party;

import com.sct.application.business.dto.ScCommunityPartyAll;
import com.sct.commons.file.location.FileLocation;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScCommunityPartyCondition;
import com.sct.service.database.condition.ScCommunityPartyConditionExport;
import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.main.ScCommunityLeaderImpl;
import com.sct.service.main.ScCommunityPartyImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyOrganizationsServiceImpl {

    @Autowired
    private ScCommunityPartyImpl scCommunityPartyImpl;
    @Autowired
    private ScCommunityLeaderImpl scCommunityLeaderImpl;

    public PageResultVO listPage(PageRecord paging, ScCommunityPartyCondition condition) {
        return scCommunityPartyImpl.listPage(paging, condition);
    }

    public ResultVOEntity list(ScCommunityPartyCondition condition) {
        return scCommunityPartyImpl.list(condition);
    }

    public ScCommunityPartyAll partyOrganizationsDetail(Integer id) {
        ScCommunityPartyAll scCommunityPartyAll = ScCommunityPartyAll.of();
        //先查询党组织信息
        ScCommunityParty scCommunityParty = scCommunityPartyImpl.select(id);
        scCommunityPartyAll.setScCommunityParty(scCommunityParty);
        //在查询党组织领导班子详情
        Integer communityId = scCommunityParty.getCommunityId();
        if (communityId != null) {
            scCommunityPartyAll.setScCommunityLeaderList(scCommunityLeaderImpl.selectByCommunityId(communityId));
        }
        return scCommunityPartyAll;
    }

    public FileLocation export2FileLocation(ScCommunityPartyConditionExport conditionExport) {
        return scCommunityPartyImpl.export2FileLocation(conditionExport);
    }

    public ScCommunityParty create(ScCommunityParty scCommunityParty) {
        //新增组织数据时,需要填补相关的属性信息
        fillCreateInfo(scCommunityParty);
        return scCommunityPartyImpl.insert(scCommunityParty);
    }

    public int delete(Integer id) {
        return scCommunityPartyImpl.delete(id);
    }

    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scCommunityPartyImpl.deletes(ids);
    }

    public int update(ScCommunityParty scCommunityParty) {
        //更新组织信息前,需要填补相关的属性信息
        fillUpdateInfo(scCommunityParty);
        return scCommunityPartyImpl.update(scCommunityParty);
    }

    private void fillCreateInfo(ScCommunityParty scCommunityParty) {
        String idstr = String.format("%s", scCommunityParty.getName());
        scCommunityParty.setId(IntIdGenerator.getCRC32(idstr));
    }

    private void fillUpdateInfo(ScCommunityParty scOrganization) {

    }
}
