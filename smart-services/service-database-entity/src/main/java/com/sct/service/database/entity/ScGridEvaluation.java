package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScGridEvaluation implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer gridId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.grid_manager_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer gridManagerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.evaluation_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer evaluationType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.evaluation_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date evaluationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.evaluation_result
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String evaluationResult;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_grid_evaluation.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_grid_evaluation
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.grid_id
     *
     * @return the value of sc_grid_evaluation.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getGridId() {
        return gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.grid_id
     *
     * @param gridId the value for sc_grid_evaluation.grid_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.grid_manager_id
     *
     * @return the value of sc_grid_evaluation.grid_manager_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getGridManagerId() {
        return gridManagerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.grid_manager_id
     *
     * @param gridManagerId the value for sc_grid_evaluation.grid_manager_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setGridManagerId(Integer gridManagerId) {
        this.gridManagerId = gridManagerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.evaluation_type
     *
     * @return the value of sc_grid_evaluation.evaluation_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getEvaluationType() {
        return evaluationType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.evaluation_type
     *
     * @param evaluationType the value for sc_grid_evaluation.evaluation_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.evaluation_date
     *
     * @return the value of sc_grid_evaluation.evaluation_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getEvaluationDate() {
        return evaluationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.evaluation_date
     *
     * @param evaluationDate the value for sc_grid_evaluation.evaluation_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.evaluation_result
     *
     * @return the value of sc_grid_evaluation.evaluation_result
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getEvaluationResult() {
        return evaluationResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.evaluation_result
     *
     * @param evaluationResult the value for sc_grid_evaluation.evaluation_result
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult == null ? null : evaluationResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.create_time
     *
     * @return the value of sc_grid_evaluation.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.create_time
     *
     * @param createTime the value for sc_grid_evaluation.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.creator_id
     *
     * @return the value of sc_grid_evaluation.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.creator_id
     *
     * @param creatorId the value for sc_grid_evaluation.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_grid_evaluation.modify_time
     *
     * @return the value of sc_grid_evaluation.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_grid_evaluation.modify_time
     *
     * @param modifyTime the value for sc_grid_evaluation.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}