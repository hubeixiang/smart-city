package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScPropertyStaffCondition;
import com.sct.service.database.entity.ScPropertyStaff;
import com.sct.service.database.mapper.ScPropertyStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScPropertyStaffImpl {

    @Autowired
    private ScPropertyStaffMapper scPropertyStaffMapper;


    public ResultVOEntity list(ScPropertyStaffCondition condition) {
        List data = scPropertyStaffMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScPropertyStaffCondition condition) {
        int totalSize = scPropertyStaffMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scPropertyStaffMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScPropertyStaff scPropertyStaff) {
        return scPropertyStaffMapper.insert(scPropertyStaff);
    }

    public int delete(Integer id) {
        return scPropertyStaffMapper.deleteByPrimaryKey(id);
    }

    public int deletes(List<Integer> ids) {
        return scPropertyStaffMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScPropertyStaff scPropertyStaff) {
        return scPropertyStaffMapper.updateByPrimaryKey(scPropertyStaff);
    }

    public ScPropertyStaff select(Integer id) {
        return scPropertyStaffMapper.selectByPrimaryKey(id);
    }

    public int deleteByEstateId(Integer estateId) {
        return scPropertyStaffMapper.deleteByEstateId(estateId);
    }

    public int deleteByEstateIds(List<Integer> estateId) {
        return scPropertyStaffMapper.deleteByEstateIds(estateId);
    }
}
