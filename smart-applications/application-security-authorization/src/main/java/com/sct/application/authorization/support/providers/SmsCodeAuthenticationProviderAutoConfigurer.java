package com.sct.application.authorization.support.providers;

import com.sct.application.authorization.support.sms.SmsCodeAuthenticationProvider;
import com.sct.application.authorization.support.sms.SmsCodeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 短信登录
 */
public class SmsCodeAuthenticationProviderAutoConfigurer implements AuthenticationProviderDefine {
    //依据手机号查询用户信息,因为是验证登录,因此不用设置用户密码处理
    private UserDetailsService userDetailsService;

    public SmsCodeAuthenticationProviderAutoConfigurer(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Class<?> supportsAuthenticationToken() {
        return SmsCodeAuthenticationToken.class;
    }

    @Autowired
    public SmsCodeAuthenticationProvider authenticationProvider() {
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        return smsCodeAuthenticationProvider;
    }
}
