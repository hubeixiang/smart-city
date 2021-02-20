package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScGridRange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScGridRangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_range
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScGridRange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_range
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScGridRange> selectAll();

    ScGridRange selectByGridId(@Param("gridId") Integer gridId);

    int deleteByGridId(@Param("gridId") Integer gridId);

    int updateByPrimaryKey(ScGridRange record);
}