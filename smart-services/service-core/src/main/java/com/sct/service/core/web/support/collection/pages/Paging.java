package com.sct.service.core.web.support.collection.pages;

public interface Paging {
    /**
     * 返回分页的下标,从0开始计算
     *
     * @return
     */
    public int getPageIndex();

    /**
     * 返回设置的每页大小, 最小值为1
     *
     * @return
     */
    public int getPageSize();

    /**
     * 当前页的开始行号, >=0
     *
     * @return
     */
    default int getOffset() {
        return getPageIndex() * getPageSize();
    }
}
