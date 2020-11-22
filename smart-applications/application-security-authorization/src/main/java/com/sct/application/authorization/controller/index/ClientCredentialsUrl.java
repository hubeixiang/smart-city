package com.sct.application.authorization.controller.index;

import com.sct.application.authorization.properties.OauthClient;
import org.springframework.util.StringUtils;

public class ClientCredentialsUrl {
    private String httpPath;
    private String contextPath;
    private String redirect;
    private String oauthUrl = "oauth/token";
    private String grantType = "client_credentials";
    private CredentialsEntity cre = new CredentialsEntity();
    private MyUrl myUrl;

    public ClientCredentialsUrl(OauthClient oauthClient, String httpPath, String contextPath, String redirect) {
        cre.setClientId(oauthClient.getClientId());
        cre.setClientSecret(oauthClient.getClientSecret());
        cre.setScope(oauthClient.getClientScope());
        this.httpPath = httpPath;
        this.contextPath = contextPath;
        this.redirect = redirect;
        stmyUrl();
    }

    private void stmyUrl() {
        //http://127.0.0.1:7090/oauth/token?&grant_type=client_credentials&scope=message.read message.write&redirect_uri=http://localhost:7081/authorized&client_id=message-client&client_secret=secret
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s", httpPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamScope(), cre.getParamClient());
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s", httpPath, contextPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamScope(), cre.getParamClient());
        }
        myUrl = new MyUrl();
        myUrl.setMethod("POST/GET");
        myUrl.setUri(uri);
    }

    public MyUrl getMyUrl() {
        return myUrl;
    }
}
