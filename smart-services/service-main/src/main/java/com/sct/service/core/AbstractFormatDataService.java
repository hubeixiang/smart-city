package com.sct.service.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sct.commons.utils.JsonUtils;
import com.sct.commons.utils.data.DataColumnarUtil;
import com.sct.service.database.condition.FieldMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractFormatDataService {
    protected static TypeReference<List<Map<String, Object>>> typeReference = new TypeReference<List<Map<String, Object>>>() {
    };
    private static Logger logger = LoggerFactory.getLogger(AbstractFormatDataService.class);

    protected <T> List<Map<String, Object>> format2TwoDimensionalArrayInternal(List<T> datas) {
        String json = JsonUtils.toJson(datas);
        List<Map<String, Object>> lists = JsonUtils.fromJson(json, typeReference);
        if (lists == null) {
            logger.error(String.format("format2TwoDimensionalArrayInternal fromJson 2 List error[%s]", json));
        }
        return lists;
    }

    protected List<String> format2Line(Map<String, Object> map, List<FieldMapping> mappings) {
        List<String> line = new ArrayList<>();
        boolean allEmpty = true;
        for (FieldMapping fieldMapping : mappings) {
            Object value = map.get(fieldMapping.getFieldName());
            String str = DataColumnarUtil.adapterValueForString(value);
            if (str == null) {
                line.add("");
            } else {
                line.add(str);
                allEmpty = false;
            }
        }
        if (allEmpty) {
            return null;
        } else {
            return line;
        }
    }
}
