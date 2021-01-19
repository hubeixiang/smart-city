package com.sct.service.database.dict;

import com.sct.commons.utils.dict.Description;
import com.sct.commons.utils.dict.IdEnum;

public enum ScOrganizationRegisterStatus implements IdEnum<ScOrganizationRegisterStatus> {
    @Description("未登记") UN_REGISTER(0, "未登记"),
    @Description("已登记") REGISTER(1, "已登记"),
    @Description("已注销") CANCEL(2, "已注销");

    private int id;
    private String description;

    ScOrganizationRegisterStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ScOrganizationRegisterStatus getType(Integer id) {
        return IdEnum.getType(ScOrganizationRegisterStatus.class, id);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
