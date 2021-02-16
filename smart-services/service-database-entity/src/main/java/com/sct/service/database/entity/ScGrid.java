package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScGrid implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.grid_no
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String gridNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.marker_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer markerStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Float latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_grid
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.id
     *
     * @return the value of sc_grid.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.id
     *
     * @param id the value for sc_grid.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.grid_no
     *
     * @return the value of sc_grid.grid_no
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getGridNo() {
        return gridNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.grid_no
     *
     * @param gridNo the value for sc_grid.grid_no
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridNo(String gridNo) {
        this.gridNo = gridNo == null ? null : gridNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.name
     *
     * @return the value of sc_grid.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.name
     *
     * @param name the value for sc_grid.name
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.community_id
     *
     * @return the value of sc_grid.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.community_id
     *
     * @param communityId the value for sc_grid.community_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.comment
     *
     * @return the value of sc_grid.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.comment
     *
     * @param comment the value for sc_grid.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.marker_status
     *
     * @return the value of sc_grid.marker_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getMarkerStatus() {
        return markerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.marker_status
     *
     * @param markerStatus the value for sc_grid.marker_status
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setMarkerStatus(Integer markerStatus) {
        this.markerStatus = markerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.longitude
     *
     * @return the value of sc_grid.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.longitude
     *
     * @param longitude the value for sc_grid.longitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.latitude
     *
     * @return the value of sc_grid.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.latitude
     *
     * @param latitude the value for sc_grid.latitude
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.create_time
     *
     * @return the value of sc_grid.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.create_time
     *
     * @param createTime the value for sc_grid.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.creator_id
     *
     * @return the value of sc_grid.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.creator_id
     *
     * @param creatorId the value for sc_grid.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.modify_time
     *
     * @return the value of sc_grid.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.modify_time
     *
     * @param modifyTime the value for sc_grid.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}