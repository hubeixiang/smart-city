package com.sct.service.core.web.support.collection;

import java.util.ArrayList;

public class ResultList<E> extends ArrayList<E> {
    public static ResultList of() {
        return new ResultList();
    }
}
