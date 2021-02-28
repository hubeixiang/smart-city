package com.sct.application.business.service;

import com.sct.commons.utils.id.IntIdGenerator;

public class IntIdGeneratorUtils {
    public static Integer generateId(String... args) {
        StringBuffer sb = new StringBuffer("%s");
        for (int i = 1; i < args.length; i++) {
            sb.append(":%s");
        }
        String idStr = String.format(String.valueOf(sb), args);
        return IntIdGenerator.getCRC32(idStr);
    }
}
