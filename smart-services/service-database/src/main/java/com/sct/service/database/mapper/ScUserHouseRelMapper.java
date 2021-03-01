package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScUserHouseRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScUserHouseRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_house_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("houseId") Integer houseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_house_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScUserHouseRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_house_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScUserHouseRel selectByPrimaryKey(@Param("userId") Integer userId, @Param("houseId") Integer houseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_house_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScUserHouseRel> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_house_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScUserHouseRel record);

    int deleteByPrimaryKeys(@Param("userIds") List<Integer> userIds, @Param("houseId") Integer houseId);
}