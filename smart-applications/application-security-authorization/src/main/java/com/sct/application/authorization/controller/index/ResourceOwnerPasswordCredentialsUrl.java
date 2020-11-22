package com.sct.application.authorization.controller.index;

import com.sct.application.authorization.properties.OauthClient;
import org.springframework.util.StringUtils;

public class ResourceOwnerPasswordCredentialsUrl {
    private String httpPath;
    private String contextPath;
    private String redirect;
    private String user;
    private String oauthUrl = "oauth/token";
    private String grantType = "password";
    private CredentialsEntity cre = new CredentialsEntity();
    private MyUrl myUrl;

    public ResourceOwnerPasswordCredentialsUrl(OauthClient oauthClient, String httpPath, String contextPath, String redirect, String user) {
        cre.setClientId(oauthClient.getClientId());
        cre.setClientSecret(oauthClient.getClientSecret());
        cre.setScope(oauthClient.getClientScope());
        this.httpPath = httpPath;
        this.contextPath = contextPath;
        this.redirect = redirect;
        this.user = user;
        stmyUrl();
    }

    private void stmyUrl() {
        //http://127.0.0.1:7090/oauth/token?&grant_type=password&scope=message.read message.write&username=user1&password=user1&client_id=message-client&client_secret=secret&redirect_uri=http://localhost:7081/authorized
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s&%s&%s", httpPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamScope(), cre.getParamClient()
                    , cre.getParamVar("username", user), cre.getParamVar("password", user)
            );
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s&%s&%s", httpPath, contextPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamScope(), cre.getParamClient()
                    , cre.getParamVar("username", user), cre.getParamVar("password", user)
            );
        }
        myUrl = new MyUrl();
        myUrl.setMethod("POST/GET");
        myUrl.setUri(uri);
    }

    public MyUrl getMyUrl() {
        return myUrl;
    }
}
