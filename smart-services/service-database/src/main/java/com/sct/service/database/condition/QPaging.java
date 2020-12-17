package com.sct.service.database.condition;

public class QPaging {
    private int startIndex;
    private int endIndex;

    public static QPaging of(int startIndex, int endIndex) {
        QPaging qPaging = new QPaging();
        qPaging.setStartIndex(startIndex);
        qPaging.setEndIndex(endIndex);
        return qPaging;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
