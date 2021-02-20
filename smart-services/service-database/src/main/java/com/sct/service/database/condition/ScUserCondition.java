package com.sct.service.database.condition;

import com.sct.commons.i18n.I18nMessageUtil;

import java.util.List;

public class ScUserCondition {
    private String userName;
    private Integer sex;
    private String mobile;
    private String email;
    private String cardId;
    private Boolean validStatus;
    private List<Integer> userTypes;

    public static void checkSQLinjectionException(ScUserCondition condition) {
        if (checkSQLinjection(condition)) {
            String msg = I18nMessageUtil.getInstance().getMessage("Controller.Paramter.CheckSQLinjection");
            throw new RuntimeException(msg);
        }
    }

    /**
     * 检查是否有sql注入危险
     * 主要检查在mybaits中使用${}引用的变量
     *
     * @param condition
     * @return true:有sql注入风险
     */
    public static boolean checkSQLinjection(ScUserCondition condition) {
        return NonSQLinjectionUtil.checkSQLinjection(condition.getUserName());
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
