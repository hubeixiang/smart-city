package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScLog;

import java.util.List;

public interface ScLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_log
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_log
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int insert(ScLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_log
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    ScLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_log
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    List<ScLog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_log
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int updateByPrimaryKey(ScLog record);
}