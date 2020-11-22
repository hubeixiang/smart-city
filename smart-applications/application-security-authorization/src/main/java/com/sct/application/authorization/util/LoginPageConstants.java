package com.sct.application.authorization.util;

/**
 * _for_message : 在message.properties中的key值
 * _for_pageid : 返回给页面时的addAttribute(key,value)中的key值
 * _for_page_submitid: 如果页面提交给服务处理时的parameter的key值
 */
public final class LoginPageConstants {
    public final static String login_screen_sms_code_send_mobile_not_null_for_message = "login.screen.sms.code.send.mobile.not.null";
    public final static String login_screen_sms_code_send_mobile_not_null_for_pageid = "screenSmsCodeSendMobile_not_null";
    public final static String login_screen_sms_code_send_mobile_not_null_for_page_submitid = "screenSmsCodeSendMobile_not_null";

    public final static String login_password_encrypt_public_key_for_pageid = "publickey";
    public final static String login_password_encrypt_public_key_for_page_submitid = "publickey";
}
