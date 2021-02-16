package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScPartyMember;

import java.util.List;

public interface ScPartyMemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScPartyMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScPartyMember selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScPartyMember> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScPartyMember record);
}