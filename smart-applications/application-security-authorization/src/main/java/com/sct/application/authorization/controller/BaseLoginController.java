package com.sct.application.authorization.controller;


import com.sct.application.authorization.controller.entity.UserLoginStatus;
import com.sct.application.authorization.controller.index.AuthorizationCodeUrl;
import com.sct.application.authorization.controller.index.BaseUrl;
import com.sct.application.authorization.controller.index.CheckTokenUri;
import com.sct.application.authorization.controller.index.ClientCredentialsUrl;
import com.sct.application.authorization.controller.index.ImplicitUrl;
import com.sct.application.authorization.controller.index.RefreshTokenUri;
import com.sct.application.authorization.controller.index.ResourceOwnerPasswordCredentialsUrl;
import com.sct.application.authorization.properties.ILoginProperties;
import com.sct.application.authorization.properties.OauthClient;
import com.sct.application.authorization.support.session.CustomSessionRegistry;
import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.commons.utils.web.WebUtil;
import com.sct.service.users.data.entity.AuthorityUser;
import com.sct.service.users.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseLoginController {
    private static Logger logger = LoggerFactory.getLogger(BaseLoginController.class);
    @Autowired
    protected CustomSessionRegistry customSessionRegistry;
    @Autowired
    protected ILoginProperties iLoginProperties;

    protected void assembleUserLoginStatus(UserLoginStatus userLoginStatus, SecurityUser customUser) {
        AuthorityUser authorityUser = (AuthorityUser) customUser.getUserEntity();
        userLoginStatus.setId(customUser.getUserPkId());
        userLoginStatus.setWxId(authorityUser.getWxId());
        userLoginStatus.setUserId(customUser.getLoginId());
        userLoginStatus.setUserName(customUser.getSecurityUserName());
        userLoginStatus.setUserStatus(authorityUser.getUserStatus());
        userLoginStatus.setMobile(authorityUser.getUserMobile());
        userLoginStatus.setEmail(authorityUser.getUserEmail());
    }

    protected void appendOauthUrl(Model model, HttpServletRequest request) {
        BaseUrl baseUrl = createBaseUrl(request);
        String httpPath = baseUrl.getHttpPath();
        String contextPath = baseUrl.getContextPath();
        String redirect = String.format("%s/%s", httpPath, "index");
        String state = "xyzabcn";
        String clientId = System.getProperty("clientId", null);
        String clientSecret = System.getProperty("clientSecret", null);
        String scope = System.getProperty("clientScope", null);
        OauthClient defaultOauthClient = iLoginProperties.getOauthClient();
        if (StringUtils.isEmpty(clientId)) {
            clientId = defaultOauthClient.getClientId();
        }
        if (StringUtils.isEmpty(clientSecret)) {
            clientSecret = defaultOauthClient.getClientSecret();
        }
        if (StringUtils.isEmpty(scope)) {
            scope = defaultOauthClient.getClientScope();
        }
        OauthClient oauthClient = new OauthClient();
        oauthClient.setClientId(clientId);
        oauthClient.setClientSecret(clientSecret);
        oauthClient.setClientScope(scope);
        //authorizationCode
        AuthorizationCodeUrl authorizationCodeUrl = new AuthorizationCodeUrl(oauthClient, httpPath, contextPath, redirect, state);
        model.addAttribute("authorizationCode_code_url", authorizationCodeUrl.getCodeMyUrl());
        model.addAttribute("authorizationCode_code_token_url", authorizationCodeUrl.getTokenMyUrl());
        //client credentials
        ClientCredentialsUrl clientCredentialsUrl = new ClientCredentialsUrl(oauthClient, httpPath, contextPath, redirect);
        model.addAttribute("client_credentials_token_url", clientCredentialsUrl.getMyUrl());
        //implicit
        ImplicitUrl implicitUrl = new ImplicitUrl(oauthClient, httpPath, contextPath, redirect);
        model.addAttribute("implicit_token_url", implicitUrl.getMyUrl());
        model.addAttribute("spec_implicit_token_url", implicitUrl.getSpecUrl());
        //password
        String userName = getUserName();
        ResourceOwnerPasswordCredentialsUrl passwordCredentialsUrl = new ResourceOwnerPasswordCredentialsUrl(oauthClient, httpPath, contextPath, redirect, userName);
        model.addAttribute("password_token_url", passwordCredentialsUrl.getMyUrl());
        //refresh token
        RefreshTokenUri refreshTokenUri = new RefreshTokenUri(oauthClient, httpPath, contextPath);
        model.addAttribute("refresh_token_url", refreshTokenUri.getMyUrl());
        //check token
        CheckTokenUri checkTokenUri = new CheckTokenUri(oauthClient, httpPath, contextPath);
        model.addAttribute("check_token_url", checkTokenUri.getMyUrl());
    }

    protected void appendUserName(Model model) {
        String userName = getUserName();
        if (!StringUtils.isEmpty(userName)) {
            model.addAttribute("loginUserName", userName);
        }
    }

    private String getUserName() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) object;
            String username = securityUser.getSecurityUserName();
            return username;
        } else if (object instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) object;
            String username = userDetails.getUsername();
            return username;
        } else {
            return object.toString();
        }
    }

    protected void appendCommonLoginTips(Model model) {
        model.addAttribute("page_title", getMessage("WelcomeLoginController.page_title"));
        model.addAttribute("header_title", getMessage("WelcomeLoginController.header_title"));
        model.addAttribute("submit_tip", getMessage("WelcomeLoginController.submit_tip"));
        model.addAttribute("online_user_tips", getMessage("WelcomeLoginController.online_user_tips"));
    }

    protected BaseUrl createBaseUrl(HttpServletRequest request) {
        String httpPath = WebUtil.getBasePath(request);
        String contextPath = null;
        return new BaseUrl(httpPath, contextPath);
    }

    protected String getMessage(String code) {
        return I18nMessageUtil.getInstance().getMessage(code);
    }


}
