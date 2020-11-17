package com.sct.commons.db.autoconfigure.entity;

import com.sct.commons.constants.ConstantCommonAutoConfigure;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jdbc 连接相关的属性
 */
@Component
@ConfigurationProperties(prefix = ConstantCommonAutoConfigure.Datasource.Datasource_jdbc_PREFIX)
public class DataSourceJdbcProperties {
    private String url;
    private String username;
    private String password;
    private Integer maxActive;
    private String driver;
    private String validationQuery = "SELECT 1";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }
}
