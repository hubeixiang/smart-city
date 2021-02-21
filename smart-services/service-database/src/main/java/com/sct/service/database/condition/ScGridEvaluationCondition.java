package com.sct.service.database.condition;
public class ScGridEvaluationCondition implements ConditionQuery {

    private String startDate;
    private String endDate;

    public static ScGridEvaluationCondition of() {
        return new ScGridEvaluationCondition();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
