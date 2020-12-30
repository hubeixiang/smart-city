package com.sct.webtools.model;

public enum SaveType {
    //只需要简单tomcat单机保存,也是默认的保存方式
    tomcat,
    //只需要简单ftp保存
    ftp,
    //tomcat + ftp保存
    tomcat_ftp
}
