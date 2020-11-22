package com.sct.application.authorization.controller.index;

import com.sct.application.authorization.properties.OauthClient;
import org.springframework.util.StringUtils;

public class AuthorizationCodeUrl {
    private String httpPath;
    private String contextPath;
    private String redirect;
    private String state;
    private String defaultState = "uyyApY";
    private String oauthUrl = "oauth/authorize";
    private String oauthTokenUrl = "oauth/token";
    private String grantType = "authorization_code";
    private CredentialsEntity cre = new CredentialsEntity();
    private MyUrl codeMyUrl;
    private MyUrl tokenMyUrl;

    public AuthorizationCodeUrl(OauthClient oauthClient, String httpPath, String contextPath, String redirect, String state) {
        cre.setClientId(oauthClient.getClientId());
        cre.setClientSecret(oauthClient.getClientSecret());
        cre.setScope(oauthClient.getClientScope());
        this.httpPath = httpPath;
        this.contextPath = contextPath;
        this.redirect = redirect;
        this.state = state;
        stmyUrl();
    }

    private void stmyUrl() {
        stCodeMyUrl();
        stTokenMyUrl();
    }

    private void stCodeMyUrl() {
        //http://127.0.0.1:7090/oauth/authorize?client_id=messaging-client&redirect_uri=http://localhost:7081/authorized&response_type=code&scope=message.read%20message.write&state=uyyApY
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s&%s&%s", httpPath, oauthUrl
                    , cre.getParamResponseType("code")
                    , cre.getParamScope(), cre.getParamClient()
                    , cre.getParamVar("state", StringUtils.isEmpty(state) ? defaultState : state)
                    , cre.getParamRedirectUri(redirect));
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s&%s&%s", httpPath, contextPath, oauthUrl
                    , cre.getParamResponseType("code")
                    , cre.getParamScope(), cre.getParamClient()
                    , cre.getParamVar("state", StringUtils.isEmpty(state) ? defaultState : state)
                    , cre.getParamRedirectUri(redirect));
        }
        codeMyUrl = new MyUrl();
        codeMyUrl.setMethod("GET");
        codeMyUrl.setUri(uri);
    }

    private void stTokenMyUrl() {
        //http://127.0.0.1:7090/oauth/token?grant_type=authorization_code&code=ddT1gE&redirect_uri=http://localhost:7081/authorized&client_id=message-client&client_secret=secret
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s&%s&%s", httpPath, oauthTokenUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamVar("code", "第一步中获取的code值")
                    , cre.getParamClient()
                    , cre.getParamVar("state", StringUtils.isEmpty(state) ? defaultState : state)
                    , cre.getParamRedirectUri(redirect));
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s&%s&%s", httpPath, contextPath, oauthTokenUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamVar("code", "第一步中获取的code值")
                    , cre.getParamClient()
                    , cre.getParamVar("state", StringUtils.isEmpty(state) ? defaultState : state)
                    , cre.getParamRedirectUri(redirect));
        }
        tokenMyUrl = new MyUrl();
        tokenMyUrl.setMethod("POST");
        tokenMyUrl.setUri(uri);
    }

    public MyUrl getCodeMyUrl() {
        return codeMyUrl;
    }

    public MyUrl getTokenMyUrl() {
        return tokenMyUrl;
    }
}
