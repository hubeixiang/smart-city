package com.sct.service.database.condition;

import java.util.Date;

public class ScSsoLogCondition {
    private String userId;
    private Integer ssoLogType;
    private Date createTimeStart;
    private Date createTimeEnd;
    private Integer operationStatus;
    private String requestIp;

    public static ScSsoLogCondition of() {
        return new ScSsoLogCondition();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSsoLogType() {
        return ssoLogType;
    }

    public void setSsoLogType(Integer ssoLogType) {
        this.ssoLogType = ssoLogType;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Integer getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
}
