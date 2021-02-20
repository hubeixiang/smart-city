package com.sct.application.business.service.business.grid;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScCommunityCondition;
import com.sct.service.database.entity.ScCommunity;
import com.sct.service.main.ScCommunityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GridCommunityPartyServiceImpl {
//
//    @Autowired
//    private ScCommunityImpl scCommunityImpl;
//
//    @Autowired
//    private FormatDataServiceImpl formatDataServiceImpl;
//
//    public ScCommunity create(ScCommunity scCommunity) {
//
//        return scCommunityImpl.insert(scCommunity);
//    }
//
//    public int delete(Integer id) {
//        return scCommunityImpl.delete(id);
//    }
//
//    public int delete(List<Integer> ids) {
//        if (CollectionUtils.isEmpty(ids)) {
//            return 0;
//        }
//        return scCommunityImpl.deletes(ids);
//    }
//
//    public int update(ScCommunity scCommunity) {
//        return scCommunityImpl.update(scCommunity);
//    }
//
//    public PageResultVO listPage(PageRecord paging, ScCommunityCondition condition) {
//        return scCommunityImpl.listPage(paging, condition);
//    }
//
//    public ResultVOEntity list(ScCommunityCondition condition) {
//        return scCommunityImpl.list(condition);
//    }
}
