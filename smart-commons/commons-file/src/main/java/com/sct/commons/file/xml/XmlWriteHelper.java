package com.sct.commons.file.xml;

import com.sct.commons.file.FileConstants;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * 使用dom4j三方包的,xml写工具
 */
public class XmlWriteHelper {
    private static final Logger logger = LoggerFactory.getLogger(XmlWriteHelper.class);

    /**
     * 创建写入xml文件的Document对象
     *
     * @param rootElementName 创建的Document的root节点名称,可以为空,如果为空,不设置根节点名称,需要外部设置
     * @return
     */
    public static Document createDocument(String rootElementName) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            document.setXmlVersion("1.0");
            if (StringUtils.isNotEmpty(rootElementName)) {
                document.createElement(rootElementName);
            }
            return document;
        } catch (ParserConfigurationException e) {
            if (StringUtils.isNotEmpty(rootElementName)) {
                logger.error(String.format("createDocument rootElementName=%s ParserConfigurationException:%s", rootElementName, e.getMessage()), e);
            } else {
                logger.error(String.format("createDocument ParserConfigurationException:%s", e.getMessage()), e);
            }
        }
        return null;
    }

    /**
     * 将对象写入指定的xml文件中,内容之间自动分行
     *
     * @param document
     * @param absolutePathFile
     */
    public static void transform2XmlFile(Document document, String absolutePathFile) {
        try {
            if (!absolutePathFile.endsWith(FileConstants.FILE_SUFFIX_XML)) {
                absolutePathFile = absolutePathFile + FileConstants.FILE_SUFFIX_XML;
            }
            // 开始把Document映射到文件
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            transFormer.setOutputProperty(OutputKeys.INDENT, "yes");
            transFormer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, "yes");
            transFormer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            // 设置输出结果
            DOMSource domSource = new DOMSource(document);
            // 生成xml文件
            File file = new File(absolutePathFile);
            // 判断是否存在,如果不存在,则创建
            if (!file.exists()) {
                file.createNewFile();
            }
            // 文件输出流
            FileOutputStream out = new FileOutputStream(file);
            // 设置输入源
            StreamResult xmlResult = new StreamResult(out);

            // 输出xml文件
            transFormer.transform(domSource, xmlResult);
        } catch (Exception e) {
            logger.error(String.format("writeDocument to file=[%s] Exception:%s", absolutePathFile, e.getMessage()), e);
        }
    }

    /**
     * 将对象写入指定的xml文件中,但是内容不分行
     *
     * @param document
     * @param absolutePathFile
     */
    public static void writeDocument2Xml(Document document, String absolutePathFile) {
        try {
            if (!absolutePathFile.endsWith(FileConstants.FILE_SUFFIX_XML)) {
                absolutePathFile = absolutePathFile + FileConstants.FILE_SUFFIX_XML;
            }
            //设置字符编码方式
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(FileConstants.CHARACTER_SET_UTF8);

            // 生成xml文件
            File file = new File(absolutePathFile);
            // 判断是否存在,如果不存在,则创建
            if (!file.exists()) {
                file.createNewFile();
            }
            file = null;
            //开始写
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(absolutePathFile), format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            logger.error(String.format("writeDocument to file=[%s] Exception:%s", absolutePathFile, e.getMessage()), e);
        }
    }
}
