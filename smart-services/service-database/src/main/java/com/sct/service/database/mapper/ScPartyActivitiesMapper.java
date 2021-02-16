package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScPartyActivities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScPartyActivitiesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_activities
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("topicId") Integer topicId, @Param("partyId") Integer partyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_activities
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScPartyActivities record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_activities
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScPartyActivities selectByPrimaryKey(@Param("id") Integer id, @Param("topicId") Integer topicId, @Param("partyId") Integer partyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_activities
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScPartyActivities> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_activities
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScPartyActivities record);
}