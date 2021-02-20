package com.sct.application.business.service.business.grid;

import com.sct.application.business.dto.ScGridAll;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.condition.ScGridManagerCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.entity.ScGridManager;
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

    //查询详情
    @Transactional
    public ScGridAll select(Integer id) {
        ScGridAll all = new ScGridAll();
        all.setScGrid(scGridImpl.select(id));
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
}
