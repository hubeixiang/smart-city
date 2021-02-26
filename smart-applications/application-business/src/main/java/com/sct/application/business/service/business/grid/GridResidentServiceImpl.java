package com.sct.application.business.service.business.grid;

import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScResidentCondition;
import com.sct.service.database.entity.ScResident;
import com.sct.service.main.ScResidentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridResidentServiceImpl {

    @Autowired
    private ScResidentImpl scResidentImpl;


    public int create(ScResident body) {
        //居民省份证作为唯一标志
        body.setId(this.generateId(body.getCardId()));
        return scResidentImpl.insert(body);
    }

    public int update(ScResident body) {
        return scResidentImpl.update(body);
    }

    public int delete(Integer id) {
        return scResidentImpl.delete(id);
    }

    public int delete(List<Integer> ids) {
        return scResidentImpl.deletes(ids);
    }

    private Integer generateId(String... args) {
        StringBuffer sb = new StringBuffer("%s");
        for (int i = 1; i < args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }

    // 按条件查询所有居民信息
    public ResultVOEntity list(ScResidentCondition condition) {
        return scResidentImpl.list(condition);
    }

    //分页查询小区
    public PageResultVO listPage(PageRecord paging, ScResidentCondition condition) {
        return scResidentImpl.listPage(paging, condition);
    }

    public ScResident select(Integer id) {
        return scResidentImpl.select(id);
    }
}
