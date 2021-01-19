package com.sct.service.database.dict;

import com.sct.commons.utils.dict.Description;
import com.sct.commons.utils.dict.IdEnum;

public enum ScOrganizationMarkerStatus implements IdEnum<ScOrganizationMarkerStatus> {
    @Description("未标注") UN_TAGGING(0, "未标注"),
    @Description("已标注") TAGGING(1, "已标注");

    private int id;
    private String description;

    ScOrganizationMarkerStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ScOrganizationMarkerStatus getType(Integer id) {
        return IdEnum.getType(ScOrganizationMarkerStatus.class, id);
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
