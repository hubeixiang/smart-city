package com.sct.commons.file.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用dom4j三方包的,xml读工具
 */
public class XmlParserHelper {
    private static final Logger logger = LoggerFactory.getLogger(XmlParserHelper.class);

    //判断本节点是否为一个正确的节点
    public static boolean isElementNode(Node node) {
        if (node != null) {
            try {
                short nodeType = node.getNodeType();
                if (nodeType != Node.ELEMENT_NODE) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 创建读取xml文件的DocumentBuilder对象
     * 外部获取到改对象后可以调用 DocumentBuilder.parse(InputStream) || DocumentBuilder.parse(File) 方法
     * 获取到真正可以操作的xml对象Document
     *
     * @return
     */
    public static DocumentBuilder genXmlDocumentBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("failed to parse ", e);
        }
//        builder.parse()
        return builder;
    }


    public static List<Node> getChildNodes(Node node) {

        List<Node> childNodeList = new ArrayList<Node>();
        NodeList children = node.getChildNodes();
        if (children == null) {
            return childNodeList;
        }
        int childrenSize = children.getLength();
        for (int i = 0; i < childrenSize; i++) {
            Node child = children.item(i);
            short nodeType = child.getNodeType();
            if (nodeType != Node.ELEMENT_NODE) {
                continue;
            }
            childNodeList.add(child);
        }
        return childNodeList;
    }

    public static Element getChildNodeByTagName(Element taskNode, String nodeName) {
        NodeList tableList = taskNode.getElementsByTagName(nodeName);
        if (tableList == null) {
            return null;
        }
        int length = tableList.getLength();
        if (length == 0) {
            return null;
        }
        Element returnNode = (Element) tableList.item(0);
        return returnNode;
    }

}
