package com.sct.application.authorization.controller.index;

import com.sct.application.authorization.properties.OauthClient;
import org.springframework.util.StringUtils;

public class CheckTokenUri {
    private String httpPath;
    private String contextPath;
    private String oauthUrl = "oauth/check_token";
    private CredentialsEntity cre = new CredentialsEntity();
    private MyUrl myUrl;

    public CheckTokenUri(OauthClient oauthClient, String httpPath, String contextPath) {
        cre.setClientId(oauthClient.getClientId());
        cre.setClientSecret(oauthClient.getClientSecret());
        cre.setScope(oauthClient.getClientScope());
        this.httpPath = httpPath;
        this.contextPath = contextPath;
        stmyUrl();
    }

    private void stmyUrl() {
        //http://127.0.0.1:7090/oauth/check_token?token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IitkeStjb3RFeXIwa3BuNW5XZDFhUk5qQUZJZmFQUDZKb0NpUWVjK2F5Qjg9In0.eyJleHAiOjE1OTQ5Mzc1ODMsInVzZXJfbmFtZSI6ImFkbWluIiwianRpIjoiNWEzODgxMWUtODYyZS00NWI4LWFhNDItMTViOGY2NzJmYTZlIiwiY2xpZW50X2lkIjoiYnNzLWVtYmVkLXdoaXRlIiwic2NvcGUiOlsibWVzc2FnZS5yZWFkIiwibWVzc2FnZS53cml0ZSJdfQ.CB_zcPwI7GuQTaowf6HW5HOSx-pXHHa3AmAJ_m31URwubfhhzymG7sYB8H9h69Jm6u98lZQ7lci6RKuOgOULMmx3oI1KUwluIv7MeJabUumbJfYfn5ZNBEXrMsKnN_tUIcjUFtWlNz60_n_Oc6K4TSelJoLNc0oeMCZVi75PqSoVLU3qzl_WlbL0C6gLsZ2OqRrwJAxgVKXbn2p69Fa8woHjAuizvMoZjzyRS5-WskCUMj_GTRkAvjZ3jp_iuEDaIFb_lS0yBUAmQEGtOPPOyQ4gDcRen4JIgW4c8iYR8784EnJTO4P4oE7IPT0HH0ygNApXaBl0TOyEp-B4vWkQAw
        String uri = null;
        if (StringUtils.isEmpty(contextPath)) {
            uri = String.format("%s/%s?%s", httpPath, oauthUrl
                    , cre.getParamVar("token", "要检查的Token"));
        } else {
            uri = String.format("%s/%s/%s?%s", httpPath, contextPath, oauthUrl
                    , cre.getParamVar("token", "要检查的Token"));
        }
        myUrl = new MyUrl();
        myUrl.setMethod("GET");
        myUrl.setUri(uri);
    }

    public MyUrl getMyUrl() {
        return myUrl;
    }
}
