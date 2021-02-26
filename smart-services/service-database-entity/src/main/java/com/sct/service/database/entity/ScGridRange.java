package com.sct.service.database.entity;

import com.sct.service.database.extend.BaseExtend;

import java.io.Serializable;

public class ScGridRange extends BaseExtend implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_range.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer gridId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_range.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_range.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_range.order
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer order;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_grid_range
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_range.grid_id
     *
     * @return the value of sc_grid_range.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getGridId() {
        return gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_range.grid_id
     *
     * @param gridId the value for sc_grid_range.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_range.longitude
     *
     * @return the value of sc_grid_range.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_range.longitude
     *
     * @param longitude the value for sc_grid_range.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_range.latitude
     *
     * @return the value of sc_grid_range.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_range.latitude
     *
     * @param latitude the value for sc_grid_range.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_range.order
     *
     * @return the value of sc_grid_range.order
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_range.order
     *
     * @param order the value for sc_grid_range.order
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setOrder(Integer order) {
        this.order = order;
    }
}