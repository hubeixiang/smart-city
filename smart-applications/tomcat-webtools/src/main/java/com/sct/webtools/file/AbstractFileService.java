package com.sct.webtools.file;

import com.sct.commons.excel.wrapper.FileContextWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AbstractFileService {
    private static Logger logger = LoggerFactory.getLogger(AbstractFileService.class);

    public List<FileContextWrapper> parserMultipartFiles(List<MultipartFile> files) {
        try {
            List<FileContextWrapper> contextWrappers = new ArrayList<>();
            for (MultipartFile multipartFile : files) {
                FileContextWrapper contextWrapper = new FileContextWrapper();
                contextWrapper.setName(multipartFile.getOriginalFilename());
                contextWrapper.setInput(multipartFile.getInputStream());
            }
            return contextWrappers;
        } catch (Exception e) {
            logger.error(String.format("parser upload multipart files exception:%s", e.getMessage()), e);
            throw new RuntimeException(String.format("parser upload multipart files exception:%s", e.getMessage()), e);
        }
    }

    /**
     * *初始化目录 <br>
     * 如果不存在指定路径，则创建
     *
     * @param directory
     */
    public void initDirectory(String directory) {
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
            logger.info("Directory [{}] not exists,created", directory);
        }
    }

    /**
     * 初始化目录
     * <br>基本路径+相对路径
     *
     * @param basePathP
     * @param path
     * @return
     */
    public String initFilePath(String basePathP, String path) {
        if (!path.startsWith(File.separator)) {
            path = File.separator + path;
        }
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        return basePathP + path;
    }

    /**
     * 初始化文件名称
     * <br>基本路径+相对文件路径名称
     *
     * @param basePathP
     * @param name
     * @return
     */
    public String initFileName(String basePathP, String name) {
        if (!name.startsWith(File.separator)) {
            name = File.separator + name;
        }

        return basePathP + name;
    }
}
