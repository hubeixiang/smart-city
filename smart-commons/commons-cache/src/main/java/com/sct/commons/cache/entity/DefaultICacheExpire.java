package com.sct.commons.cache.entity;

import java.util.Date;

public class DefaultICacheExpire implements ICacheExpire {
    private Date createDate;
    private Date expiredDate;

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getExpiredDate() {
        return expiredDate;
    }

    @Override
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public void destory() {

    }
}
