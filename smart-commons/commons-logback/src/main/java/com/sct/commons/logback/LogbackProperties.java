package com.sct.commons.logback;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "logback")
public class LogbackProperties {
    String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
