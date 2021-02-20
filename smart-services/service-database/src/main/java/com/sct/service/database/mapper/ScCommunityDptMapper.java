package com.sct.service.database.mapper;

import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScCommunityDptCondition;
import com.sct.service.database.entity.ScCommunityDpt;
import com.sct.service.database.entity.ScUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScCommunityDptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int insert(ScCommunityDpt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    ScCommunityDpt selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    List<ScCommunityDpt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_community_dpt
     *
     * @mbg.generated Tue Feb 16 11:09:04 CST 2021
     */
    int updateByPrimaryKey(ScCommunityDpt record);

    public int selectConditionCount(@Param("condition") ScCommunityDptCondition condition);

    public List<ScUser> selectConditionPage(@Param("condition") ScCommunityDptCondition condition, @Param("qPaging") QPaging qPaging);

    public List<ScUser> selectCondition(@Param("condition") ScCommunityDptCondition condition);
}