package com.sct.service.database.entity;

import java.io.Serializable;
import java.util.Date;

public class ScCommunityLeader implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.name
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.post
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private String post;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.mobile
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.pic_url
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private String picUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.order
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Integer order;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.community_id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Integer communityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.term
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private String term;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.term_begin
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Date termBegin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.term_end
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Date termEnd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.create_time
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.creator_id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_community_leader.modify_time
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_community_leader
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.id
     *
     * @return the value of sc_community_leader.id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.id
     *
     * @param id the value for sc_community_leader.id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.name
     *
     * @return the value of sc_community_leader.name
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.name
     *
     * @param name the value for sc_community_leader.name
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.post
     *
     * @return the value of sc_community_leader.post
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public String getPost() {
        return post;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.post
     *
     * @param post the value for sc_community_leader.post
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.mobile
     *
     * @return the value of sc_community_leader.mobile
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.mobile
     *
     * @param mobile the value for sc_community_leader.mobile
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.pic_url
     *
     * @return the value of sc_community_leader.pic_url
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.pic_url
     *
     * @param picUrl the value for sc_community_leader.pic_url
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.order
     *
     * @return the value of sc_community_leader.order
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.order
     *
     * @param order the value for sc_community_leader.order
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.community_id
     *
     * @return the value of sc_community_leader.community_id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.community_id
     *
     * @param communityId the value for sc_community_leader.community_id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.term
     *
     * @return the value of sc_community_leader.term
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public String getTerm() {
        return term;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.term
     *
     * @param term the value for sc_community_leader.term
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.term_begin
     *
     * @return the value of sc_community_leader.term_begin
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Date getTermBegin() {
        return termBegin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.term_begin
     *
     * @param termBegin the value for sc_community_leader.term_begin
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setTermBegin(Date termBegin) {
        this.termBegin = termBegin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.term_end
     *
     * @return the value of sc_community_leader.term_end
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Date getTermEnd() {
        return termEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.term_end
     *
     * @param termEnd the value for sc_community_leader.term_end
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.create_time
     *
     * @return the value of sc_community_leader.create_time
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.create_time
     *
     * @param createTime the value for sc_community_leader.create_time
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.creator_id
     *
     * @return the value of sc_community_leader.creator_id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.creator_id
     *
     * @param creatorId the value for sc_community_leader.creator_id
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_community_leader.modify_time
     *
     * @return the value of sc_community_leader.modify_time
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_community_leader.modify_time
     *
     * @param modifyTime the value for sc_community_leader.modify_time
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}