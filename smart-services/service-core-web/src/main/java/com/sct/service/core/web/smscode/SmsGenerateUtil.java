package com.sct.service.core.web.smscode;

import com.sct.commons.cache.SimpleMemoryCacheHolderServicesManager;
import com.sct.commons.cache.entity.CacheEntity;
import com.sct.commons.utils.RandomStringUtil;
import com.sct.service.core.web.smscode.cache.SmsCacheEntity;
import com.sct.service.core.web.smscode.cache.SmsInfo;

public final class SmsGenerateUtil {
    private final static String CACHE_KEY_PREFIX = "smsVerify_code_";

    /**
     * 生成短信码
     *
     * @param mobile
     * @return
     */
    public static SmsInfo createSmsCode(String mobile) {
        String code = RandomStringUtil.randomChar(4);
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setCode(code);
        smsInfo.setPhone(mobile);
        return smsInfo;
    }

    public static boolean cacheSimpleObject(SmsInfo smsInfo, int timeout) {
        String key = getKey(smsInfo);
        SmsCacheEntity smsCacheEntity = new SmsCacheEntity();
        smsCacheEntity.setCacheKey(key);
        if (timeout <= 0) {
            smsInfo.setExpireDate(0);
        } else {
            long currentTime = System.currentTimeMillis();
            smsInfo.setExpireDate(currentTime + (timeout * 1000));
        }
        smsCacheEntity.setCacheValue(smsInfo);
        SimpleMemoryCacheHolderServicesManager.getInstance().setBaseCacheEntity(smsCacheEntity, timeout);
        return true;
    }

    public static SmsCacheEntity takeOutFromCache(SmsInfo smsInfo) {
        String key = getKey(smsInfo);
        CacheEntity cacheEntity = SimpleMemoryCacheHolderServicesManager.getInstance().getBaseCacheEntity(key);
        if (cacheEntity != null) {
            return (SmsCacheEntity) cacheEntity;
        }
        return null;
    }

    public static void remove(SmsInfo smsInfo) {
        String key = getKey(smsInfo);
        SimpleMemoryCacheHolderServicesManager.getInstance().remove(key);
    }

    private static String getKey(SmsInfo smsInfo) {
        String key = String.format("%s_%s", CACHE_KEY_PREFIX, smsInfo.getPhone());
        return key;
    }
}
