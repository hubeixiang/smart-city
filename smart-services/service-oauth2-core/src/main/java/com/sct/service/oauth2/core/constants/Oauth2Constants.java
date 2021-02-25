package com.sct.service.oauth2.core.constants;

public final class Oauth2Constants {
    /**
     * oauth2.0 协议认证相关的路径
     */
    public final static String Oauth2_Context_Path = "oauth2.0";

    /**
     * 登录认证后使用session进行后续认证的路径
     */
    public final static String Session_Context_Path = "session";
    /**
     * oauth2.0 resourceserver侧访问的url
     */
    public final static String Oauth2_ResourceServer_Context_Path = "resourceserver";
    /**
     * oauth2.0 resourceserver侧访问的url对应的不走oauth2.0访问的路
     */
    public final static String Oauth2_ResourceServer_Context_Path_None_Auth = "service";
}
