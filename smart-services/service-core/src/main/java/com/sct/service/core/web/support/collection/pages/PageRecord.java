package com.sct.service.core.web.support.collection.pages;

public class PageRecord implements Paging {
    private int index = 0;
    private int size = 1;

    @Override
    public int getPageIndex() {
        return index;
    }

    public void setPageIndex(int index) {
        this.index = index;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    public void setPageSize(int size) {
        this.size = size;
    }
}
