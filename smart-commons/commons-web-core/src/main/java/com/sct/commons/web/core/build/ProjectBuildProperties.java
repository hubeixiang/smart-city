package com.sct.commons.web.core.build;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ConditionalOnResource(resources = "classpath:/config/build.properties")
@ConfigurationProperties(prefix = "build")
@PropertySource("classpath:/config/build.properties")
public class ProjectBuildProperties {
    private String module;
    private String version;
    private String revision;
    private String timestamp;
    private String startUpTime = Instant.now().toString();

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStartUpTime() {
        return startUpTime;
    }

    public void setStartUpTime(String startUpTime) {
        this.startUpTime = startUpTime;
    }
}
