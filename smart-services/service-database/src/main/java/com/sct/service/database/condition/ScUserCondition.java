package com.sct.service.database.condition;

import java.util.List;

public class ScUserCondition implements ConditionQuery {
    private String userName;
    private Integer sex;
    private String mobile;
    private String email;
    private String cardId;
    private Boolean validStatus;
    private List<Integer> userTypes;

    public void checkSQLinjectionException(ScUserCondition condition) {
        checkSQLinjectionException(condition.getUserName());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Boolean getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Boolean validStatus) {
        this.validStatus = validStatus;
    }

    public List<Integer> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<Integer> userTypes) {
        this.userTypes = userTypes;
    }
}
