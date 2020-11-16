package com.sct.commons.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author sven
 */
public class MapUtils {

	public static boolean isNullOrEmpty(Map map) {
		return map == null || map.isEmpty();
	}

	public static Map<String, Object> removeEmptyValue(Map<String, Object> map) {
		Map<String, Object> newMap = new HashMap<>();
		if (map == null)
			return newMap;
		map.entrySet().stream().filter(entry -> !HsvenStringUtils.isNullOrEmpty(entry.getValue())).forEach(entry -> {
			newMap.put(entry.getKey(), entry.getValue());
		});
		return newMap;
	}

	public static <K extends Comparable, V> Map<K, V> sortMapByKey(Map<K, V> data) {
		Map<K, V> data_ = new LinkedHashMap<>();
		List<K> list = new LinkedList<>(data.keySet());
		Collections.sort(list);
		for (K k : list) {
			data_.put(k, data.get(k));
		}
		return data_;
	}

	public static <K, V> Map<K, V> merge(Map<K, V> map, Map<K, V> maps) {
		return merge(new HashMap<>(), map, maps);
	}

	public static <K, V> Map<K, V> merge(Map<K, V> target, Map<K, V> map, Map<K, V>... maps) {
		target.putAll(map);
		for (int i = 0; i < maps.length; i++) {
			target.putAll(maps[i]);
		}
		return target;
	}

	public static <K, V> Map<K, V> merge(Supplier<Map<K, V>> supplier, Map<K, V> map, Map<K, V>... maps) {
		return merge(supplier.get(), map, maps);
	}

}
