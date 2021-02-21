package com.sct.application.business.service.business.grid;

import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.database.condition.ScEstateCondition;
import com.sct.service.database.condition.ScPropertyStaffCondition;
import com.sct.service.database.entity.ScEstate;
import com.sct.service.database.entity.ScEstateStaffRel;
import com.sct.service.database.entity.ScPropertyStaff;
import com.sct.service.main.ScEstateImpl;
import com.sct.service.main.ScEstateStaffRelImpl;
import com.sct.service.main.ScPropertyStaffImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GridEstateServiceImpl {

    @Autowired
    private ScEstateImpl scEstateImpl;
    @Autowired
    private ScPropertyStaffImpl scPropertyStaffImpl;

    @Autowired
    private ScEstateStaffRelImpl scEstateStaffRelImpl;

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
    public int create(ScEstate scEstate) {
        scEstate.setId(this.generateId(String.valueOf(scEstate.getCommunityId()), scEstate.getName()));
        return scEstateImpl.insert(scEstate);
    }

    //删除小区信息
    @Transactional
    public int delete(Integer id) {
        //删除小区关联的无业人员
        int deleteStaff = scPropertyStaffImpl.deleteByEstateId(id);
        //删除小区关联的无业人员关系表
        int deleteRel = scEstateStaffRelImpl.deleteByEstateId(id);
        if (deleteStaff != deleteRel) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除物业人员表记录与关系表记录个数不一致，取消");
        }
        return scEstateImpl.delete(id);
    }

    //批量删除小区信息
    @Transactional
    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        //删除小区关联的无业人员
        int deleteStaff = scPropertyStaffImpl.deleteByEstateIds(ids);
        //删除小区关联的无业人员关系表
        int deleteRel = scEstateStaffRelImpl.deleteByEstateIds(ids);
        if (deleteStaff != deleteRel) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除物业人员表记录与关系表记录个数不一致，取消");
        }
        return scEstateImpl.deletes(ids);
    }

    //更新小区信息
    public int update(ScEstate scEstate) {
        return scEstateImpl.update(scEstate);
    }

    //分页查询小区
    public PageResultVO listPage(PageRecord paging, ScEstateCondition condition) {
        return scEstateImpl.listPage(paging, condition);
    }

    // 查询所有
    public ResultVOEntity list(ScEstateCondition condition) {
        return scEstateImpl.list(condition);
    }

    //查询详情
    @Transactional
    public ScEstate select(Integer id) {
        return scEstateImpl.select(id);
    }

    //分页查询物业人员
    public PageResultVO listPropertyStaffPage(PageRecord paging, ScPropertyStaffCondition condition) {
        return scPropertyStaffImpl.listPage(paging, condition);
    }

    //新增物业人员
    @Transactional
    public int createStaff(ScPropertyStaff body, Integer estateId) {
        //此处使用estateId作为特殊标识生成物业人员id，以免不同小区添加了同一物业人员而导致主键冲突
        body.setId(this.generateId(body.getName(), String.valueOf(body.getStaffType()), String.valueOf(estateId), body.getMobile()));

        int insertBody = scPropertyStaffImpl.insert(body);
        //构建关系对象
        ScEstateStaffRel rel = new ScEstateStaffRel();
        rel.setEstateId(estateId);
        rel.setStaffId(body.getId());
        rel.setCreatorId(body.getCreatorId());
        rel.setCreateTime(body.getCreateTime());
        rel.setModifyTime(body.getModifyTime());
        int insertRel = scEstateStaffRelImpl.insert(rel);
        if (insertBody == insertRel) {
            return insertBody;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "插入主表数据与插入关系表数据个数不一致，取消");
        }
    }

    //更新物业人员
    public int updateStaff(ScPropertyStaff body) {
        return scPropertyStaffImpl.update(body);
    }

    //删除物业人员
    @Transactional
    public int deleteStaff(Integer id, Integer estateId) {
        int deleteBody = scPropertyStaffImpl.delete(id);
        int deleteRel = scEstateStaffRelImpl.delete(estateId, id);
        if (deleteBody == deleteRel) {
            return deleteBody;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除主表数据与删除关系表数据个数不一致，取消");
        }
    }

    // 批量删除物业人员
    @Transactional
    public int deleteStaff(List<Integer> ids, Integer estateId) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        int deletesBody = scPropertyStaffImpl.deletes(ids);
        int deletesRel = scEstateStaffRelImpl.deletes(estateId, ids);
        if (deletesBody == deletesRel) {
            return deletesBody;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除主表数据与删除关系表数据个数不一致，取消");
        }
    }
}
