package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScGridRange;

import java.util.List;

public interface ScGridRangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_range
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int insert(ScGridRange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_range
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    List<ScGridRange> selectAll();
}