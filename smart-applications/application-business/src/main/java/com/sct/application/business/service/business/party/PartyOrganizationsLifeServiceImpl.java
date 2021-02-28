package com.sct.application.business.service.business.party;

import com.sct.application.business.service.IntIdGeneratorUtils;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScPartyActivitiesCondition;
import com.sct.service.database.condition.ScPartyActivitiesConditionExport;
import com.sct.service.database.condition.ScPartyTopicCondition;
import com.sct.service.database.condition.ScPartyTopicConditionExport;
import com.sct.service.database.entity.ScPartyActivities;
import com.sct.service.database.entity.ScPartyTopic;
import com.sct.service.main.PartyActivitiesImpl;
import com.sct.service.main.PartyTopicImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyOrganizationsLifeServiceImpl {

    @Autowired
    private PartyTopicImpl partyTopicImpl;

    @Autowired
    private PartyActivitiesImpl partyActivitiesImpl;

    public PageResultVO listPage(PageRecord paging, ScPartyTopicCondition condition) {
        return partyTopicImpl.listPage(paging, condition);
    }

    public ResultVOEntity list(ScPartyTopicCondition condition) {
        return partyTopicImpl.list(condition);
    }

    public FileLocation export2FileLocation(ScPartyTopicConditionExport conditionExport) {
        return partyTopicImpl.export2FileLocation(conditionExport);
    }

    public FileLocation export2FileLocationActivities(ScPartyActivitiesConditionExport conditionExport) {
        return partyActivitiesImpl.export2FileLocation(conditionExport);
    }

    /**
     * 查询党员相关全纪实
     *
     * @param
     * @return
     */
    public ResultVOEntity overview(ScPartyTopicCondition scPartyTopicCondition) {
        return partyTopicImpl.overview(scPartyTopicCondition);
    }

    public ScPartyTopic create(ScPartyTopic scPartyTopic) {
        //新增组织数据时,需要填补相关的属性信息
        fillCreateInfo(scPartyTopic);
        return partyTopicImpl.insert(scPartyTopic);
    }

    public int delete(Integer id) {
        return partyTopicImpl.delete(id);
    }

    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return partyTopicImpl.deletes(ids);
    }

    public int update(ScPartyTopic scPartyTopic) {
        //更新组织信息前,需要填补相关的属性信息
        fillUpdateInfo(scPartyTopic);
        return partyTopicImpl.update(scPartyTopic);
    }

    private void fillCreateInfo(ScPartyTopic scPartyTopic) {
        scPartyTopic.setId(IntIdGeneratorUtils.generateId(String.valueOf(scPartyTopic.getPartyId()), String.valueOf(System.currentTimeMillis())));
    }

    private void fillUpdateInfo(ScPartyTopic scPartyTopic) {

    }

    //--具体活动处理

    public PageResultVO listPage(PageRecord paging, ScPartyActivitiesCondition condition) {
        return partyActivitiesImpl.listPage(paging, condition);
    }

    public ResultVOEntity list(ScPartyActivitiesCondition condition) {
        return partyActivitiesImpl.list(condition);
    }

    public FileLocation export2FileLocation(ScPartyActivitiesConditionExport conditionExport) {
        return partyActivitiesImpl.export2FileLocation(conditionExport);
    }

    public ScPartyActivities create(ScPartyActivities scPartyActivities) {
        //新增组织数据时,需要填补相关的属性信息
        fillCreateInfo(scPartyActivities);
        return partyActivitiesImpl.insert(scPartyActivities);
    }

    public int deleteAct(Integer id) {
        return partyActivitiesImpl.delete(id);
    }

    public int deleteAct(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return partyActivitiesImpl.deletes(ids);
    }

    public int update(ScPartyActivities scPartyActivities) {
        //更新组织信息前,需要填补相关的属性信息
        fillUpdateInfo(scPartyActivities);
        return partyActivitiesImpl.update(scPartyActivities);
    }

    private void fillCreateInfo(ScPartyActivities scPartyActivities) {
        scPartyActivities.setId(IntIdGeneratorUtils.generateId(String.valueOf(scPartyActivities.getTopicId()), String.valueOf(System.currentTimeMillis())));
    }

    private void fillUpdateInfo(ScPartyActivities scPartyMember) {

    }
}
