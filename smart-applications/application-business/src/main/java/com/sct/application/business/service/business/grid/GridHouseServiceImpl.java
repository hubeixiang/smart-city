package com.sct.application.business.service.business.grid;

import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScHouseCondition;
import com.sct.service.database.entity.ScHouse;
import com.sct.service.main.ScHouseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GridHouseServiceImpl {

    @Autowired
    private ScHouseImpl scHouseImpl;

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
    public int create(ScHouse scHouse) {
        scHouse.setId(this.generateId(String.valueOf(scHouse.getBuildingId()), String.valueOf(scHouse.getUnit()), String.valueOf(scHouse.getFloor()), String.valueOf(scHouse.getRoomNumer())));
        return scHouseImpl.insert(scHouse);
    }

    //删除小区信息
    @Transactional
    public int delete(Integer id) {
        return scHouseImpl.delete(id);
    }

    //批量删除小区信息
    @Transactional
    public int delete(List<Integer> ids) {
        return scHouseImpl.deletes(ids);
    }

    //更新小区信息
    public int update(ScHouse scHouse) {
        return scHouseImpl.update(scHouse);
    }

    //分页查询小区
    public PageResultVO listPage(PageRecord paging, ScHouseCondition condition) {
        return scHouseImpl.listPage(paging, condition);
    }

    // 查询所有
    public ResultVOEntity list(ScHouseCondition condition) {
        return scHouseImpl.list(condition);
    }

    //查询详情
    @Transactional
    public ScHouse select(Integer id) {
        return scHouseImpl.select(id);
    }
}
