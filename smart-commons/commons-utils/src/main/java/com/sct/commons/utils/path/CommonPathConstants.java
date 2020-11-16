package com.sct.commons.utils.path;

/**
 * 只能配合CommonPath中的相关属性使用
 */
public class CommonPathConstants {
    //默认的路径拼接分割符
    public final static String default_split = "/";
    //各种分隔符的正则表达式,方便匹配分割
    public final static String default_regex_split = "/";
    public final static String windowns_regex_split = "\\\\";
    //各种路径的头类型举例,不用做正则表达式模式,分析时会主动截取掉路径开头的这些头类型
    public final static String PATH_TYPE_HTTP = "http://";
    public final static String PATH_TYPE_HTTPS = "https://";
    public final static String PATH_TYPE_FILE = "file://";
    public final static String PATH_TYPE_WINDOWS_1 = "D:/";
    public final static String PATH_TYPE_WINDOWS_2 = "D:\\";
}
