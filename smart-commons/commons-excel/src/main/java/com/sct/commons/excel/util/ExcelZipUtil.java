package com.sct.commons.excel.util;

import com.sct.commons.excel.wrapper.FileContextWrapper;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExcelZipUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelZipUtil.class);

    /**
     * ZIP压缩文件
     *
     * @param excelFileWrappers
     * @return
     * @throws Exception
     */
    public static ByteArrayOutputStream zip(List<? extends FileContextWrapper> excelFileWrappers) throws Exception {
        Map<String/*文件名称*/, Integer> fileMap = new HashMap<>();    // 判断文件是否重复
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutput = null;
        try {
            zipOutput = new ZipOutputStream(outputStream);
            for (FileContextWrapper excelFileWrapper : excelFileWrappers) {
                if (!fileMap.containsKey(excelFileWrapper.getName())) {    // 判断是否已经存储在同名文件
                    fileMap.put(excelFileWrapper.getName(), null);
                    zip(zipOutput, excelFileWrapper.getOutput(), excelFileWrapper.getName());
                }
            }
            return outputStream;
        } catch (Exception e) {
            throw e;
        } finally {
            fileMap.clear();
            if (zipOutput != null) {
                try {
                    zipOutput.flush();
                    zipOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void zip(ZipOutputStream zipOutput, ByteArrayOutputStream byteOutput, String name) throws IOException {
        ZipEntry entry = new ZipEntry(name);
        zipOutput.putNextEntry(entry);
        zipOutput.write(byteOutput.toByteArray());
    }

    /**
     * ZIP解压文件
     *
     * @param input
     * @param encoding
     * @return
     * @throws Exception
     */
    public static List<? extends FileContextWrapper> unzip(InputStream input, String encoding) throws Exception {
        List<FileContextWrapper> fileList = new ArrayList<>();
        try (ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(input, encoding)) {
            ZipArchiveEntry zipEntry = null;
            while ((zipEntry = zipInputStream.getNextZipEntry()) != null) {
                String name = zipEntry.getName().substring(zipEntry.getName().lastIndexOf("/") + 1);    // 文件名称(去掉目录)
                if (!zipEntry.isDirectory()) {
                    FileContextWrapper file = new FileContextWrapper(name, copy(zipInputStream));
                    logger.info("unzip file [{}]", name);
                    fileList.add(file);
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileList;
    }

    private static InputStream copy(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(input, output, 10240);
        InputStream newInput = new ByteArrayInputStream(output.toByteArray());
        return newInput;
    }
}
