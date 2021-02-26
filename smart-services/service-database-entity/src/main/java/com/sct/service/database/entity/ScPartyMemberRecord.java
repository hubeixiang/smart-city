package com.sct.service.database.entity;

import com.sct.service.database.extend.BaseExtend;

import java.io.Serializable;
import java.util.Date;

public class ScPartyMemberRecord extends BaseExtend implements Serializable {
    private Integer id;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.party_member_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer partyMemberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.record_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date recordDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.record_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer recordType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.record_abstract
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String recordAbstract;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.record_detail
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private String recordDetail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_party_member_record.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_party_member_record
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.party_member_id
     *
     * @return the value of sc_party_member_record.party_member_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getPartyMemberId() {
        return partyMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.party_member_id
     *
     * @param partyMemberId the value for sc_party_member_record.party_member_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setPartyMemberId(Integer partyMemberId) {
        this.partyMemberId = partyMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.record_date
     *
     * @return the value of sc_party_member_record.record_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.record_date
     *
     * @param recordDate the value for sc_party_member_record.record_date
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.record_type
     *
     * @return the value of sc_party_member_record.record_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getRecordType() {
        return recordType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.record_type
     *
     * @param recordType the value for sc_party_member_record.record_type
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.record_abstract
     *
     * @return the value of sc_party_member_record.record_abstract
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getRecordAbstract() {
        return recordAbstract;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.record_abstract
     *
     * @param recordAbstract the value for sc_party_member_record.record_abstract
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRecordAbstract(String recordAbstract) {
        this.recordAbstract = recordAbstract == null ? null : recordAbstract.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.record_detail
     *
     * @return the value of sc_party_member_record.record_detail
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public String getRecordDetail() {
        return recordDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.record_detail
     *
     * @param recordDetail the value for sc_party_member_record.record_detail
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setRecordDetail(String recordDetail) {
        this.recordDetail = recordDetail == null ? null : recordDetail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.create_time
     *
     * @return the value of sc_party_member_record.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.create_time
     *
     * @param createTime the value for sc_party_member_record.create_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.creator_id
     *
     * @return the value of sc_party_member_record.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.creator_id
     *
     * @param creatorId the value for sc_party_member_record.creator_id
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_party_member_record.modify_time
     *
     * @return the value of sc_party_member_record.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_party_member_record.modify_time
     *
     * @param modifyTime the value for sc_party_member_record.modify_time
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}