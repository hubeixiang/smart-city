package com.sct.commons.utils.path;

import org.apache.commons.lang3.StringUtils;

/**
 * 普通路径分析&格式化对象
 */
public class CommonPath {
    private final String type;
    private final CommonPath parent;
    private final String nodeName;

    public CommonPath(String type, CommonPath parent, String nodeName) {
        this.type = type;
        this.parent = parent;
        this.nodeName = nodeName;
    }

    /**
     * 使用默认的分隔符正则表达式进行分割
     *
     * @param type    指定路径的类型
     * @param pathStr 要分析的完整路径字符串
     * @return 解析完毕后的Path对象
     * @see CommonPathConstants  路径类型 type
     */
    public static CommonPath parse(String type, String pathStr) {
        return parse(type, pathStr, CommonPathConstants.default_regex_split);
    }

    /**
     * 使用指定的分隔符正则表达式进行分割
     *
     * @param type       指定路径的类型
     * @param pathStr    要分析的完整路径字符串
     * @param regexSplit 当指定的分割符正则表达式为空时,使用默认的分隔符正则表达式
     * @return 解析完毕后的Path对象
     * @see CommonPathConstants  路径类型 type
     */
    public static CommonPath parse(String type, String pathStr, String regexSplit) {
        if (StringUtils.isEmpty(pathStr)) {
            return null;
        }
        if (StringUtils.isEmpty(regexSplit)) {
            regexSplit = CommonPathConstants.default_regex_split;
        }
        String newPathStr = null;
        if (StringUtils.isEmpty(type)) {
            newPathStr = pathStr;
        } else {
            if (pathStr.startsWith(type)) {
                newPathStr = pathStr.substring(type.length());
            } else {
                throw new RuntimeException(String.format("error path=[%s] starts with type=[%s]", pathStr, type));
            }
        }
        String[] pathArray = newPathStr.split(regexSplit);

        CommonPath parent_Path = null;
        CommonPath return_Path = null;
        int size = pathArray.length;
        for (int i = 0; i < size; i++) {
            String path = pathArray[i];
            if (StringUtils.isEmpty(path)) {
                continue;
            }
            return_Path = new CommonPath(type, parent_Path, path);
            parent_Path = return_Path;

        }
        return return_Path;
    }

    /**
     * 获取路径类型,完整的路径需要拼接上路径类型
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 获取父级路径
     *
     * @return
     */
    public CommonPath getParent() {
        return parent;
    }

    /**
     * 获取父级路径字符串
     *
     * @return
     */
    public String getParentPath() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.getPath();
    }

    /**
     * 获取路径的当前节点值
     *
     * @return
     */
    public String getNodeName() {
        return this.nodeName;
    }

    /**
     * 获取当前路径的完整路径格式化后的字符串
     *
     * @return
     */
    public String getPath() {
        if (parent == null) {
            //最上层目录
            if (StringUtils.isEmpty(type)) {
                //没有指定path的头类型
                return String.format("%s%s", CommonPathConstants.default_split, this.nodeName);
            } else {
                //指定了path的头类型
                return String.format("%s%s", type, this.nodeName);
            }
        } else {
            //下层目录
            return String.format("%s%s%s", parent.getPath(), CommonPathConstants.default_split, this.nodeName);
        }
    }
}
