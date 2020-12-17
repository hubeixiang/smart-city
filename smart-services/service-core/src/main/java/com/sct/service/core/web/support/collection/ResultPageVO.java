package com.sct.service.core.web.support.collection;

import com.sct.service.core.web.support.collection.pages.Paging;

public interface ResultPageVO {
    /**
     * 返回分页信息
     *
     * @return
     */
    public Paging getPaging();

    /**
     * 返回分页结果集
     *
     * @return
     */
    public ResultVO getResult();
}
