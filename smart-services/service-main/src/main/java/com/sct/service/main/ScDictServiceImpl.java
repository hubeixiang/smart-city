package com.sct.service.main;

import com.sct.service.database.entity.ScDict;
import com.sct.service.database.mapper.ScDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ScDictServiceImpl {

    private final static Map<String, List<ScDict>> cacheData = new ConcurrentHashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    @Autowired
    private ScDictMapper scDictMapper;

    public void init() {
        loadData();
    }

    /**
     * 重新加载缓存的字段数据
     */
    public void refresh() {
        loadData();
    }

    public Map<String, List<ScDict>> getAllData() {
        Map<String, List<ScDict>> data = new HashMap<>();
        data.putAll(cacheData);
        return data;
    }

    public List<ScDict> getData(String fieldId) {
        List<ScDict> data = null;
        try {
            readWriteLock.readLock().lock();
            data = cacheData.get(fieldId);
        } finally {
            readWriteLock.readLock().unlock();
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    private void loadData() {
        List<ScDict> data = scDictMapper.selectAll();
        Map<String, List<ScDict>> dataMap = new HashMap<>();
        for (ScDict scDict : data) {
            List<ScDict> tmp = null;
            if (dataMap.containsKey(scDict.getFieldId())) {
                tmp = dataMap.get(scDict.getFieldId());
            } else {
                tmp = new ArrayList<>();
                dataMap.put(scDict.getFieldId(), tmp);
            }
            tmp.add(scDict);
        }
        try {
            readWriteLock.writeLock().lock();
            cacheData.clear();
            cacheData.putAll(dataMap);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
