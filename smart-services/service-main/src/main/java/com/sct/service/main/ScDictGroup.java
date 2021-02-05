package com.sct.service.main;

import com.sct.service.database.entity.ScDict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScDictGroup {
    private List<ScDict> list;
    private Map<String, ScDict> map;

    public static ScDictGroup of() {
        return of(new ArrayList<>(), new HashMap<>());
    }

    public static ScDictGroup of(List<ScDict> list, Map<String, ScDict> map) {
        ScDictGroup scDictGroup = new ScDictGroup();
        scDictGroup.setList(list);
        scDictGroup.setMap(map);
        return scDictGroup;
    }

    public static void add(ScDictGroup group, ScDict scDict) {
        group.getList().add(scDict);
        group.getMap().put(String.valueOf(scDict.getDictId()), scDict);
    }

    public List<ScDict> getList() {
        return list;
    }

    public void setList(List<ScDict> list) {
        this.list = list;
    }

    public Map<String, ScDict> getMap() {
        return map;
    }

    public void setMap(Map<String, ScDict> map) {
        this.map = map;
    }
}
