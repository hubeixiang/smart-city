package com.sct.application.authorization.support.sms;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    //依据手机号查询用户信息
    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 进行身份认证的逻辑
     *
     * @param authentication 就是我们传入的Token
     * @return
     * @throws AuthenticationException
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Assert.isInstanceOf(SmsCodeAuthenticationToken.class, authentication,
                () -> messages.getMessage(
                        "SmsCodeAuthenticationProvider.onlySupports",
                        "Only SmsCodeAuthenticationToken is supported"));
        //利用UserDetailsService获取用户信息，拿到用户信息后重新组装一个已认证的Authentication
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());  //根据手机号码拿到用户信息
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * AuthenticationManager挑选一个AuthenticationProvider来处理传入进来的Token就是根据supports方法来判断的
     *
     * @param aClass
     * @return
     */
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);   //判断出入进来的是不是SmsCodeAuthenticationToken类型
    }
}
