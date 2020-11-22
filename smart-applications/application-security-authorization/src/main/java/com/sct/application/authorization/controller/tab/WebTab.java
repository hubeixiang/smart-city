package com.sct.application.authorization.controller.tab;

public class WebTab implements java.io.Serializable {
    private String tabId;
    private String activeClass = "";
    private String tabContentActiveClass = "";
    private boolean isActive;
    private String href;
    private String tipText;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        if (isActive()) {
            activeClass = "active";
            tabContentActiveClass = "tab-pane fade in active";
        } else {
            activeClass = "";
            tabContentActiveClass = "tab-pane fade";
        }
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTipText() {
        return tipText;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }

    public String getActiveClass() {
        return activeClass;
    }

    public void setActiveClass(String activeClass) {
        this.activeClass = activeClass;
    }

    public String getTabContentActiveClass() {
        return tabContentActiveClass;
    }

    public void setTabContentActiveClass(String tabContentActiveClass) {
        this.tabContentActiveClass = tabContentActiveClass;
    }
}
