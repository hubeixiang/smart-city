package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScPartyMemberRecord;

import java.util.List;

public interface ScPartyMemberRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member_record
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScPartyMemberRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_party_member_record
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScPartyMemberRecord> selectAll();
}