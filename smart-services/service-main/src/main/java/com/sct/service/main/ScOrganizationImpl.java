package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.ExportExcelCondition;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScOrganizationCondition;
import com.sct.service.database.dict.ScOrganizationRegisterStatus;
import com.sct.service.database.entity.ScOrganization;
import com.sct.service.database.mapper.ScOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScOrganizationImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScOrganizationMapper scOrganizationMapper;

    public ResultVOEntity list(ScOrganizationCondition condition) {
        List data = scOrganizationMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public List<List<String>> export(ScOrganizationCondition condition, ExportExcelCondition exportExcelCondition) {
        List<ScOrganization> datas = scOrganizationMapper.selectCondition(condition);
        List<List<String>> result = formatDataService.format2TwoDimensionalArray(datas, exportExcelCondition, 1000);
        return result;
    }

    public PageResultVO listPage(Paging paging, ScOrganizationCondition condition) {
        int totalSize = scOrganizationMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scOrganizationMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public ScOrganization insert(ScOrganization scOrganization) {
        scOrganizationMapper.insert(scOrganization);
        return scOrganization;
    }

    public int delete(Integer id) {
        return scOrganizationMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scOrganizationMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScOrganization scOrganization) {
        return scOrganizationMapper.updateByPrimaryKey(scOrganization);
    }

    public int cancel(Integer id) {
        return scOrganizationMapper.updateRegisterStatus(id, ScOrganizationRegisterStatus.CANCEL.getId());
    }
}
