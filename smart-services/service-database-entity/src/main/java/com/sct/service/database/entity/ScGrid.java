package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScGrid implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.name
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.community_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.grid_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Integer gridId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.comment
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.is_marker
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Boolean isMarker;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.create_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.creator_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private String creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid.modify_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_grid
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.id
     *
     * @return the value of sc_grid.id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.name
     *
     * @return the value of sc_grid.name
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.grid_id
     *
     * @return the value of sc_grid.grid_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Integer getGridId() {
        return gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.grid_id
     *
     * @param gridId the value for sc_grid.grid_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.comment
     *
     * @return the value of sc_grid.comment
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.is_marker
     *
     * @return the value of sc_grid.is_marker
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public Boolean getIsMarker() {
        return isMarker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.is_marker
     *
     * @param isMarker the value for sc_grid.is_marker
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setIsMarker(Boolean isMarker) {
        this.isMarker = isMarker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.create_time
     *
     * @return the value of sc_grid.create_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid.creator_id
     *
     * @param creatorId the value for sc_grid.creator_id
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid.modify_time
     *
     * @return the value of sc_grid.modify_time
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
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
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}