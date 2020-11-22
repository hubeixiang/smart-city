package com.sct.application.authorization.support.username.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 重新用户名密码登录验证方式的流程
 * 与原有spring security固有流程不同的是
 * 1. 增加用户查询后的密码验证失败处理方式,更新用户对应的状态
 * 2. 增加用户密码验证成功后的处理方式,更新锁定时间为空
 */
public abstract class HiosAbstractUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static Logger logger = LoggerFactory.getLogger(HiosAbstractUserDetailsAuthenticationProvider.class);

    protected abstract PasswordEncoder getPasswordEncoder();

    protected abstract UserDetailsService getUserDetailsService();

    /**
     * 密码正确时的检查项
     *
     * @return
     */
    protected abstract UserDetailsChecker getHiosPasswordSuccessUserDetailsChecker();

    /**
     * 密码错误时的检查项
     *
     * @return
     */
    protected abstract UserDetailsChecker getHiosPasswordErrorUserDetailsChecker();


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
//        String presentedPassword = CustomWebAuthenticationDetailsUtil.decoder(authentication);
        String presentedPassword = authentication.getCredentials().toString();
        if (getPasswordEncoder() != null && !getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            //判断是否有密码重试次数限制
            if (getHiosPasswordErrorUserDetailsChecker() != null) {
                getHiosPasswordErrorUserDetailsChecker().check(userDetails);
            } else {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"));
            }
        } else if (getHiosPasswordSuccessUserDetailsChecker() != null) {
            getHiosPasswordSuccessUserDetailsChecker().check(userDetails);
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}
