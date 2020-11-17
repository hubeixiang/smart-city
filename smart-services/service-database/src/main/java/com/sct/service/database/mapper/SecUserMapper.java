package com.sct.service.database.mapper;


import com.sct.service.users.data.entity.AuthorityUser;

import java.util.List;

public interface SecUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user
     *
     * @mbg.generated Wed Aug 12 10:15:44 CST 2020
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user
     *
     * @mbg.generated Wed Aug 12 10:15:44 CST 2020
     */
    int insert(AuthorityUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user
     *
     * @mbg.generated Wed Aug 12 10:15:44 CST 2020
     */
    AuthorityUser selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user
     *
     * @mbg.generated Wed Aug 12 10:15:44 CST 2020
     */
    List<AuthorityUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_user
     *
     * @mbg.generated Wed Aug 12 10:15:44 CST 2020
     */
    int updateByPrimaryKey(AuthorityUser record);

    int updateByLoginrelevant(AuthorityUser record);

    int updatePasswordByUserId(AuthorityUser record);

    int updatePasswordAndPwdExpireDateByUserId(AuthorityUser record);

    /**
     * 按照登录用户名查询
     *
     * @param loginId
     * @return
     */
    AuthorityUser selectByLoginId(String loginId);

    AuthorityUser selectByUserId(String userId);

    /**
     * 按照手机号进行登录
     *
     * @param userMobile
     * @return
     */
    AuthorityUser selectByUserMobile(String userMobile);
}