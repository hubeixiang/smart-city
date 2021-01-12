package com.sct.summary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sct"})
public class SummaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SummaryApplication.class, args);
    }
}
