package com.sct.service.core.service;

import com.sct.commons.utils.JsonUtils;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QPagingUtil {
    public static QPaging toQPaging(Paging paging) {
        if (paging == null) {
            return null;
        }
        int startIndex = paging.getOffset();
        int endIndex = startIndex + paging.getPageSize();
        return QPaging.of(startIndex, endIndex);
    }

    public static <E> List<String> parserResultColumns(List<E> entitys) {
        List<String> columns = null;
        if (entitys == null || entitys.size() == 0) {
            columns = new ArrayList<>();
        } else {
            columns = parserResultColumn(entitys.get(0));
        }
        return columns;
    }

    public static <E> List<String> parserResultColumn(E entity) {
        List<String> columns = new ArrayList<>();
        if (entity != null) {
            Map<String, Object> map = JsonUtils.fromJson(JsonUtils.toJson(entity), Map.class);
            columns.addAll(map.keySet());
        }
        return columns;
    }
}
