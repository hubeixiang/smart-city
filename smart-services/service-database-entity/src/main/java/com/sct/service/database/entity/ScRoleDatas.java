package com.sct.service.database.entity;

import java.io.Serializable;

public class ScRoleDatas implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_role_datas.role_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_role_datas.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer gridId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_role_datas.grid_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer gridLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_role_datas
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_role_datas.role_id
     *
     * @return the value of sc_role_datas.role_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_role_datas.role_id
     *
     * @param roleId the value for sc_role_datas.role_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_role_datas.grid_id
     *
     * @return the value of sc_role_datas.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getGridId() {
        return gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_role_datas.grid_id
     *
     * @param gridId the value for sc_role_datas.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_role_datas.grid_level
     *
     * @return the value of sc_role_datas.grid_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getGridLevel() {
        return gridLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_role_datas.grid_level
     *
     * @param gridLevel the value for sc_role_datas.grid_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridLevel(Integer gridLevel) {
        this.gridLevel = gridLevel;
    }
}