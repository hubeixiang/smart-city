package com.sct.service.main;

import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScGridEvaluationCondition;
import com.sct.service.database.entity.ScGridEvaluation;
import com.sct.service.database.mapper.ScGridEvaluationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScGridEvaluationImpl {

    @Autowired
    private ScGridEvaluationMapper scGridEvaluationMapper;


    public ResultVOEntity list(ScGridEvaluationCondition condition) {
        List data = scGridEvaluationMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScGridEvaluationCondition condition) {
        int totalSize = scGridEvaluationMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scGridEvaluationMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public int insert(ScGridEvaluation scGridEvaluation) {
        return scGridEvaluationMapper.insert(scGridEvaluation);
    }

    public int delete(Integer gridId, Integer gridManagerId) {
        return scGridEvaluationMapper.deleteByPrimaryKey(gridId, gridManagerId);
    }

    public int deletes(Integer gridId, List<Integer> gridManagerIds) {
        return scGridEvaluationMapper.deleteByPrimaryKeys(gridId, gridManagerIds);
    }

    public int update(ScGridEvaluation scGridEvaluation) {
        return scGridEvaluationMapper.updateByPrimaryKey(scGridEvaluation);
    }

    public ScGridEvaluation select(Integer gridId, Integer gridManagerId) {
        return scGridEvaluationMapper.selectByPrimaryKey(gridId, gridManagerId);
    }
}
