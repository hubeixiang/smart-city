package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScCommunityLeader;
import java.util.List;

public interface ScCommunityLeaderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    int insert(ScCommunityLeader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    ScCommunityLeader selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    List<ScCommunityLeader> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_leader
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    int updateByPrimaryKey(ScCommunityLeader record);
}