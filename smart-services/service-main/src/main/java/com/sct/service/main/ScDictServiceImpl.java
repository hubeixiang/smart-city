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

    private final static Map<String, ScDictGroup> cacheData = new ConcurrentHashMap<>();
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

    public Map<String, ScDictGroup> getAllData() {
        Map<String, ScDictGroup> data = new HashMap<>();
        data.putAll(cacheData);
        return data;
    }

    public List<ScDict> getData(String fieldId) {
        List<ScDict> data = null;
        try {
            readWriteLock.readLock().lock();
            ScDictGroup scDictGroup = cacheData.get(fieldId);
            if (scDictGroup != null) {
                data = scDictGroup.getList();
            }
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
        Map<String, ScDictGroup> dataMap = new HashMap<>();
        for (ScDict scDict : data) {
            ScDictGroup group = null;
            if (dataMap.containsKey(scDict.getFieldId())) {
                group = dataMap.get(scDict.getFieldId());
            } else {
                group = ScDictGroup.of();
                dataMap.put(scDict.getFieldId(), group);
            }
            ScDictGroup.add(group, scDict);
        }
        try {
            readWriteLock.writeLock().lock();
            cacheData.clear();
            cacheData.putAll(dataMap);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public ScDict find(String fieldId, Integer dictId) {
        ScDictGroup group = cacheData.get(fieldId);
        if (group == null) {
            return null;
        } else {
            return group.getMap().get(dictId);
        }
    }
}
