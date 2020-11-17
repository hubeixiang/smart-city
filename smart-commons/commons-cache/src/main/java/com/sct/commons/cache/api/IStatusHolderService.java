package com.sct.commons.cache.api;

import com.sct.commons.cache.entity.CacheEntity;
import com.sct.commons.cache.entity.DefaultICacheExpire;
import com.sct.commons.cache.entity.ICacheExpire;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Collection;
import java.util.Date;

public interface IStatusHolderService<E extends CacheEntity> {
    public String holderName();

    /**
     * 默认注册需要判断超时的对象
     *
     * @param status
     */
    void register(E status);

    /**
     * 注册对象到缓存中
     *
     * @param status    要注册的对象
     * @param canExpire 对象是否需要判断超时
     *                  true:需要判断超时时,会在超时后自动注销对象
     *                  false:不会自动注销对象,需要主动调用注销接口才会注销对象
     */
    void register(E status, boolean canExpire);

    /**
     * 判断key是否存在
     *
     * @param uuid
     * @return
     */
    public boolean isExists(String uuid);

    /**
     * 依据key直接获取注册的对象
     *
     * @param uuid
     * @return
     */
    E get(String uuid);

    /**
     * 主动清除、注销指定的key代表的对象
     *
     * @param uuid
     */
    void clean(String uuid);

    /**
     * 自动循环判断所有的对象是否超时,超时对象自动注销
     *
     * @return
     */
    Collection<E> clean();


    /**
     * 创建默认的超时设置
     *
     * @param canExpire  是否设置超时时间
     * @param timeExpire 超时的时长,单位秒. 60:代表60秒后超时
     * @return
     */
    default ICacheExpire createDefaultICacheExpire(boolean canExpire, int timeExpire) {
        Date createDate = new Date();
        ICacheExpire cacheExpire = new DefaultICacheExpire();
        cacheExpire.setCreateDate(createDate);
        if (canExpire) {
            cacheExpire.setExpiredDate(DateUtils.addSeconds(createDate, timeExpire));
        }
        return cacheExpire;
    }
}
