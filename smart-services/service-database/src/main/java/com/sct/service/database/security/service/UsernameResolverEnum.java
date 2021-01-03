package com.sct.service.database.security.service;

public enum UsernameResolverEnum {
    //用户表主键
    userPk,
    //登录用户名
    loginId,
    //用户手机号
    cellPhone,
    //邮箱
    email,
    //三种混合,登录名+手机+邮箱
    mix_3,
    //微信id
    wxId
}
