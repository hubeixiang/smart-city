/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sct.application.authorization.autoconfigure.oauth2;

import com.sct.service.oauth2.core.constants.Oauth2Constants;
import com.sct.service.sucurity.support.password.factory.SctPasswordEncoderFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证服务器自定义实现
 *
 * @author Joe Grandja
 */
@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationSecurityConfigurer extends AuthorizationServerConfigurerAdapter {

    //依据登录用户id查询,解决token刷新时只有user_id的用户信息获取
    @Autowired
    @Qualifier("authorityUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.jdbc(dataSource)
                //配置客户端查询时用户密码的加密方式,不配置时默认使用NoOpPasswordEncoder
                .passwordEncoder(createOauthClientPasswordEncoder());
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint
        endpoints.pathMapping("/oauth/authorize", String.format("%s/authorize", Oauth2Constants.Oauth2_Context_Path));
        //org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
        endpoints.pathMapping("/oauth/token", String.format("%s/token", Oauth2Constants.Oauth2_Context_Path));
        //org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint
        endpoints.pathMapping("/oauth/check_token", String.format("%s/check_token", Oauth2Constants.Oauth2_Context_Path));
        endpoints
                //oauth认证服务,在认证用户密码登录方式时需要使用
                .authenticationManager(this.authenticationManager)
                //oauth认证服务刷新token时使用的用户认证,刷新token时,使用的是token中存储的user_id进行用户验证
                .userDetailsService(this.userDetailsService)
                .tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter)
                //默认是POST,新增可以使用GET方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        //相比原有默认的tokenGranter,多了对Client Credentials模式中的刷新token功能的支持
        endpoints.tokenGranter((grangType, tokenRequest) -> {
            List<TokenGranter> list = new ArrayList<>();
            AuthorizationCodeTokenGranter authorizationCodeTokenGranter = new AuthorizationCodeTokenGranter(
                    endpoints.getTokenServices(),
                    endpoints.getAuthorizationCodeServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory());
            list.add(authorizationCodeTokenGranter);
            RefreshTokenGranter refreshTokenGranter = new RefreshTokenGranter(
                    endpoints.getTokenServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory());
            list.add(refreshTokenGranter);
            ImplicitTokenGranter implicitTokenGranter = new ImplicitTokenGranter(
                    endpoints.getTokenServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory());
            list.add(implicitTokenGranter);
            ClientCredentialsTokenGranter clientCredentialsTokenGranter = new ClientCredentialsTokenGranter(
                    endpoints.getTokenServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory());
            //此处设置的
            clientCredentialsTokenGranter.setAllowRefresh(true);
            list.add(clientCredentialsTokenGranter);
            if (authenticationManager != null) {
                ResourceOwnerPasswordTokenGranter resourceOwnerPasswordTokenGranter = new ResourceOwnerPasswordTokenGranter(authenticationManager,
                        endpoints.getTokenServices(),
                        endpoints.getClientDetailsService(),
                        endpoints.getOAuth2RequestFactory());
                list.add(resourceOwnerPasswordTokenGranter);
            }
            CompositeTokenGranter delegate = new CompositeTokenGranter(list);
            return delegate.grant(grangType, tokenRequest);
        });
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //刷新token，验证token等等请求允许,不添加则页面请求时会报 401 未验证错误
        security.passwordEncoder(createOauthClientPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    private PasswordEncoder createOauthClientPasswordEncoder() {
        return SctPasswordEncoderFactories.getInstance().getDelegatingPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        JwtTokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter);
        tokenStore.setApprovalStore(approvalStore());
        return tokenStore;
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }
}