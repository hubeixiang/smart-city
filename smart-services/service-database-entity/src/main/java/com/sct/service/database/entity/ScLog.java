package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.log_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer logLevel;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.oper_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer operId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.oper_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer operType;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.dev_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer devType;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_log.info
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_log
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.id
     *
     * @return the value of sc_log.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.id
     *
     * @param id the value for sc_log.id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.log_level
     *
     * @return the value of sc_log.log_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getLogLevel() {
        return logLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.log_level
     *
     * @param logLevel the value for sc_log.log_level
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.oper_id
     *
     * @return the value of sc_log.oper_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getOperId() {
        return operId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.oper_id
     *
     * @param operId the value for sc_log.oper_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.oper_type
     *
     * @return the value of sc_log.oper_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getOperType() {
        return operType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.oper_type
     *
     * @param operType the value for sc_log.oper_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.create_time
     *
     * @return the value of sc_log.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.create_time
     *
     * @param createTime the value for sc_log.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.creator_id
     *
     * @return the value of sc_log.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.creator_id
     *
     * @param creatorId the value for sc_log.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.dev_type
     *
     * @return the value of sc_log.dev_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getDevType() {
        return devType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.dev_type
     *
     * @param devType the value for sc_log.dev_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_log.info
     *
     * @return the value of sc_log.info
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_log.info
     *
     * @param info the value for sc_log.info
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}