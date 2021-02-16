package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScCommunityParty;

import java.util.List;

public interface ScCommunityPartyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScCommunityParty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScCommunityParty selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScCommunityParty> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_party
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScCommunityParty record);
}