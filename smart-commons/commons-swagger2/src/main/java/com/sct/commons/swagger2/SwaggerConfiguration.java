package com.sct.commons.swagger2;

import com.sct.commons.constants.ConstantCommonAutoConfigure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConditionalOnMissingBean(SwaggerConfiguration.class)
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".rest.package:com.sct}")
    private String restPackage;
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".rest.title:Restful接口描述}")
    private String restTitle;
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".rest.desc:Java后台服务提供的Restful接口}")
    private String restDesc;
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".rest.version:1.0.0}")
    private String restVersion;
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".contact.name:sct}")
    private String contactName;
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".contact.url:www.sct.com}")
    private String contactUrl;
    @Value("${" + ConstantCommonAutoConfigure.Swagger2.SWAGGER2_PREFIX + ".contact.email:xxx@263.com}")
    private String contactEmail;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(restPackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(contactName, contactUrl, contactEmail);
        return new ApiInfoBuilder()
                .title(restTitle)
                .description(restDesc)
                .contact(contact)   // 联系方式
                .version(restVersion)  // 版本
                .build();
    }

}
