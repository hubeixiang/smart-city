package com.sct.service.main;

import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScCommunityLeaderCondition;
import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.database.mapper.ScCommunityLeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScCommunityLeaderImpl {

    @Autowired
    private ScCommunityLeaderMapper scCommunityLeaderMapper;

    public Integer insert(ScCommunityLeader scCommunityLeader) {
        return scCommunityLeaderMapper.insert(scCommunityLeader);
    }

    public int delete(Integer id) {
        return scCommunityLeaderMapper.deleteByPrimaryKey(id);
    }

    public int deleteByCommunityId(Integer id) {
        return scCommunityLeaderMapper.deleteByCommunityId(id);
    }

    public int deleteByCommunityIds(List<Integer> ids) {
        return scCommunityLeaderMapper.deleteByCommunityIds(ids);
    }

    public int deletes(List<Integer> ids) {
        return scCommunityLeaderMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScCommunityLeader scCommunityLeader) {
        return scCommunityLeaderMapper.updateByPrimaryKey(scCommunityLeader);
    }

    public PageResultVO listPage(Paging paging, ScCommunityLeaderCondition condition) {
        int totalSize = scCommunityLeaderMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scCommunityLeaderMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public List<ScCommunityLeader> selectByCommunityId(Integer id) {
        ScCommunityLeaderCondition condition = new ScCommunityLeaderCondition();
        condition.setCommunityId(id);
        return scCommunityLeaderMapper.selectCondition(condition);
    }

    public ScCommunityLeader select(Integer id) {
        return scCommunityLeaderMapper.selectByPrimaryKey(id);
    }
}
