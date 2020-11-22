package com.sct.application.authorization.support.additional;

/**
 * 登录入口方式,主要是为了区别在用户退出时的退出方式
 */
public enum LoginType {
    //内蒙的cas登录
    nmhighwaycas,
    //电信运营商4a登录
    telecom_4a
}
