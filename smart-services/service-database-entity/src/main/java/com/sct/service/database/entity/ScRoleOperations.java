package com.sct.service.database.entity;

import java.io.Serializable;

public class ScRoleOperations implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_role_operations
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_role_operations.role_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer roleId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_role_operations.dev_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer devType;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_role_operations.oper_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer operId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_role_operations.role_id
     *
     * @return the value of sc_role_operations.role_id
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_role_operations.role_id
     *
     * @param roleId the value for sc_role_operations.role_id
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_role_operations.dev_type
     *
     * @return the value of sc_role_operations.dev_type
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getDevType() {
        return devType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_role_operations.dev_type
     *
     * @param devType the value for sc_role_operations.dev_type
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_role_operations.oper_id
     *
     * @return the value of sc_role_operations.oper_id
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getOperId() {
        return operId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_role_operations.oper_id
     *
     * @param operId the value for sc_role_operations.oper_id
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setOperId(Integer operId) {
        this.operId = operId;
    }
}