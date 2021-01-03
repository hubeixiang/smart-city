package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScBuilding;
import java.util.List;

public interface ScBuildingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    int insert(ScBuilding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    ScBuilding selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    List<ScBuilding> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_building
     *
     * @mbg.generated Sat Jan 02 12:33:41 CST 2021
     */
    int updateByPrimaryKey(ScBuilding record);
}