package com.sct.service.database.condition;

import com.sct.service.database.entity.ScLog;

import java.util.Date;

public class ScLogCondition extends ScLog {
    private Date createTimeStart;
    private Date createTimeEnd;

    public static ScLogCondition of() {
        return new ScLogCondition();
    }

    public static void checkParam(ScLogCondition condition) {

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
}
