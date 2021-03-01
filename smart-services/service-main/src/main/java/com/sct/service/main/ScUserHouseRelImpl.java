package com.sct.service.main;

import com.sct.service.database.entity.ScUserHouseRel;
import com.sct.service.database.mapper.ScUserHouseRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScUserHouseRelImpl {

    @Autowired
    private ScUserHouseRelMapper scUserHouseRelMapper;
    
    public int insert(ScUserHouseRel scUserHouseRel) {
        return scUserHouseRelMapper.insert(scUserHouseRel);
    }

    public int delete(Integer userId, Integer houseId) {
        return scUserHouseRelMapper.deleteByPrimaryKey(userId, houseId);
    }

    public int deletes(List<Integer> userIds, Integer houseId) {
        return scUserHouseRelMapper.deleteByPrimaryKeys(userIds, houseId);
    }

    public int update(ScUserHouseRel scUserHouseRel) {
        return scUserHouseRelMapper.updateByPrimaryKey(scUserHouseRel);
    }

    public ScUserHouseRel select(Integer userId, Integer houseId) {
        return scUserHouseRelMapper.selectByPrimaryKey(userId, houseId);
    }
}
