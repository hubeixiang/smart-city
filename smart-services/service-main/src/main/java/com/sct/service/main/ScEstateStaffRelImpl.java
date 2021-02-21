package com.sct.service.main;

import com.sct.service.database.entity.ScEstateStaffRel;
import com.sct.service.database.mapper.ScEstateStaffRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScEstateStaffRelImpl {

    @Autowired
    private ScEstateStaffRelMapper scEstateStaffRelMapper;

    public int insert(ScEstateStaffRel scEstateStaffRel) {
        return scEstateStaffRelMapper.insert(scEstateStaffRel);
    }

    public int delete(Integer estateId, Integer staffId) {
        return scEstateStaffRelMapper.deleteByPrimaryKey(estateId, staffId);
    }

    public int deletes(Integer estateId, List<Integer> ids) {
        return scEstateStaffRelMapper.deleteByPrimaryKeys(estateId, ids);
    }

    public int update(ScEstateStaffRel scEstateStaffRel) {
        return scEstateStaffRelMapper.updateByPrimaryKey(scEstateStaffRel);
    }

    public ScEstateStaffRel select(Integer estateId, Integer staffId) {
        return scEstateStaffRelMapper.selectByPrimaryKey(estateId, staffId);
    }

//    public List<Integer> selectStaffIdsByEstateId(Integer estateId) {
//        return scEstateStaffRelMapper.selectStaffIdsByEstateId(estateId);
//    }

    public int deleteByEstateId(Integer estateId) {
        return scEstateStaffRelMapper.deleteByEstateId(estateId);
    }

    public int deleteByEstateIds(List<Integer> estateIds) {
        return scEstateStaffRelMapper.deleteByEstateIds(estateIds);
    }
}
