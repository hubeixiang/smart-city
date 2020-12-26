package com.sct.application.security.autoconfigure;

import com.sct.service.sucurity.support.password.factory.SctPasswordEncoderFactories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityRestAutoConfigure {

    @Value("${ilogin.password.type:sct}")
    private String passwordType;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return SctPasswordEncoderFactories.getInstance().getPasswordEncoder(passwordType);
    }
}
