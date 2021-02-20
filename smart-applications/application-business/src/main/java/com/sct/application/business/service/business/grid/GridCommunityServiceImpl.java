package com.sct.application.business.service.business.grid;

import com.sct.application.business.dto.ScCommunityAll;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScCommunityCondition;
import com.sct.service.database.condition.ScCommunityLeaderCondition;
import com.sct.service.database.entity.ScCommunity;
import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.main.ScCommunityImpl;
import com.sct.service.main.ScCommunityLeaderImpl;
import com.sct.service.main.ScCommunityPartyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class GridCommunityServiceImpl {

    @Autowired
    private ScCommunityImpl scCommunityImpl;
    @Autowired
    private ScCommunityPartyImpl scCommunityPartyImpl;

    @Autowired
    private ScCommunityLeaderImpl scCommunityLeaderImpl;

    //    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public ScCommunityAll create(ScCommunity scCommunity, ScCommunityParty scCommunityParty) {
        fillCreateInfo(scCommunity, scCommunityParty);
        ScCommunity community = scCommunityImpl.insert(scCommunity);
        scCommunityParty.setCommunityId(community.getId());
        ScCommunityParty party = scCommunityPartyImpl.insert(scCommunityParty);
        return new ScCommunityAll(community, party, null);
    }

    private void fillCreateInfo(ScCommunity scCommunity, ScCommunityParty scCommunityParty) {
        String scCommunityIdstr = String.format("%s", scCommunity.getName());
        scCommunity.setId(IntIdGenerator.getCRC32(scCommunityIdstr));

        String scCommunityPartyIdstr = String.format("%s:%s", scCommunity.getName(), scCommunityParty.getName());
        scCommunityParty.setId(IntIdGenerator.getCRC32(scCommunityPartyIdstr));
    }

    private Integer generateId(String ...args){
        StringBuffer sb = new StringBuffer("%s");
        for (int i=1;i<args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }

    @Transactional
    public int delete(Integer id) {
        int deleteScCommunityParty =  scCommunityPartyImpl.deleteByCommunityId(id);
        if (deleteScCommunityParty <= 0) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除原纪录子表失败");
        }
        int deleteCommunity = scCommunityImpl.delete(id);
        if (deleteCommunity <= 0) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除原纪录失败");
        }
        return deleteCommunity;
    }

    @Transactional
    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        scCommunityPartyImpl.deleteByCommunityIds(ids);
        return scCommunityImpl.deletes(ids);
    }
    @Transactional
    public ScCommunityAll update(ScCommunity scCommunity, ScCommunityParty scCommunityParty) {
        //先删后增
        this.delete(scCommunity.getId());
        return this.create(scCommunity,scCommunityParty);
    }

    // 分页查询社区信息
    public PageResultVO listPage(PageRecord paging, ScCommunityCondition condition) {
        return scCommunityImpl.listPage(paging, condition);
    }
    // 查询所有社区信息
    public ResultVOEntity list(ScCommunityCondition condition) {
        return scCommunityImpl.list(condition);
    }

    public List<Map<Integer, String>> getIdNameMapping(){
        return scCommunityImpl.getIdNameMapping();
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
    @Transactional
    public int updateLeader(ScCommunityLeader scCommunityLeader) {
        return scCommunityLeaderImpl.update(scCommunityLeader);
//        //先删后增
//        this.deleteLeader(scCommunityLeader.getId());
//        return this.createLeader(scCommunityLeader);
    }

    // 删除领导
    @Transactional
    public int deleteLeader(Integer id) {
        return scCommunityLeaderImpl.delete(id);
    }

    // 批量删除领导
    public int deleteLeader(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scCommunityLeaderImpl.deletes(ids);
    }

    @Transactional
    public ScCommunityAll select(Integer id) {
        ScCommunityAll all = new ScCommunityAll();
        all.setScCommunity(scCommunityImpl.select(id));
        all.setScCommunityParty(scCommunityPartyImpl.selectByCommunityId(id));
        all.setScCommunityLeaderList(scCommunityLeaderImpl.selectByCommunityId(id));
        return all;
    }

}