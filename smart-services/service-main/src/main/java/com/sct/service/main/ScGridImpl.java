package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.mapper.ScGridMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScGridImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScGridMapper scGridMapper;


    public ResultVOEntity list(ScGridCondition condition) {
        List data = scGridMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScGridCondition condition) {
        int totalSize = scGridMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scGridMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScGrid scGrid) {
        return scGridMapper.insert(scGrid);
    }

    public int delete(Integer id) {
        return scGridMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scGridMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScGrid scGrid) {
        return scGridMapper.updateByPrimaryKey(scGrid);
    }

    public ScGrid select(Integer id) {
        return scGridMapper.selectByPrimaryKey(id);
    }

    public int updateValidStatus(Integer id, Integer validStatus) {
        return scGridMapper.updateValidStatusByPrimaryKey(id, validStatus);
    }

    public ResultVOEntity listGridsByCommunityId(Integer communityId) {
        List data = scGridMapper.selectByCommunityId(communityId);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }
}
