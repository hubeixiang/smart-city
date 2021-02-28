package com.sct.service.main;

import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScHouseCondition;
import com.sct.service.database.entity.ScHouse;
import com.sct.service.database.mapper.ScHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScHouseImpl {

    @Autowired
    private ScHouseMapper scHouseMapper;


    public ResultVOEntity list(ScHouseCondition condition) {
        List data = scHouseMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScHouseCondition condition) {
        int totalSize = scHouseMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scHouseMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScHouse scHouse) {
        return scHouseMapper.insert(scHouse);
    }

    public int delete(Integer id) {
        return scHouseMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scHouseMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScHouse scHouse) {
        return scHouseMapper.updateByPrimaryKey(scHouse);
    }

    public ScHouse select(Integer id) {
        return scHouseMapper.selectByPrimaryKey(id);
    }
}
