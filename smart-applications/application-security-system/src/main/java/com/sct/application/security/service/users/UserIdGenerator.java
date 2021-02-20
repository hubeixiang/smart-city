package com.sct.application.security.service.users;

import com.sct.commons.utils.id.IntIdGenerator;
import org.apache.commons.lang3.StringUtils;

public class UserIdGenerator {
    public static int crc32Id(String prefix, String identityId) {
        if (StringUtils.isEmpty(identityId)) {
            throw new RuntimeException("identityId can't null!");
        }
        String str = null;
        if (StringUtils.isEmpty(prefix)) {
            str = identityId;
        } else {
            str = String.format("%s:%s", prefix, identityId);
        }
        return IntIdGenerator.getCRC32(str);
    }
}
