package com.sct.service.main;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScLogCondition;
import com.sct.service.database.condition.ScLogConditionExport;
import com.sct.service.database.entity.ScLog;
import com.sct.service.database.mapper.ScLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class ScLogServiceImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScLogMapper scLogMapper;

    /**
     * 导出服务
     *
     * @param conditionExport
     * @return
     */
    public FileLocation export2FileLocation(ScLogConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scLogMapper.selectCondition(conditionExport.getCondition()));
    }

    public ScLog insert(ScLog scLog) {
        if (scLog == null) {
            return null;
        }
        if (scLog.getCreateTime() == null) {
            scLog.setCreateTime(Date.from(Instant.now()));
        }
        scLogMapper.insert(scLog);
        return scLog;
    }

    public ResultVOEntity list(ScLogCondition condition) {
        List data = scLogMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScLogCondition condition) {
        int totalSize = scLogMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scLogMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }
}
