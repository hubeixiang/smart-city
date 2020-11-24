package com.sct.service.oauth2.resourceserver.autoconfigure;

import com.sct.service.oauth2.core.constants.Oauth2Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

@Configuration
@EnableResourceServer
public class CustomResourceServerConfigurerAdapter implements ResourceServerConfigurer {

    @Value("${oauth2.client.resource-id:oauth2-resourceid}")
    private String resourceId;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId)
                .tokenStore(tokenStore);
//                .tokenServices();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher(String.format("/%s/**", Oauth2Constants.Oauth2_ResourceServer_Context_Path))
//                .authorizeRequests()
//                .antMatchers(String.format("/%s/**", Oauth2Constants.Oauth2_ResourceServer_Context_Path)).access("#oauth2.hasScope('read write')");
//
        http.requestMatchers()
                .antMatchers(String.format("/%s/**", Oauth2Constants.Oauth2_ResourceServer_Context_Path))
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
        http.csrf().disable();
    }

    @Configuration
    protected static class JwkTokenStoreConfiguration {

        private final ResourceServerProperties resource;

        public JwkTokenStoreConfiguration(ResourceServerProperties resource) {
            this.resource = resource;
        }

        @Bean
        @ConditionalOnMissingBean(ResourceServerTokenServices.class)
        public DefaultTokenServices jwkTokenServices(TokenStore jwkTokenStore) {
            DefaultTokenServices services = new DefaultTokenServices();
            services.setTokenStore(jwkTokenStore);
            return services;
        }

        @Bean
        @ConditionalOnMissingBean(TokenStore.class)
        public TokenStore jwkTokenStore() {
            return new JwkTokenStore(this.resource.getJwk().getKeySetUri());
        }
    }

}