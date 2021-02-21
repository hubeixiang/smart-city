package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScEstateCondition;
import com.sct.service.database.entity.ScEstate;
import com.sct.service.database.mapper.ScEstateMapper;
import com.sct.service.database.mapper.ScEstateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScEstateImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScEstateMapper scEstateMapper;


    public ResultVOEntity list(ScEstateCondition condition) {
        List data = scEstateMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScEstateCondition condition) {
        int totalSize = scEstateMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scEstateMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScEstate scEstate) {
        return scEstateMapper.insert(scEstate);
    }

    public int delete(Integer id) {
        return scEstateMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scEstateMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScEstate scEstate) {
        return scEstateMapper.updateByPrimaryKey(scEstate);
    }

    public ScEstate select(Integer id) {
        return scEstateMapper.selectByPrimaryKey(id);
    }
}
