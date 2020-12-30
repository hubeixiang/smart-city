package com.sct.webtools.file;

import com.sct.commons.excel.wrapper.FileContextWrapper;
import com.sct.commons.file.fileutil.SimpleFileUtil;
import com.sct.comons.ftp.properties.FtpConfiguration;
import com.sct.comons.ftp.service.AgentFtpTransferFactory;
import com.sct.comons.ftp.transfer.IFtpTransfer;
import com.sct.webtools.model.SaveType;
import com.sct.webtools.model.UploadFilesParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 静态web服务文件的删除
 */
@Service
public class StaticFileServiceImpl extends AbstractFileService {
    private static Logger logger = LoggerFactory.getLogger(StaticFileServiceImpl.class);
    @Autowired
    protected FtpConfiguration ftpConfiguration;
    @Autowired(required = false)
    private AgentFtpTransferFactory agentFtpTransferFactory;

    public void delete(String basePath, List<String> files) {
        if (StringUtils.isEmpty(basePath)) {
            throw new RuntimeException("File base path can NOT NULL");
        }
        for (String path : files) {
            String fileName = initFileName(basePath, path);
            File file = new File(fileName);
            boolean delete = file.delete();
            logger.info("delete [{}] {}", fileName, delete);
        }
    }

    public void upload(String basePath, UploadFilesParam params) {
        if (StringUtils.isEmpty(basePath)) {
            throw new RuntimeException("File base path can NOT NULL");
        }
        String staticSavePath = initFilePath(basePath, params.getSavePath());
        initDirectory(staticSavePath);
        String ftpRemoteSubDirectory = params.getSavePath();

        List<FileContextWrapper> contextWrappers = parserMultipartFiles(params.getFiles());
        SaveType saveType = params.getSaveType();
        switch (saveType) {
            case tomcat:
                saveStatic(staticSavePath, contextWrappers);
                break;
            case ftp:

                saveFtp(ftpRemoteSubDirectory, contextWrappers);
                break;
            case tomcat_ftp:
                saveStaticAndFtp(staticSavePath, ftpRemoteSubDirectory, contextWrappers);
                break;
            default:
                throw new RuntimeException("error save type " + saveType);
        }

    }


    private void saveStatic(String staticSavePath, List<FileContextWrapper> contextWrappers) {
        initDirectory(staticSavePath);
        for (FileContextWrapper contextWrapper : contextWrappers) {
            String fullFileName = initFileName(staticSavePath, contextWrapper.getName());
            logger.info("saveStatic [{}]", fullFileName);
            boolean ok = SimpleFileUtil.write(fullFileName, contextWrapper.getInput(), false);
            if (!ok) {
                throw new RuntimeException(String.format("save file %s failure", fullFileName));
            }
        }
    }

    private void saveFtp(String ftpRemoteSubDirectory, List<FileContextWrapper> contextWrappers) {
        if (agentFtpTransferFactory == null) {
            throw new RuntimeException("agent ftp transfer must instantiation");
        }
        FtpConfiguration ftpConfig = ftpConfiguration.clone();
        ftpConfig.setRemoteSubDirectory(ftpRemoteSubDirectory);
        IFtpTransfer iFtpTransfer = agentFtpTransferFactory.createIFtpTransfer(ftpConfig);
        try {
            iFtpTransfer.connect();
            for (FileContextWrapper contextWrapper : contextWrappers) {
                boolean put = iFtpTransfer.put(contextWrapper.getInput(), contextWrapper.getName());
                logger.info("upload to ftp[{}] {}", contextWrapper.getName(), put);
            }
        } catch (Exception e) {

        } finally {
            if (iFtpTransfer != null) {
                iFtpTransfer.disconnect();
            }
        }
    }

    private void saveStaticAndFtp(String staticSavePath, String ftpRemoteSubDirectory, List<FileContextWrapper> contextWrappers) {
        saveStatic(staticSavePath, contextWrappers);
        saveFtp(ftpRemoteSubDirectory, contextWrappers);
    }
}
