package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScRole;

import java.util.List;

public interface ScRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_role
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_role
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int insert(ScRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_role
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    ScRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_role
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    List<ScRole> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_role
     *
     * @mbg.generated Sat Nov 28 16:00:48 CST 2020
     */
    int updateByPrimaryKey(ScRole record);
}