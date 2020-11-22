package com.sct.application.authorization.controller.index;

import com.sct.application.authorization.properties.OauthClient;
import org.springframework.util.StringUtils;

public class ImplicitUrl {
    private String httpPath;
    private String contextPath;
    private String redirect;
    private String oauthUrl = "oauth/authorize";
    private String specoauthUrl = "implicit/oauth/authorize";
    private String grantType = "implicit";
    private CredentialsEntity cre = new CredentialsEntity();
    private MyUrl myUrl;
    private MyUrl specUrl;

    public ImplicitUrl(OauthClient oauthClient, String httpPath, String contextPath, String redirect) {
        cre.setClientId(oauthClient.getClientId());
        cre.setClientSecret(oauthClient.getClientSecret());
        cre.setScope(oauthClient.getClientScope());
        this.httpPath = httpPath;
        this.contextPath = contextPath;
        this.redirect = redirect;
        stsmyUrl();
    }

    private void stsmyUrl() {
        stmyUrl();
        specUrl();
    }

    private void stmyUrl() {
        //http://authserver:7090/oauth/authorize?response_type=token&client_id=messaging-client&scope=message.read message.write&grant_type=implicit&redirect_uri=http://webserver:7080/authorized
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s&%s&%s", httpPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamResponseType("token")
                    , cre.getParamScope(), cre.getParamClientId()
                    , cre.getParamRedirectUri(redirect));
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s&%s&%s", httpPath, contextPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamResponseType("token")
                    , cre.getParamScope(), cre.getParamClientId()
                    , cre.getParamRedirectUri(redirect));
        }
        myUrl = new MyUrl();
        myUrl.setMethod("GET");
        myUrl.setUri(uri);
    }

    private void specUrl() {
        //http://authserver:7090/oauth/authorize?response_type=token&client_id=messaging-client&scope=message.read message.write&grant_type=implicit&redirect_uri=http://webserver:7080/authorized
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s&%s&%s", httpPath, specoauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamResponseType("token")
                    , cre.getParamScope(), cre.getParamClientId()
                    , cre.getParamRedirectUri(redirect));
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s&%s&%s", httpPath, contextPath, specoauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamResponseType("token")
                    , cre.getParamScope(), cre.getParamClientId()
                    , cre.getParamRedirectUri(redirect));
        }
        specUrl = new MyUrl();
        specUrl.setMethod("POST/GET");
        specUrl.setUri(uri);
    }

    public MyUrl getMyUrl() {
        return myUrl;
    }

    public MyUrl getSpecUrl() {
        return specUrl;
    }
}