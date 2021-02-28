package com.sct.service.main;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScPartyActivitiesCondition;
import com.sct.service.database.condition.ScPartyActivitiesConditionExport;
import com.sct.service.database.entity.ScPartyActivities;
import com.sct.service.database.mapper.ScPartyActivitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyActivitiesImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScPartyActivitiesMapper scPartyActivitiesMapper;

    public ResultVOEntity list(ScPartyActivitiesCondition condition) {
        List data = scPartyActivitiesMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScPartyActivitiesCondition condition) {
        int totalSize = scPartyActivitiesMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scPartyActivitiesMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScPartyActivitiesConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scPartyActivitiesMapper.selectCondition(conditionExport.getCondition()));
    }

    public ScPartyActivities select(Integer id) {
        return scPartyActivitiesMapper.selectByPrimaryKey(id);
    }

    public ScPartyActivities insert(ScPartyActivities scPartyActivities) {
        scPartyActivitiesMapper.insert(scPartyActivities);
        return scPartyActivities;
    }

    public int delete(Integer id) {
        return scPartyActivitiesMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scPartyActivitiesMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScPartyActivities scPartyActivities) {
        return scPartyActivitiesMapper.updateByPrimaryKey(scPartyActivities);
    }
}
