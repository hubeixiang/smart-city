package com.sct.commons.db.autoconfigure.mybatis;

import com.sct.commons.constants.ConstantCommonAutoConfigure;
import com.sct.commons.db.autoconfigure.dao.CommonSqlSessionDaoSupport;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;


public class MyBatisBeanRegistryFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private ConfigurableEnvironment environment;
    private BeanDefinitionRegistry registry;

    public MyBatisBeanRegistryFactoryPostProcessor(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.registry = registry;
        createMybatisRelatedBeans();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private void createMybatisRelatedBeans() {
        //引入了mybatis-spring-boot-starter后,MybatisAutoConfiguration会自动初始化相关的配置
        createSqlSessionFactoryBean();
        createCommonSqlSessionDaoSupport();
        //MapperScannerConfigurer 因为需要外部配置base-package,因此还是走此处初始化
        createMapperScannerConfigurerBean();
    }

    private void createSqlSessionFactoryBean() {
        String configLocation = environment.getProperty(ConstantCommonAutoConfigure.Mybatis.Mybatis_factor_config);
        String mapperLocations = environment.getProperty(ConstantCommonAutoConfigure.Mybatis.Mybatis_factor_mapper);
        String enable = environment.getProperty(ConstantCommonAutoConfigure.Mybatis.Mybatis_factor_enable);
        if (StringUtils.isEmpty(enable) || !enable.equalsIgnoreCase("true"))
            return;
        final BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);
        builder.addPropertyReference("dataSource", "dataSource");
        builder.addPropertyValue("configLocation", configLocation);
        builder.addPropertyValue("mapperLocations", mapperLocations);

        registry.registerBeanDefinition("sqlSessionFactory", builder.getBeanDefinition());
    }

    private void createCommonSqlSessionDaoSupport() {
        final BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CommonSqlSessionDaoSupport.class);
        builder.addConstructorArgReference("sqlSessionFactory");

        registry.registerBeanDefinition("commonSqlSessionDaoSupport", builder.getBeanDefinition());
    }

    private void createMapperScannerConfigurerBean() {
        String basePackage = environment.getProperty(ConstantCommonAutoConfigure.Mybatis.Mybatis_scanner_package);
        final BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        builder.addPropertyValue("sqlSessionFactoryBeanName", "sqlSessionFactory");
        builder.addPropertyValue("basePackage", basePackage);

        registry.registerBeanDefinition("mapperScannerConfigurer", builder.getBeanDefinition());
    }
}
