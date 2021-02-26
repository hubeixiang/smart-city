package com.sct.service.main;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScPartyMemberRecordCondition;
import com.sct.service.database.condition.ScPartyMemberRecordConditionExport;
import com.sct.service.database.entity.ScPartyMemberRecord;
import com.sct.service.database.mapper.ScPartyMemberRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyMemberRecordImpl {
    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScPartyMemberRecordMapper scPartyMemberRecordMapper;


    public ResultVOEntity list(ScPartyMemberRecordCondition condition) {
        List data = scPartyMemberRecordMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScPartyMemberRecordCondition condition) {
        int totalSize = scPartyMemberRecordMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scPartyMemberRecordMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScPartyMemberRecordConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scPartyMemberRecordMapper.selectCondition(conditionExport.getCondition()));
    }

    public List<ScPartyMemberRecord> listEntity(ScPartyMemberRecordCondition condition) {
        return scPartyMemberRecordMapper.selectCondition(condition);
    }

    public List<ScPartyMemberRecord> selectDocumentaryAll(Integer partyMemberId, Integer recordType) {
        return scPartyMemberRecordMapper.selectDocumentaryAll(partyMemberId, recordType);
    }

    public ScPartyMemberRecord select(Integer id) {
        return scPartyMemberRecordMapper.selectDocumentary(id);
    }

    public ScPartyMemberRecord insert(ScPartyMemberRecord scPartyMemberRecord) {
        scPartyMemberRecordMapper.insert(scPartyMemberRecord);
        return scPartyMemberRecord;
    }

    public int delete(Integer id) {
        return scPartyMemberRecordMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scPartyMemberRecordMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScPartyMemberRecord scPartyMemberRecord) {
        return scPartyMemberRecordMapper.updateByPrimaryKey(scPartyMemberRecord);
    }
}
