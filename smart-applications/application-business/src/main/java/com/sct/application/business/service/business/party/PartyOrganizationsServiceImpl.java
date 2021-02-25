package com.sct.application.business.service.business.party;

import com.sct.application.business.dto.ScCommunityPartyAll;
import com.sct.commons.file.location.FileLocation;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScCommunityLeaderCondition;
import com.sct.service.database.condition.ScCommunityPartyCondition;
import com.sct.service.database.condition.ScCommunityPartyConditionExport;
import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.main.ScCommunityLeaderImpl;
import com.sct.service.main.ScCommunityPartyImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private Integer generateId(String ...args){
        StringBuffer sb = new StringBuffer("%s");
        for (int i=1;i<args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }



    // 分页查询社区领导班子
    public PageResultVO listCommunityLeaderPage(PageRecord paging, ScCommunityLeaderCondition condition) {
        return scCommunityLeaderImpl.listPage(paging, condition);
    }

    // 创建领导班子成员
    public Integer createLeader(ScCommunityLeader body) {
        body.setId(this.generateId(body.getName(),String.valueOf(body.getCommunityId()),body.getMobile()));
        return scCommunityLeaderImpl.insert(body);
    }

    // 更新领导班子
    public int updateLeader(ScCommunityLeader scCommunityLeader) {
        return scCommunityLeaderImpl.update(scCommunityLeader);
    }

    // 删除领导
    public int deleteLeader(Integer id) {
        return scCommunityLeaderImpl.delete(id);
    }

    // 批量删除领导
    public int deleteLeader(List<Integer> ids) {
        if (org.springframework.util.CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scCommunityLeaderImpl.deletes(ids);
    }

    // 领导详情
    public ScCommunityLeader partyOrganizationsLeaderDetail(Integer id) {
        return scCommunityLeaderImpl.select(id);
    }
}
