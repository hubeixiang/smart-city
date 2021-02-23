package com.sct.service.main;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScCommunityPartyCondition;
import com.sct.service.database.condition.ScCommunityPartyConditionExport;
import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.database.mapper.ScCommunityPartyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScCommunityPartyImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScCommunityPartyMapper scCommunityPartyMapper;

    public ResultVOEntity list(ScCommunityPartyCondition condition) {
        List data = scCommunityPartyMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScCommunityPartyCondition condition) {
        int totalSize = scCommunityPartyMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scCommunityPartyMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScCommunityPartyConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scCommunityPartyMapper.selectCondition(conditionExport.getCondition()));
    }


    public ScCommunityParty select(Integer id) {
        return scCommunityPartyMapper.selectByPrimaryKey(id);
    }

    public ScCommunityParty insert(ScCommunityParty scCommunityParty) {
        scCommunityPartyMapper.insert(scCommunityParty);
        return scCommunityParty;
    }

    public int delete(Integer id) {

        return scCommunityPartyMapper.deleteByPrimaryKey(id);
    }

    public int deleteByCommunityId(Integer id) {
        return scCommunityPartyMapper.deleteByCommunityId(id);
    }

    public int deleteByCommunityIds(List<Integer> ids) {
        return scCommunityPartyMapper.deleteByCommunityIds(ids);
    }

    public int deletes(List<Integer> ids) {
        return scCommunityPartyMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScCommunityParty scCommunityParty) {
        return scCommunityPartyMapper.updateByPrimaryKey(scCommunityParty);
    }

    public ScCommunityParty selectByCommunityId(Integer id) {
        return scCommunityPartyMapper.selectByCommunityId(id);
    }
}
