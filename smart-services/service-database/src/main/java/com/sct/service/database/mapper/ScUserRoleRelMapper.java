package com.sct.service.database.mapper;

import com.sct.service.database.entity.ScUserRoleRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScUserRoleRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_role_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId, @Param("roleType") Integer roleType);

    int deleteByPrimaryKeyCondition(@Param("userId") Integer userId, @Param("roleId") Integer roleId, @Param("roleType") Integer roleType);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_role_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScUserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_user_role_rel
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScUserRoleRel> selectAll();
}