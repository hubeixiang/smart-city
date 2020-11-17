package com.sct.commons.file.location;

import java.io.File;

/**
 * 提供的文件定位管理工具
 */
public class FileLocationManager {
    private static FileLocationManager instance;

    private FileLocationManager() {
    }

    public static FileLocationManager getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance != null) {
            return;
        }
        FileExtensions.getInstance();
        instance = new FileLocationManager();
    }

    /**
     * 依据传入的文件名称,鉴别其文件扩展信息
     *
     * @param fileName 传入的文件名,不包含文件所在的路径
     * @return 返回该文件名是否在, 为null时, 表明未识别出文件后缀
     */
    public FileNameDescribe parserFileNameDescribe(String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        String extension = fileName.substring(index);
        String name = fileName.substring(0, index);
        FileNameDescribe fileNameDescribe = new FileNameDescribe();
        fileNameDescribe.setFileName(name);
        FileExtension fileExtension = FileExtensions.getInstance().parserFileExtension(extension, "");
        fileNameDescribe.setFileExtension(fileExtension);
        return fileNameDescribe;
    }

    /**
     * 生成文件名
     *
     * @param fileNameDescribe
     * @return null:表明未识别出文件名或者文件后缀
     */
    public String getFileName(FileNameDescribe fileNameDescribe) {
        if (!StringUtils.isObjectEmpty(fileNameDescribe) && !StringUtils.isEmpty(fileNameDescribe.getFileName()) && !StringUtils.isObjectEmpty(fileNameDescribe.getFileExtension()) && !StringUtils.isEmpty(fileNameDescribe.getFileExtension().getExtension())) {
            return String.format("%s%s", fileNameDescribe.getFileName(), fileNameDescribe.getFileExtension().getExtension());
        }
        return null;
    }

    /**
     * @param fileLocation
     * @return null:表明未识别出文件定位id或者文件后缀
     */
    public String getFileName(FileLocation fileLocation) {
        if (fileLocation != null) {
            if (!StringUtils.isObjectEmpty(fileLocation.getFileExtension()) && !StringUtils.isEmpty(fileLocation.getFileExtension().getExtension())) {
                if (fileLocation.getFileUuid() != null && fileLocation.getFileUuid().length() != 0) {
                    return String.format("%s%s", fileLocation.getFileUuid(), fileLocation.getFileExtension().getExtension());
                }
            }
        }
        return null;
    }

    public File fileUrl2File(FileLocation fileLocation) {
        String fileUrl = fileUrl(fileLocation);
        if (StringUtils.isEmpty(fileUrl)) {
            return null;
        } else {
            return new File(fileUrl);
        }
    }

    public String fileUrl(FileLocation fileLocation) {
        String fileName = getFileName(fileLocation);
        if (StringUtils.isNotEmpty(fileName)) {
            return fileLocation.getServiceUrl() + File.separator + fileName;
        }
        return null;
    }

    public FileLocation parserFileLocalion(File file) {
        if (file.isDirectory()) {
            return null;
        }
        FileLocation fileLocation = new FileLocation();
        String fileName = file.getName();
        if (fileName == null || fileName.length() == 0) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        String extension = fileName.substring(index);
        String name = fileName.substring(0, index);
        String dir = file.getParent();
        FileExtension fileExtension = FileExtensions.getInstance().parserFileExtension(extension, "");
        fileLocation.setFileExtension(fileExtension);
        fileLocation.setFileUuid(name);
        fileLocation.setServiceUrl(dir);
        return fileLocation;
    }
}
