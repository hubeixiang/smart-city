package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScPartyLearnRecord implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_learn_record.party_member_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer partyMemberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_learn_record.course_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_learn_record.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_learn_record.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_learn_record.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_learn_record.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_party_learn_record
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_learn_record.party_member_id
     *
     * @return the value of sc_party_learn_record.party_member_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getPartyMemberId() {
        return partyMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_learn_record.party_member_id
     *
     * @param partyMemberId the value for sc_party_learn_record.party_member_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPartyMemberId(Integer partyMemberId) {
        this.partyMemberId = partyMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_learn_record.course_id
     *
     * @return the value of sc_party_learn_record.course_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_learn_record.course_id
     *
     * @param courseId the value for sc_party_learn_record.course_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_learn_record.comment
     *
     * @return the value of sc_party_learn_record.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_learn_record.comment
     *
     * @param comment the value for sc_party_learn_record.comment
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_learn_record.create_time
     *
     * @return the value of sc_party_learn_record.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_learn_record.create_time
     *
     * @param createTime the value for sc_party_learn_record.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_learn_record.creator_id
     *
     * @return the value of sc_party_learn_record.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_learn_record.creator_id
     *
     * @param creatorId the value for sc_party_learn_record.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_learn_record.modify_time
     *
     * @return the value of sc_party_learn_record.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_learn_record.modify_time
     *
     * @param modifyTime the value for sc_party_learn_record.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}