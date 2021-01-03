package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScResident;
import java.util.List;

public interface ScResidentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_resident
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_resident
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    int insert(ScResident record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_resident
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    ScResident selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_resident
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    List<ScResident> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_resident
     *
     * @mbg.generated Sat Jan 02 12:33:42 CST 2021
     */
    int updateByPrimaryKey(ScResident record);
}