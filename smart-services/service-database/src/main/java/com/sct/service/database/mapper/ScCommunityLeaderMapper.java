package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScCommunityLeader;

import java.util.List;

public interface ScCommunityLeaderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScCommunityLeader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScCommunityLeader selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScCommunityLeader> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScCommunityLeader record);
}