package com.sct.service.main;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScPartyTopicCondition;
import com.sct.service.database.condition.ScPartyTopicConditionExport;
import com.sct.service.database.entity.ScPartyTopic;
import com.sct.service.database.mapper.ScPartyTopicMapper;
import com.sct.service.database.mybatis.LowerHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyTopicImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScPartyTopicMapper scPartyTopicMapper;

    public ResultVOEntity overview(ScPartyTopicCondition condition) {
        List<LowerHashMap> data = scPartyTopicMapper.selectConditionOverview(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }


    public ResultVOEntity list(ScPartyTopicCondition condition) {
        List data = scPartyTopicMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScPartyTopicCondition condition) {
        int totalSize = scPartyTopicMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scPartyTopicMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScPartyTopicConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scPartyTopicMapper.selectCondition(conditionExport.getCondition()));
    }

    public ScPartyTopic select(Integer id) {
        return scPartyTopicMapper.selectByPrimaryKey(id);
    }

    public ScPartyTopic insert(ScPartyTopic scPartyTopic) {
        scPartyTopicMapper.insert(scPartyTopic);
        return scPartyTopic;
    }

    public int delete(Integer id) {
        return scPartyTopicMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scPartyTopicMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScPartyTopic scPartyTopic) {
        return scPartyTopicMapper.updateByPrimaryKey(scPartyTopic);
    }
}
