package com.sct.application.authorization.controller.index;

import com.sct.application.authorization.properties.OauthClient;
import org.springframework.util.StringUtils;

public class RefreshTokenUri {
    private String httpPath;
    private String contextPath;
    private String oauthUrl = "oauth/token";
    private String grantType = "refresh_token";
    private CredentialsEntity cre = new CredentialsEntity();
    private MyUrl myUrl;

    public RefreshTokenUri(OauthClient oauthClient, String httpPath, String contextPath) {
        cre.setClientId(oauthClient.getClientId());
        cre.setClientSecret(oauthClient.getClientSecret());
        cre.setScope(oauthClient.getClientScope());
        this.httpPath = httpPath;
        this.contextPath = contextPath;
        stmyUrl();
    }

    private void stmyUrl() {
        //http://192.168.1.22:7090/oauth/token?grant_type=refresh_token&scope=message.read message.write&client_id=messaging-client&refresh_token=就的token
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s&%s&%s&%s", httpPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamScope(), cre.getParamClient()
                    , cre.getParamVar(grantType, "要刷新的token"));
        } else {
            uri = String.format("%s/%s/%s?%s&%s&%s&%s", httpPath, contextPath, oauthUrl
                    , cre.getParamGrantType(grantType)
                    , cre.getParamScope(), cre.getParamClient()
                    , cre.getParamVar(grantType, "要刷新的token"));
        }
        myUrl = new MyUrl();
        myUrl.setMethod("POST");
        myUrl.setUri(uri);
    }

    public MyUrl getMyUrl() {
        return myUrl;
    }
}
