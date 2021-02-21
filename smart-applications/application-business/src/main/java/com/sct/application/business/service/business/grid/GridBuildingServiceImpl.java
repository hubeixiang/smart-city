package com.sct.application.business.service.business.grid;

import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScBuildingCondition;
import com.sct.service.database.entity.ScBuilding;
import com.sct.service.main.ScBuildingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GridBuildingServiceImpl {

    @Autowired
    private ScBuildingImpl scBuildingImpl;

    // 生成id
    private Integer generateId(String... args) {
        StringBuffer sb = new StringBuffer("%s");
        for (int i = 1; i < args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }

    //创建新小区
    public int create(ScBuilding scBuilding) {
        scBuilding.setId(this.generateId(String.valueOf(scBuilding.getCommunityId()), String.valueOf(scBuilding.getEstateId()), scBuilding.getName()));
        return scBuildingImpl.insert(scBuilding);
    }

    //删除小区信息
    @Transactional
    public int delete(Integer id) {
        return scBuildingImpl.delete(id);
    }

    //批量删除小区信息
    @Transactional
    public int delete(List<Integer> ids) {
        return scBuildingImpl.deletes(ids);
    }

    //更新小区信息
    public int update(ScBuilding scBuilding) {
        return scBuildingImpl.update(scBuilding);
    }

    //分页查询小区
    public PageResultVO listPage(PageRecord paging, ScBuildingCondition condition) {
        return scBuildingImpl.listPage(paging, condition);
    }

    // 查询所有
    public ResultVOEntity list(ScBuildingCondition condition) {
        return scBuildingImpl.list(condition);
    }

    //查询详情
    @Transactional
    public ScBuilding select(Integer id) {
        return scBuildingImpl.select(id);
    }
}
