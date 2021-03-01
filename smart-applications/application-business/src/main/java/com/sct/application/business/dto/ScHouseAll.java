package com.sct.application.business.dto;

import com.sct.service.database.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "ScHouseAll", description = "房屋全部信息")
public class ScHouseAll implements Serializable {
    @ApiModelProperty(value = "房屋信息")
    private ScHouse scHouse;
    @ApiModelProperty(value = "居住人员信息")
    private List<ScResident> scResidents;

    public ScHouseAll() {
    }

    public ScHouseAll(ScHouse scHouse, List<ScResident> scResidents) {
        this.scHouse = scHouse;
        this.scResidents = scResidents;
    }

    public ScHouse getScHouse() {
        return scHouse;
    }

    public void setScHouse(ScHouse scHouse) {
        this.scHouse = scHouse;
    }

    public List<ScResident> getScResidents() {
        return scResidents;
    }

    public void setScResidents(List<ScResident> scResidents) {
        this.scResidents = scResidents;
    }
}
