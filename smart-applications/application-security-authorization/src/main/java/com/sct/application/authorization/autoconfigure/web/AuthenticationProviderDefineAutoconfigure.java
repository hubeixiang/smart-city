package com.sct.application.authorization.autoconfigure.web;

import com.sct.application.authorization.properties.AuthProvider;
import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.support.providers.AuthenticationProviderDefine;
import com.sct.application.authorization.support.providers.CustomDaoAuthenticationProviderConfigurer;
import com.sct.application.authorization.support.providers.SmsCodeAuthenticationProviderAutoConfigurer;
import com.sct.service.sucurity.support.password.factory.SctPasswordEncoderFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationProviderDefineAutoconfigure {
    private final static List<AuthenticationProviderDefine> initAuthenticationProviderDefine = new ArrayList<>();

    @Autowired
    private ILoginProperties iLoginProperties;

    /**
     * 依据登录用户id查询
     */
    @Qualifier("authorityUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    public static List<AuthenticationProviderDefine> getInitAuthenticationProviderDefine() {
        return initAuthenticationProviderDefine;
    }

    public PasswordEncoder secUserPasswordEncoder() {
        return SctPasswordEncoderFactories.getInstance().getPasswordEncoder(iLoginProperties.getPassword().getType());
    }

    @PostConstruct
    public void init() {
        //对生效的登录验证方式进行排序,初始化
        //序号值越低的，优先使用验证
        List<AuthProvider> authProviders = iLoginProperties.getAuthProvider();
        AuthProvider[] sorts = new AuthProvider[authProviders.size()];
        sorts = authProviders.toArray(sorts);
        Arrays.sort(sorts);
        for (AuthProvider authProvider : sorts) {
            if (!authProvider.isEnable()) {
                continue;
            }
            switch (authProvider.getName()) {
                case "username":
                    daoAuthenticationProviderDefine();
                    break;
                case "sms":
                    smsAuthenticationProviderDefine();
                    break;
                default:
                    throw new RuntimeException("Error find auth-provider:" + authProvider.getName());
            }
        }
    }

    private AuthenticationProviderDefine daoAuthenticationProviderDefine() {
        CustomDaoAuthenticationProviderConfigurer customDaoAuthenticationProviderConfigurer = new CustomDaoAuthenticationProviderConfigurer(secUserPasswordEncoder(), userDetailsService, null, null);
        initAuthenticationProviderDefine.add(customDaoAuthenticationProviderConfigurer);
        return customDaoAuthenticationProviderConfigurer;
    }

    private AuthenticationProviderDefine smsAuthenticationProviderDefine() {
        SmsCodeAuthenticationProviderAutoConfigurer smsCodeAuthenticationProviderAutoConfigurer = new SmsCodeAuthenticationProviderAutoConfigurer(userDetailsService);
        initAuthenticationProviderDefine.add(smsCodeAuthenticationProviderAutoConfigurer);
        return smsCodeAuthenticationProviderAutoConfigurer;
    }

}
