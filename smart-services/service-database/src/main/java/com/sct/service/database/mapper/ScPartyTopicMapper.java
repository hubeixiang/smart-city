package com.sct.service.database.mapper;

import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScPartyTopicCondition;
import com.sct.service.database.entity.ScOrganization;
import com.sct.service.database.entity.ScPartyTopic;
import com.sct.service.database.mybatis.LowerHashMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScPartyTopicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_topic
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    int deleteByPrimaryKeys(@Param("ids") List<Integer> ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_topic
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScPartyTopic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_topic
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScPartyTopic selectByPrimaryKey(@Param("id") Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_topic
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScPartyTopic> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_topic
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScPartyTopic record);

    public int selectConditionCount(@Param("condition") ScPartyTopicCondition condition);

    public List<ScOrganization> selectConditionPage(@Param("condition") ScPartyTopicCondition condition, @Param("qPaging") QPaging qPaging);

    public List<ScOrganization> selectCondition(@Param("condition") ScPartyTopicCondition condition);

    public List<LowerHashMap> selectConditionOverview(@Param("condition") ScPartyTopicCondition condition);

}