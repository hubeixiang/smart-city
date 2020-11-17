package com.sct.commons.cache.entity;

public interface CacheEntity<E> {
    /**
     * 获取缓存的key信息
     *
     * @return
     */
    public String getCacheKey();

    public void setCacheKey(String cacheKey);

    /**
     * 获取缓存的内容
     *
     * @return
     */
    public E getCacheValue();

    public void setCacheValue(E cacheValue);

    /**
     * 获取配置的超时设置信息
     *
     * @return
     */
    public ICacheExpire getICacheExpire();

    /**
     * 设置缓存对象的超时相关信息
     *
     * @param iCacheExpire
     */
    public void setICacheExpire(ICacheExpire iCacheExpire);

    public void destory();

    /**
     * 判断是否超时
     *
     * @return
     */
    default boolean isExpired() {
        if (getICacheExpire() != null && getICacheExpire().isExpired()) {
            return true;
        }
        return false;
    }
}
