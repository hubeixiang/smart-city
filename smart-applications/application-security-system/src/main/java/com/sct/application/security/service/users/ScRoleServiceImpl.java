package com.sct.application.security.service.users;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScRoleCondition;
import com.sct.service.database.condition.ScRoleConditionExport;
import com.sct.service.database.entity.ScRoleDatas;
import com.sct.service.database.entity.ScRoleOperations;
import com.sct.service.database.entity.ScUserRoleRel;
import com.sct.service.database.mapper.ScRoleDatasMapper;
import com.sct.service.database.mapper.ScRoleMapper;
import com.sct.service.database.mapper.ScRoleOperationsMapper;
import com.sct.service.database.mapper.ScUserRoleRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScRoleServiceImpl {

    @Autowired
    private ScRoleMapper scRoleMapper;

    @Autowired
    private ScUserRoleRelMapper scUserRoleRelMapper;

    @Autowired
    private ScRoleOperationsMapper scRoleOperationsMapper;

    @Autowired
    private ScRoleDatasMapper scRoleDatasMapper;

    @Autowired
    private FormatDataServiceImpl formatDataService;

    public ResultVOEntity list(ScRoleCondition condition) {
        List data = scRoleMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScRoleCondition condition) {
        int totalSize = scRoleMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scRoleMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScRoleConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scRoleMapper.selectCondition(conditionExport.getCondition()));
    }

    @Transactional
    public int inserts(List<ScUserRoleRel> scUserRoleRels) {
        int total = 0;
        for (ScUserRoleRel scUserRoleRel : scUserRoleRels) {
            int i = scUserRoleRelMapper.insert(scUserRoleRel);
            total = total + i;
        }
        return total;
    }

    @Transactional
    public int deletes(List<ScUserRoleRel> scUserRoleRels) {
        int total = 0;
        for (ScUserRoleRel scUserRoleRel : scUserRoleRels) {
            int i = scUserRoleRelMapper.deleteByPrimaryKey(scUserRoleRel.getUserId(), scUserRoleRel.getRoleId(), scUserRoleRel.getRoleType());
            total = total + i;
        }
        return total;
    }

    @Transactional
    public int delete(Integer userId, Integer roleId, Integer roleType) {
        return scUserRoleRelMapper.deleteByPrimaryKeyCondition(userId, roleId, roleType);
    }

    @Transactional
    public int insertFunctions(List<ScRoleOperations> scRoleOperationsList) {
        int total = 0;
        for (ScRoleOperations scRoleOperations : scRoleOperationsList) {
            int i = scRoleOperationsMapper.insert(scRoleOperations);
            total = total + i;
        }
        return total;
    }

    @Transactional
    public int deleteFunctions(List<ScRoleOperations> scRoleOperationsList) {
        int total = 0;
        for (ScRoleOperations scRoleOperations : scRoleOperationsList) {
            int i = scRoleOperationsMapper.deleteByPrimaryKey(scRoleOperations.getRoleId(), scRoleOperations.getDevType(), scRoleOperations.getOperId());
            total = total + i;
        }
        return total;
    }

    @Transactional
    public int deleteFunction(Integer roleId, Integer devType) {
        return scRoleOperationsMapper.deleteByPrimaryKeyCondition(roleId, devType, null);
    }

    @Transactional
    public int insertDataOpts(List<ScRoleDatas> scRoleDatasList) {
        int total = 0;
        for (ScRoleDatas scRoleDatas : scRoleDatasList) {
            int i = scRoleDatasMapper.insert(scRoleDatas);
            total = total + i;
        }
        return total;
    }

    @Transactional
    public int deleteDataOpts(List<ScRoleDatas> scRoleDatasList) {
        int total = 0;
        for (ScRoleDatas scRoleDatas : scRoleDatasList) {
            int i = scRoleDatasMapper.deleteByPrimaryKey(scRoleDatas.getRoleId(), scRoleDatas.getGridId(), scRoleDatas.getGridLevel());
            total = total + i;
        }
        return total;
    }

    @Transactional
    public int deleteDataOpts(Integer roleId, Integer gridId, Integer gridLevel) {
        return scRoleDatasMapper.deleteByPrimaryKeyCondition(roleId, gridId, gridLevel);
    }
}
