package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScCommunityCondition;
import com.sct.service.database.entity.ScCommunity;
import com.sct.service.database.mapper.ScCommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScCommunityImpl {

    @Autowired
    private ScCommunityMapper scCommunityMapper;


    public ResultVOEntity list(ScCommunityCondition condition) {
        List data = scCommunityMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScCommunityCondition condition) {
        int totalSize = scCommunityMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scCommunityMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public ScCommunity insert(ScCommunity scCommunity) {
        scCommunityMapper.insert(scCommunity);
        return scCommunity;
    }

    public int delete(Integer id) {
        return scCommunityMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {

        return scCommunityMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScCommunity scCommunity) {
        return scCommunityMapper.updateByPrimaryKey(scCommunity);
    }

    public List<Map<Integer, String>> getIdNameMapping(){
        return scCommunityMapper.getIdNameMapping();
    }

    public ScCommunity select(Integer id) {
        return scCommunityMapper.selectByPrimaryKey(id);
    }
}
