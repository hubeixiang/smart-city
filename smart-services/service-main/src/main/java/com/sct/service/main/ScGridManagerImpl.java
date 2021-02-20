package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.condition.ScGridManagerCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.entity.ScGridManager;
import com.sct.service.database.mapper.ScGridManagerMapper;
import com.sct.service.database.mapper.ScGridMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScGridManagerImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScGridManagerMapper scGridManagerMapper;


    public ResultVOEntity list(ScGridManagerCondition condition) {
        List data = scGridManagerMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScGridManagerCondition condition) {
        int totalSize = scGridManagerMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scGridManagerMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public ScGridManager insert(ScGridManager scGridManager) {
        scGridManagerMapper.insert(scGridManager);
        return scGridManager;
    }

    public int delete(Integer id) {
        return scGridManagerMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scGridManagerMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScGridManager scGridManager) {
        return scGridManagerMapper.updateByPrimaryKey(scGridManager);
    }

    public ScGridManager select(Integer id) {
        return scGridManagerMapper.selectByPrimaryKey(id);
    }

    public List<ScGridManager> selectByGridId(Integer id) {
        ScGridManagerCondition condition = new ScGridManagerCondition();
        condition.setGridId(id);
        return scGridManagerMapper.selectCondition(condition);
    }
}
