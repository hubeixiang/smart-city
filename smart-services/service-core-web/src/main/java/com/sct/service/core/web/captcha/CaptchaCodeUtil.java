package com.sct.service.core.web.captcha;

import com.sct.commons.cache.SimpleMemoryCacheHolderServicesManager;
import com.sct.commons.cache.entity.CacheEntity;
import com.sct.service.core.web.captcha.cache.CaptchaCacheEntity;
import com.sct.service.core.web.captcha.cache.CaptchaInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 生成验证码的工具类
 */
public final class CaptchaCodeUtil {
    private final static String CACHE_KEY_PREFIX = "captchaVerify_code_";
    private final static CaptchaCode captchaCode = new CaptchaCode();

    /**
     * 随机生成图片验证码内容
     *
     * @return
     */
    public final static CaptchaCodeInfo createVerifyCodeInfo() {
        return captchaCode.createVerifyCodeInfo();
    }

    /**
     * 将生产的图片buffer输出为图片
     *
     * @param image
     * @param out
     * @throws IOException
     */
    public final static void outputPicture(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    public final static boolean cacheSimpleObject(CaptchaCodeInfo captchaCodeInfo, int timeout) {
        String key = getCachekey(captchaCodeInfo);
        CaptchaCacheEntity captchaCacheEntity = new CaptchaCacheEntity();
        captchaCacheEntity.setCacheKey(key);
        CaptchaInfo captchaInfo = new CaptchaInfo();
        captchaInfo.setCode(captchaCodeInfo.getCode());
        if (timeout <= 0) {
            captchaInfo.setExpireDate(0);
        } else {
            long currentTime = System.currentTimeMillis();
            captchaInfo.setExpireDate(currentTime + (timeout * 1000));
        }
        captchaCacheEntity.setCacheValue(captchaInfo);
        SimpleMemoryCacheHolderServicesManager.getInstance().setBaseCacheEntity(captchaCacheEntity, timeout);
        return true;
    }

    public static CaptchaCacheEntity takeOutFromCache(CaptchaCodeInfo captchaCodeInfo) {
        String key = getCachekey(captchaCodeInfo);
        CacheEntity cacheEntity = SimpleMemoryCacheHolderServicesManager.getInstance().getBaseCacheEntity(key);
        if (cacheEntity != null) {
            return (CaptchaCacheEntity) cacheEntity;
        }
        return null;
    }

    public static void remove(CaptchaCodeInfo captchaCodeInfo) {
        String key = getCachekey(captchaCodeInfo);
        SimpleMemoryCacheHolderServicesManager.getInstance().remove(key);
    }

    private static String getCachekey(CaptchaCodeInfo captchaCodeInfo) {
        return String.format("%s_%s", CACHE_KEY_PREFIX, captchaCodeInfo.getKey());
    }
}
