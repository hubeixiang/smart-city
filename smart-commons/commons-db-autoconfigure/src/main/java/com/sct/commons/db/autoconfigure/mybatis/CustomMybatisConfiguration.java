package com.sct.commons.db.autoconfigure.mybatis;

import com.sct.commons.db.autoconfigure.datasource.CustomDataSourceConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@ConditionalOnClass({SqlSessionFactoryBean.class, MapperScannerConfigurer.class})
@AutoConfigureAfter({CustomDataSourceConfiguration.class})
public class CustomMybatisConfiguration {

    @ConditionalOnBean(name = "dataSource")
    @ConditionalOnMissingBean(SqlSessionFactory.class)
    @Bean
    public static MyBatisBeanRegistryFactoryPostProcessor myBatisBeanRegistryFactoryPostProcessor(ConfigurableEnvironment environment) {
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);// 设置Binder
        return new MyBatisBeanRegistryFactoryPostProcessor(environment);
    }

}
