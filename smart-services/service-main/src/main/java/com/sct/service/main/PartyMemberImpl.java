package com.sct.service.main;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScPartyMemberCondition;
import com.sct.service.database.condition.ScPartyMemberConditionExport;
import com.sct.service.database.entity.ScPartyMember;
import com.sct.service.database.mapper.ScPartyMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyMemberImpl {
    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScPartyMemberMapper scPartyMemberMapper;

    public ResultVOEntity list(ScPartyMemberCondition condition) {
        List data = scPartyMemberMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScPartyMemberCondition condition) {
        int totalSize = scPartyMemberMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scPartyMemberMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScPartyMemberConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scPartyMemberMapper.selectCondition(conditionExport.getCondition()));
    }

    public ScPartyMember select(Integer id) {
        return scPartyMemberMapper.selectByPrimaryKey(id);
    }

    public ScPartyMember insert(ScPartyMember scPartyMember) {
        scPartyMemberMapper.insert(scPartyMember);
        return scPartyMember;
    }

    public int delete(Integer id) {
        return scPartyMemberMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scPartyMemberMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScPartyMember scPartyMember) {
        return scPartyMemberMapper.updateByPrimaryKey(scPartyMember);
    }
}
