package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScBuildingCondition;
import com.sct.service.database.entity.ScBuilding;
import com.sct.service.database.mapper.ScBuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScBuildingImpl {

    @Autowired
    private ScBuildingMapper scBuildingMapper;


    public ResultVOEntity list(ScBuildingCondition condition) {
        List data = scBuildingMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScBuildingCondition condition) {
        int totalSize = scBuildingMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scBuildingMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScBuilding scBuilding) {
        return scBuildingMapper.insert(scBuilding);
    }

    public int delete(Integer id) {
        return scBuildingMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scBuildingMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScBuilding scBuilding) {
        return scBuildingMapper.updateByPrimaryKey(scBuilding);
    }

    public ScBuilding select(Integer id) {
        return scBuildingMapper.selectByPrimaryKey(id);
    }

    public ResultVOEntity listBuildingsByEstateId(Integer estateId) {
        List data = scBuildingMapper.selectByEstateId(estateId);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }
}
