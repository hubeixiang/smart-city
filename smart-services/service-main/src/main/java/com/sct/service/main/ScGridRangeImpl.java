package com.sct.service.main;

import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.entity.ScGridRange;
import com.sct.service.database.mapper.ScGridMapper;
import com.sct.service.database.mapper.ScGridRangeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScGridRangeImpl {

    @Autowired
    private FormatDataServiceImpl formatDataService;

    @Autowired
    private ScGridRangeMapper scGridRangeMapper;

    public ScGridRange insert(ScGridRange scGridRange) {
        scGridRangeMapper.insert(scGridRange);
        return scGridRange;
    }

    public int delete(Integer id) {
        return scGridRangeMapper.deleteByGridId(id);
    }

//    public int deletes(List<Integer> ids) {
//        return scGridRangeMapper.deleteByPrimaryKeys(ids);
//    }

    public int update(ScGridRange scGridRange) {
        return scGridRangeMapper.updateByPrimaryKey(scGridRange);
    }

    public ScGridRange selectByGridId(Integer id) {
        return scGridRangeMapper.selectByGridId(id);
    }
}
