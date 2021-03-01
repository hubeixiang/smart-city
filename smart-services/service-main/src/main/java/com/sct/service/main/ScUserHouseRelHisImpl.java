package com.sct.service.main;

import com.sct.service.database.entity.ScUserHouseRelHis;
import com.sct.service.database.mapper.ScUserHouseRelHisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScUserHouseRelHisImpl {

    @Autowired
    private ScUserHouseRelHisMapper scUserHouseRelHisMapper;
    
    public int insert(ScUserHouseRelHis scUserHouseRelHis) {
        return scUserHouseRelHisMapper.insert(scUserHouseRelHis);
    }

    public int delete(Integer userId, Integer houseId) {
        return scUserHouseRelHisMapper.deleteByPrimaryKey(userId, houseId);
    }

    public int update(ScUserHouseRelHis scUserHouseRelHis) {
        return scUserHouseRelHisMapper.updateByPrimaryKey(scUserHouseRelHis);
    }

    public ScUserHouseRelHis select(Integer userId, Integer houseId) {
        return scUserHouseRelHisMapper.selectByPrimaryKey(userId, houseId);
    }
}
