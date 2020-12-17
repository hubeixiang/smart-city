package com.sct.service.core.web.support.collection.pages;

public class PageResponse extends PageRecord {
    private int totalSize = 0;

    public static PageResponse of(int pageIndex, int pageSize, int totalSize) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPageIndex(pageIndex);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalSize(totalSize);
        return pageResponse;
    }

    /**
     * 返回查询结果总数
     *
     * @return
     */
    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
