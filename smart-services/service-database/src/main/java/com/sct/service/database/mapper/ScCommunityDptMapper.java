package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScCommunityDpt;

import java.util.List;

public interface ScCommunityDptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int insert(ScCommunityDpt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    ScCommunityDpt selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    List<ScCommunityDpt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int updateByPrimaryKey(ScCommunityDpt record);
}