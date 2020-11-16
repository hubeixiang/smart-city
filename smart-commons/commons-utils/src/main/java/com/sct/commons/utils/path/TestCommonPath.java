package com.sct.commons.utils.path;

import java.io.File;

public class TestCommonPath {
    public static void main(String[] args) {
        String httppath = "http://192.168.1.21:17014/login";
        tesst("http://", httppath);
        String linuxpath = "/192.168.1.21:17014/login";
        tesst("", linuxpath);
        String filepath = "file://192.168.1.21:17014/login";
        tesst("file://", filepath);
        String winpath = "D:\\03Downloads\\Browser";
        File file = new File(winpath);
        System.out.println(file.getAbsolutePath());
        tesst(CommonPathConstants.PATH_TYPE_WINDOWS_2, winpath, CommonPathConstants.windowns_regex_split);

    }

    public static void tesst(String type, String path) {
        tesst(type, path, null);
    }

    public static void tesst(String type, String path, String split) {
        CommonPath iPath = CommonPath.parse(type, path, split);
        System.out.println(String.format("head=[%s],path=[%s],parent=[%s],nodeName=[%s]", type, iPath.getPath(), iPath.getParentPath(), iPath.getNodeName()));
    }
}
