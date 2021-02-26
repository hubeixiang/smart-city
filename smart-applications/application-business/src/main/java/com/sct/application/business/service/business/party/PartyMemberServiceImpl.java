package com.sct.application.business.service.business.party;

import com.sct.application.business.dto.ScPartyMemberDocumentary;
import com.sct.commons.file.location.FileLocation;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScPartyMemberCondition;
import com.sct.service.database.condition.ScPartyMemberConditionExport;
import com.sct.service.database.entity.ScPartyMember;
import com.sct.service.database.entity.ScPartyMemberRecord;
import com.sct.service.main.PartyMemberImpl;
import com.sct.service.main.PartyMemberRecordImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 党员管理
 */
@Service
public class PartyMemberServiceImpl {

    @Autowired
    private PartyMemberImpl partyMemberImpl;

    @Autowired
    private PartyMemberRecordImpl partyMemberRecordImpl;

    public PageResultVO listPage(PageRecord paging, ScPartyMemberCondition condition) {
        return partyMemberImpl.listPage(paging, condition);
    }

    public ResultVOEntity list(ScPartyMemberCondition condition) {
        return partyMemberImpl.list(condition);
    }

    public FileLocation export2FileLocation(ScPartyMemberConditionExport conditionExport) {
        return partyMemberImpl.export2FileLocation(conditionExport);
    }

    /**
     * 查询党员相关全纪实
     *
     * @param partyMemberId 党员id
     * @return
     */
    public ScPartyMemberDocumentary detail(Integer partyMemberId) {
        //1. 查询党员信息
        ScPartyMember scPartyMember = partyMemberImpl.select(partyMemberId);
        //2. 党员发展纪实
        List<ScPartyMemberRecord> scPartyMemberRecordList = partyMemberRecordImpl.selectDocumentaryAll(partyMemberId, null);
        //3. 党费记录

        //4. 组织生活情况
        //5. 履行党员义务情况
        //6. 参加学习培训情况
        //7. 奖励与纪律处分情况
        //8. 组织关系变更维护
        ScPartyMemberDocumentary scPartyMemberDocumentary = ScPartyMemberDocumentary.of();
        scPartyMemberDocumentary.setScPartyMember(scPartyMember);
        scPartyMemberDocumentary.setScPartMemberRecordList(scPartyMemberRecordList);
        return scPartyMemberDocumentary;
    }

    public ScPartyMember create(ScPartyMember scPartyMember) {
        //新增组织数据时,需要填补相关的属性信息
        fillCreateInfo(scPartyMember);
        return partyMemberImpl.insert(scPartyMember);
    }

    public int delete(Integer id) {
        return partyMemberImpl.delete(id);
    }

    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return partyMemberImpl.deletes(ids);
    }

    public int update(ScPartyMember scPartyMember) {
        //更新组织信息前,需要填补相关的属性信息
        fillUpdateInfo(scPartyMember);
        return partyMemberImpl.update(scPartyMember);
    }

    private void fillCreateInfo(ScPartyMember scPartyMember) {
        scPartyMember.setId(generateId(scPartyMember.getCardId()));
    }

    private void fillUpdateInfo(ScPartyMember scPartyMember) {

    }

    // 党员纪实相关

    public ScPartyMemberRecord createRecord(ScPartyMemberRecord scPartyMemberRecord) {
        //新增组织数据时,需要填补相关的属性信息
        fillCreateInfoRecord(scPartyMemberRecord);
        return partyMemberRecordImpl.insert(scPartyMemberRecord);
    }

    public int deleteRecord(Integer id) {
        return partyMemberRecordImpl.delete(id);
    }

    public int deleteRecord(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return partyMemberRecordImpl.deletes(ids);
    }

    public int updateRecord(ScPartyMemberRecord scPartyMemberRecord) {
        //更新组织信息前,需要填补相关的属性信息
        fillUpdateInfoRecord(scPartyMemberRecord);
        return partyMemberRecordImpl.update(scPartyMemberRecord);
    }

    private void fillCreateInfoRecord(ScPartyMemberRecord scPartyMemberRecord) {
        scPartyMemberRecord.setId(generateId(String.valueOf(scPartyMemberRecord.getPartyMemberId()), String.valueOf(System.currentTimeMillis())));
    }

    private void fillUpdateInfoRecord(ScPartyMemberRecord scPartyMemberRecord) {

    }

    private Integer generateId(String... args) {
        StringBuffer sb = new StringBuffer("%s");
        for (int i = 1; i < args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }
}
