package com.sct.service.database.mapper;

import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScBuildingCondition;
import com.sct.service.database.entity.ScBuilding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScBuildingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScBuilding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScBuilding selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScBuilding> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScBuilding record);

    int deleteByPrimaryKeys(@Param("ids") List<Integer> ids);

    int selectConditionCount(@Param("condition") ScBuildingCondition condition);

    List<ScBuilding> selectConditionPage(@Param("condition") ScBuildingCondition condition, @Param("qPaging") QPaging qPaging);

    List<ScBuilding> selectCondition(@Param("condition") ScBuildingCondition condition);

    List selectByEstateId(@Param("estateId") Integer estateId);
}