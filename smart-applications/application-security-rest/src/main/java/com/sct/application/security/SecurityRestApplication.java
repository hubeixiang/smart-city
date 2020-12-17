package com.sct.application.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sct"})
public class SecurityRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityRestApplication.class, args);
    }
}
