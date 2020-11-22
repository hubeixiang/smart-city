package com.sct.application.authorization.support.providers;

import org.springframework.security.authentication.AuthenticationProvider;

public interface AuthenticationProviderDefine {
    /**
     * AuthenticationProvider 支持的验证token类型
     *
     * @return 类
     */
    public Class<?> supportsAuthenticationToken();


    /**
     * 初始化AuthenticationProvider的方法
     *
     * @return 返回实例化的对象
     */
    public AuthenticationProvider authenticationProvider();
}
