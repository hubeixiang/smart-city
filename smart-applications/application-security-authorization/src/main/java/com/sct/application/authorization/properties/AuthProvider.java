package com.sct.application.authorization.properties;

public class AuthProvider implements Comparable<AuthProvider> {
    private String name;
    private boolean enable;
    private int order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(AuthProvider o) {
        if (order == o.getOrder()) {
            return 0;
        } else if (order > o.getOrder()) {
            return 1;
        } else {
            return -1;
        }
    }
}
