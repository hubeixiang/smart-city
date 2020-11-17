package com.sct.commons.file.fileutil;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Zip文件工具类
 */
public class ZipFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(ZipFileUtil.class);

    /**
     * 判断文件名是否以.zip为后缀
     *
     * @param fileName 需要判断的文件名
     * @return 是zip文件返回true, 否则返回false
     */
    public static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 把文件压缩成zip格式
     *
     * @param files       需要压缩的文件列表
     * @param zipFilePath 压缩后的zip文件路径 ,如"D:/test/aa.zip";
     */
    public static File compressFiles2Zip(List<File> files, String zipFilePath) throws IOException {
        File zipFile = null;

        if (files != null && files.size() > 0) {

            if (isEndsWithZip(zipFilePath)) {

                ZipArchiveOutputStream zaos = null;
                try {
                    zipFile = new File(zipFilePath);

                    logger.info("Start zip files to: {}", zipFile.getAbsolutePath());

                    zaos = new ZipArchiveOutputStream(zipFile);
                    // Use Zip64 extensions for all entries where they are
                    // required
                    zaos.setUseZip64(Zip64Mode.AsNeeded);
                    zaos.setEncoding("GBK"); // TODO: default encoding is UTF8
                    // 将每个文件用ZipArchiveEntry封装
                    // 再用ZipArchiveOutputStream写到压缩文件中

                    for (File file : files) {

                        if (file != null) {
                            logger.info("Start add file to zip: {}", file.getAbsolutePath());

                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                            zaos.putArchiveEntry(zipArchiveEntry);

                            InputStream is = null;
                            try {
                                is = new FileInputStream(file);
                                byte[] buffer = new byte[1024 * 5];
                                int len = -1;
                                while ((len = is.read(buffer)) != -1) {
                                    // 把缓冲区的字节写入到ZipArchiveEntry
                                    zaos.write(buffer, 0, len);
                                }
                                // Writes all necessary data for this entry.
                                zaos.closeArchiveEntry();

                            } finally {
                                if (is != null) {
                                    try {
                                        is.close();
                                    } catch (IOException ignore) {
                                    }
                                }
                            }
                        }
                    }

                    zaos.finish();

                } catch (Exception e) {
                    if (zipFile != null) {
                        logger.error(String.format("Fail zip files to: %s", zipFile.getAbsolutePath()));

                        if (zipFile.exists()) {
                            try {
                                //noinspection ResultOfMethodCallIgnored
                                zipFile.delete();
                            } catch (SecurityException ignore) {
                            }
                        }
                    }
                    throw (e instanceof IOException ? (IOException) e : new IOException(e));
                } finally {
                    if (zaos != null) {
                        try {
                            zaos.close();
                        } catch (IOException ignore) {
                        }
                    }
                }
            }
        }

        if (zipFile != null) {
            logger.info("Finish zip files to: {}", zipFile.getAbsolutePath());
        } else {
            logger.warn("No files to zip to: {}", zipFilePath);
        }

        return zipFile;
    }

    /**
     * 把zip文件解压到指定的文件夹
     *
     * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip"
     * @param saveFileDir 解压后的文件存放路径, 如"D:/test/"
     */
    public static void decompressZip(String zipFilePath, String saveFileDir) {
        if (isEndsWithZip(zipFilePath)) {
            File file = new File(zipFilePath);
            if (file.exists()) {
                InputStream is = null;
                // can read Zip archives
                ZipArchiveInputStream zais = null;
                try {
                    is = new FileInputStream(file);
                    zais = new ZipArchiveInputStream(is);
                    ArchiveEntry archiveEntry = null;
                    // 把zip包中的每个文件读取出来
                    // 然后把文件写到指定的文件夹
                    while ((archiveEntry = zais.getNextEntry()) != null) {
                        // 获取文件名
                        String entryFileName = archiveEntry.getName();
                        // 构造解压出来的文件存放路径
                        String entryFilePath = saveFileDir + File.separator + entryFileName;
                        byte[] content = new byte[(int) archiveEntry.getSize()];
                        zais.read(content);
                        OutputStream os = null;
                        try {
                            // 把解压出来的文件写到指定路径
                            File entryFile = new File(entryFilePath);
                            os = new FileOutputStream(entryFile);
                            os.write(content);
                        } catch (IOException e) {
                            throw new IOException(e);
                        } finally {
                            if (os != null) {
                                os.flush();
                                os.close();
                            }
                        }

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (zais != null) {
                            zais.close();
                        }
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}
