package com.sct.service.database.mapper;

import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScOrganizationCondition;
import com.sct.service.database.entity.ScOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScOrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_organization
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeys(@Param("ids") List<Integer> ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_organization
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_organization
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScOrganization selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_organization
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScOrganization> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_organization
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScOrganization record);

    public int selectConditionCount(@Param("condition") ScOrganizationCondition condition);

    public List<ScOrganization> selectConditionPage(@Param("condition") ScOrganizationCondition condition, @Param("qPaging") QPaging qPaging);

    public List<ScOrganization> selectCondition(@Param("condition") ScOrganizationCondition condition);

    int updateRegisterStatus(@Param("id") Integer id,@Param("registerStatus") Integer registerStatus);
}