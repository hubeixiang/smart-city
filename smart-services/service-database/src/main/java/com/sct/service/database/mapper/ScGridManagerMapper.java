package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScGridManager;

import java.util.List;

public interface ScGridManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_manager
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_manager
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScGridManager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_manager
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScGridManager selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_manager
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScGridManager> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_grid_manager
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScGridManager record);
}