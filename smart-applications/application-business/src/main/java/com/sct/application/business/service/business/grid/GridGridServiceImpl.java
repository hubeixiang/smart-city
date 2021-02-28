package com.sct.application.business.service.business.grid;

import com.sct.application.business.dto.ScGridAll;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.condition.ScGridEvaluationCondition;
import com.sct.service.database.condition.ScGridManagerCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.entity.ScGridEvaluation;
import com.sct.service.database.entity.ScGridManager;
import com.sct.service.main.ScGridEvaluationImpl;
import com.sct.service.main.ScGridImpl;
import com.sct.service.main.ScGridManagerImpl;
import com.sct.service.main.ScGridRangeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GridGridServiceImpl {

    @Autowired
    private ScGridImpl scGridImpl;
    @Autowired
    private ScGridRangeImpl scGridRangeImpl;
    @Autowired
    private ScGridManagerImpl scGridManagerImpl;
    @Autowired
    private ScGridEvaluationImpl scGridEvaluationImpl;

    @Autowired
    private FormatDataServiceImpl formatDataServiceImpl;

    // 生成id
    private Integer generateId(String... args) {
        StringBuffer sb = new StringBuffer("%s");
        for (int i = 1; i < args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }

    //创建新网格
    public int create(ScGrid scGrid) {
        scGrid.setId(this.generateId(scGrid.getGridNo(), scGrid.getName()));
        return scGridImpl.insert(scGrid);
    }

    //删除网格信息
    public int delete(Integer id) {
        return scGridImpl.delete(id);
    }

    //批量删除网格信息
    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scGridImpl.deletes(ids);
    }

    //更新网格信息
    public int update(ScGrid scGrid) {
        return scGridImpl.update(scGrid);
    }

    //分页查询网格
    public PageResultVO listPage(PageRecord paging, ScGridCondition condition) {
        return scGridImpl.listPage(paging, condition);
    }

    // 查询所有
    public ResultVOEntity list(ScGridCondition condition) {
        return scGridImpl.list(condition);
    }

    public int updateValidStatus(Integer id, Integer validStatus) {
        return scGridImpl.updateValidStatus(id, validStatus);
    }

    //查询详情
    @Transactional
    public ScGridAll select(Integer id) {
        ScGridAll all = new ScGridAll();
        ScGrid scGrid = scGridImpl.select(id);
        if(scGrid == null) {
            return null;
        }
        all.setScGrid(scGrid);
        all.setScGridRange(scGridRangeImpl.selectByGridId(id));
        all.setScGridManagerList(scGridManagerImpl.selectByGridId(id));
        return all;
    }

    //分页查询网格员列表
    public PageResultVO listGridManagerPage(PageRecord paging, ScGridManagerCondition condition) {
        return scGridManagerImpl.listPage(paging, condition);
    }

    //新增网格员
    public int createManager(ScGridManager body) {
        body.setId(this.generateId(body.getName(), String.valueOf(body.getGridId()), body.getMobile()));
        return scGridManagerImpl.insert(body);
    }

    //更新网格员
    public int updateManager(ScGridManager body) {
        return scGridManagerImpl.update(body);
    }

    //删除网格员
    public int deleteManager(Integer id) {
        return scGridManagerImpl.delete(id);
    }

    // 批量删除网格员
    public int deleteManager(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scGridManagerImpl.deletes(ids);
    }

    //分页查询网格员考评信息
    public PageResultVO listGridEvaluationPage(PageRecord paging, ScGridEvaluationCondition condition) {
        return scGridEvaluationImpl.listPage(paging, condition);
    }
    //新增考评信息
    public int createEvaluation(ScGridEvaluation body) {
        return scGridEvaluationImpl.insert(body);
    }

    public int updateEvaluation(ScGridEvaluation body) {
        return scGridEvaluationImpl.update(body);
    }

    public int deleteEvaluation(Integer gridId, Integer gridManagerId) {
        return scGridEvaluationImpl.delete(gridId, gridManagerId);
    }

    public int deleteEvaluation(Integer gridId, List<Integer> gridManagerIds) {
        return scGridEvaluationImpl.deletes(gridId, gridManagerIds);
    }

    public ResultVOEntity listGridsByCommunityId(Integer communityId) {
        return scGridImpl.listGridsByCommunityId(communityId);
    }
}
