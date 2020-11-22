package com.sct.service.core.web.rsa;

import com.sct.commons.cache.SimpleMemoryCacheHolderServicesManager;
import com.sct.commons.cache.entity.CacheEntity;
import com.sct.service.core.web.rsa.cache.RasKeyCacheInfo;
import com.sct.service.core.web.rsa.cache.RsaKeyCacheEntity;

public class RsaKeyGenerateUtil {
    public static final String CACHE_KEY_PREFIX = "rsa_private_key_";
    private static RsaKeyGenerate rsaKeyGenerate = new RsaKeyGenerate();

    public final static RasKeyCacheInfo createRasKeyCacheInfo() {
        return createRasKeyCacheInfo(false);
    }

    public final static String decryptDataOnJava(String data, String PRIVATEKEY) {
        return rsaKeyGenerate.decryptDataOnJava(data, PRIVATEKEY);
    }

    public final static RasKeyCacheInfo createRasKeyCacheInfo(boolean isNew) {
        RsaKeyInfo keyInfo = rsaKeyGenerate.genKeyPair(isNew);
        RasKeyCacheInfo rasKeyCacheInfo = new RasKeyCacheInfo();
        rasKeyCacheInfo.setPublicKey(keyInfo.getPublicKeyDecode());
        rasKeyCacheInfo.setPrivateKey(keyInfo.getPrivateKeyDecode());
        return rasKeyCacheInfo;
    }

    public final static boolean cacheSimpleObject(RasKeyCacheInfo rasKeyCacheInfo, int timeout) {
        String key = getCachekey(rasKeyCacheInfo);
        RsaKeyCacheEntity rsaKeyCacheEntity = new RsaKeyCacheEntity();
        rsaKeyCacheEntity.setCacheKey(key);
        if (timeout <= 0) {
            rasKeyCacheInfo.setExpireDate(0);
        } else {
            long currentTime = System.currentTimeMillis();
            rasKeyCacheInfo.setExpireDate(currentTime + (timeout * 1000));
        }
        rsaKeyCacheEntity.setCacheValue(rasKeyCacheInfo);
        SimpleMemoryCacheHolderServicesManager.getInstance().setBaseCacheEntity(rsaKeyCacheEntity, timeout);
        return true;
    }

    public static RsaKeyCacheEntity takeOutFromCache(RasKeyCacheInfo rasKeyCacheInfo) {
        String key = getCachekey(rasKeyCacheInfo);
        CacheEntity cacheEntity = SimpleMemoryCacheHolderServicesManager.getInstance().getBaseCacheEntity(key);
        if (cacheEntity != null) {
            return (RsaKeyCacheEntity) cacheEntity;
        }
        return null;
    }

    public static void remove(RasKeyCacheInfo rasKeyCacheInfo) {
        String key = getCachekey(rasKeyCacheInfo);
        SimpleMemoryCacheHolderServicesManager.getInstance().remove(key);
    }

    private static String getCachekey(RasKeyCacheInfo rasKeyCacheInfo) {
        return String.format("%s_%s", CACHE_KEY_PREFIX, rasKeyCacheInfo.getPublicKey());
    }
}
