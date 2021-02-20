package com.sct.application.business.service.business.grid;

import com.sct.application.business.dto.ScGridAll;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.entity.ScGrid;
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

    private Integer generateId(String ...args){
        StringBuffer sb = new StringBuffer("%s");
        for (int i=1;i<args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }

    public ScGrid create(ScGrid scGrid) {
        scGrid.setId(this.generateId(scGrid.getGridNo(),scGrid.getName()));
        return scGridImpl.insert(scGrid);
    }

    public int delete(Integer id) {
        return scGridImpl.delete(id);
    }

    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scGridImpl.deletes(ids);
    }

    public int update(ScGrid scGrid) {
        return scGridImpl.update(scGrid);
    }

    public PageResultVO listPage(PageRecord paging, ScGridCondition condition) {
        return scGridImpl.listPage(paging, condition);
    }

    public ResultVOEntity list(ScGridCondition condition) {
        return scGridImpl.list(condition);
    }
    @Transactional
    public ScGridAll select(Integer id) {
        ScGridAll all = new ScGridAll();
        all.setScGrid(scGridImpl.select(id));
        all.setScGridRange(scGridRangeImpl.selectByGridId(id));
        all.setScGridManagerList(scGridManagerImpl.selectByGridId(id));
        return all;
    }
}
