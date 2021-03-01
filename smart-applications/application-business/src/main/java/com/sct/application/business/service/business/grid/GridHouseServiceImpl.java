package com.sct.application.business.service.business.grid;

import com.sct.application.business.dto.ScHouseAll;
import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScHouseCondition;
import com.sct.service.database.condition.ScResidentCondition;
import com.sct.service.database.entity.ScHouse;
import com.sct.service.database.entity.ScResident;
import com.sct.service.database.entity.ScUserHouseRel;
import com.sct.service.database.entity.ScUserHouseRelHis;
import com.sct.service.main.ScHouseImpl;
import com.sct.service.main.ScResidentImpl;
import com.sct.service.main.ScUserHouseRelHisImpl;
import com.sct.service.main.ScUserHouseRelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GridHouseServiceImpl {

    @Autowired
    private ScHouseImpl scHouseImpl;
    @Autowired
    private ScResidentImpl scResidentImpl;
    @Autowired
    private ScUserHouseRelImpl scUserHouseRelImpl;
    @Autowired
    private ScUserHouseRelHisImpl scUserHouseRelHisImpl;

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
    public ScHouseAll select(Integer id) {
        ScHouse scHouse = scHouseImpl.select(id);
        List<ScResident> scResidents = scResidentImpl.selectByHouseId(id);
        return new ScHouseAll(scHouse, scResidents);
    }

    //通过houseId查询房屋中的居住人员
    public PageResultVO listResidents(Integer houseId, PageRecord paging, ScResidentCondition condition) {
        condition.setHouseId(houseId);
        return scResidentImpl.listPage(paging, condition);
    }

    // 绑定房屋和居住人员关系
    public int createUserHouseRel(ScUserHouseRel body) {
        return scUserHouseRelImpl.insert(body);
    }

    //解除房屋与居住人员绑定关系
    public int deleteUserHouseRel(Integer userId, Integer houseId) {
        return scUserHouseRelImpl.delete(userId, houseId);
    }

    //批量解除房屋与居住人员绑定关系
    public int deleteUserHouseRel(List<Integer> userIds, Integer houseId) {
        return scUserHouseRelImpl.deletes(userIds, houseId);
    }

    @Transactional
    public int cancel(ScUserHouseRelHis body) {
        int insert = scUserHouseRelHisImpl.insert(body);
        int delete = scUserHouseRelImpl.delete(body.getUserId(), body.getHouseId());
        if (delete == insert) {
            return delete;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除关系表数据与新增历史关系表数据个数不一致，取消注销操作");
        }
    }
}
