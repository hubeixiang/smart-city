package com.sct.service.main;

import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScSsoLogCondition;
import com.sct.service.database.entity.ScSsoLog;
import com.sct.service.database.mapper.ScSsoLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class ScSsoLogServiceImpl {

    @Autowired
    private ScSsoLogMapper scSsoLogMapper;

    public int insert(ScSsoLog scSsoLog) {
        if (scSsoLog.getCreateTime() == null) {
            scSsoLog.setCreateTime(Date.from(Instant.now()));
        }
        return scSsoLogMapper.insert(scSsoLog);
    }

    public ResultVOEntity list(ScSsoLogCondition condition) {
        List data = scSsoLogMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScSsoLogCondition condition) {
        int totalSize = scSsoLogMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scSsoLogMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }
}
