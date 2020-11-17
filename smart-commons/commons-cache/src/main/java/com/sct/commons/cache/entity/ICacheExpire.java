package com.sct.commons.cache.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sven on 2017/3/10.
 */
public interface ICacheExpire extends Serializable {
    /**
     * 创建时间
     *
     * @return
     */
    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    /**
     * 超时过期时间,为null则表明永不过期
     *
     * @return
     */
    public Date getExpiredDate();

    public void setExpiredDate(Date expiredDate);

    /**
     * 清除时销毁信息
     */
    public abstract void destory();

    /**
     * 判断是否超时
     *
     * @return
     */
    default boolean isExpired() {
        if (getExpiredDate() == null) {
            return false;
        }
        if (getExpiredDate().getTime() < System.currentTimeMillis()) {
            return true;
        }
        return false;
    }
}
