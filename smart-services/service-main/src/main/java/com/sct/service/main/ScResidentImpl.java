package com.sct.service.main;

import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScResidentCondition;
import com.sct.service.database.entity.ScResident;
import com.sct.service.database.mapper.ScResidentMapper;
import com.sct.service.database.mapper.ScResidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScResidentImpl {

    @Autowired
    private ScResidentMapper scResidentMapper;


    public ResultVOEntity list(ScResidentCondition condition) {
        List data = scResidentMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScResidentCondition condition) {
        int totalSize = scResidentMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scResidentMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScResident scResident) {
        return scResidentMapper.insert(scResident);
    }

    public int delete(Integer id) {
        return scResidentMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scResidentMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScResident scResident) {
        return scResidentMapper.updateByPrimaryKey(scResident);
    }

    public ScResident select(Integer id) {
        return scResidentMapper.selectByPrimaryKey(id);
    }
}
