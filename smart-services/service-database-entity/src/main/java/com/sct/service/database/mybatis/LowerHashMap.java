package com.sct.service.database.mybatis;

import java.util.HashMap;

public class LowerHashMap<V> extends HashMap<String, V> {
    private static final long serialVersionUID = 2L;

    @Override
    public V put(String key, V value) {
        if (key == null || key.trim().length() == 0) {
            throw new IllegalArgumentException("Empty key is not allowed.");
        }

        key = key.toLowerCase();

        return super.put(key, value);
    }
}
