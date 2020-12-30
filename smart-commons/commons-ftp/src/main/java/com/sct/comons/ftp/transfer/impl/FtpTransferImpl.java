package com.sct.comons.ftp.transfer.impl;


import com.sct.comons.ftp.file.FileWrapper;
import com.sct.comons.ftp.properties.FtpConfiguration;
import com.sct.comons.ftp.transfer.AbstractFtpTransfer;
import com.sct.comons.ftp.transfer.IFtpTransfer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * FTP传输实现
 */
public class FtpTransferImpl extends AbstractFtpTransfer {
    private static Logger log = LoggerFactory.getLogger(FtpTransferImpl.class);
    private FTPClient ftpClient;

    public FtpTransferImpl(FtpConfiguration ftpConfig) {
        this.ftpConfig = ftpConfig;
        log.info("Ftp {}", (ftpConfig.isUse() ? "enable" : "disable"));
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("ftp.protocol", "ftp");
        props.put("host", "192.168.1.21");
        props.put("port", "21");
        props.put("username", "root");
        props.put("password", "Hthy1607");
        props.put("remoteDirectory", "/opt/data/ftp/");
        props.put("isMonitor", "true");
        props.put("bufferSize", "1024");
        // IFtpTransfer transfer = FtpTransferFactory.getInstance(props, null);
        IFtpTransfer transfer = null;
        File file = new File("D:\\input\\kafka7.log");
        List<File> files = new ArrayList<File>();
        files.add(file);
        files.add(file);
        files.add(file);

        try {
            transfer.connect();
            long beginTime = System.currentTimeMillis();
            try {
                transfer.put(files);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("===cost=" + (endTime - beginTime) / 1000);
        } finally {
            transfer.disconnect();
        }
    }

    @Override
    public boolean connect() {
        boolean isConnect = false;
        int times = 0;
        ftpClient = new FTPClient();
        while (times < ftpConfig.getConnectTimes()) {
            times++;
            try {
                ftpClient.connect(ftpConfig.getHost(), ftpConfig.getPort());
                int reply = ftpClient.getReplyCode();
                if (FTPReply.isPositiveCompletion(reply)) {
                    if (ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword())) {
                        if (ftpConfig.getConnectMode() == FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE) {
                            ftpClient.enterLocalPassiveMode();
                            log.info("enter local passive mode");
                        }
                        ftpClient.setDataTimeout(1800);
                        ftpClient.setBufferSize(ftpConfig.getBufferSize());
                        ftpClient.setFileType(ftpConfig.getFileType());
                        isConnect = true;
                        log.info("connect to ftp {}:{} success({} times)", ftpConfig.getHost(), ftpConfig.getPort(), times);
                    } else {
                        log.info("connect to ftp {}:{} failure({} times)", ftpConfig.getHost(), ftpConfig.getPort(), times);
                    }
                } else {
                    ftpClient.disconnect();
                    log.info("connect to ftp {}:{} refuse", ftpConfig.getHost(), ftpConfig.getPort());
                }
            } catch (IOException e) {
                isConnect = false;
                log.info("connect to ftp {}:{} error({} times):{}", ftpConfig.getHost(), ftpConfig.getPort(), times, ExceptionUtils.getStackTrace(e));
                if (ftpClient != null && ftpClient.isConnected()) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException ie) {
                        e.printStackTrace();
                    }
                }
            }
            boolean isConnected = ftpClient.isConnected();
            if (isConnected) {
                isConnect = true;
                break;
            }
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
            }
        }

        return isConnect;
    }

    @Override
    public boolean disconnect() {
        boolean isDisconnect = false;
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                log.info("ftp client logout");
            } catch (IOException e) {
                log.info("ftp client logout error:{}", ExceptionUtils.getStackTrace(e));
            }
            try {
                ftpClient.disconnect();
                ftpClient = null;
                log.info("ftp client disconnected");
            } catch (IOException e) {
                log.error("ftp client disconnect error:{}", ExceptionUtils.getStackTrace(e));
            }
            isDisconnect = true;
        }
        return isDisconnect;
    }

    private String getDirectory(String remoteDirectory, String subDirectory) {
        String directory = remoteDirectory;
        if (!remoteDirectory.endsWith("\\") && !remoteDirectory.endsWith("/")) {    // Windows/Linux
            directory += "/";
        }
        if (subDirectory != null && subDirectory.trim().length() > 0) {
            directory += subDirectory + "/";
        }

        return directory;
    }

    @Override
    public boolean put(File file) throws Exception {
        if (ftpClient == null || !ftpClient.isConnected()) {
            throw new RuntimeException("Not connect to ftp[" + ftpConfig.getHost() + ":" + ftpConfig.getPort() + "]");
        }
        boolean isPut = false;
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            String fullRemoteDirectory = getDirectory(ftpConfig.getRemoteDirectory(), ftpConfig.getRemoteSubDirectory());
            String remoteFile = fullRemoteDirectory + file.getName();
            String remoteTmpFile = remoteFile + ".tmp";
            if (monitor != null) {
                monitor.setFileName(file.getName());
                monitor.setFileSize(file.length());
                ftpClient.setCopyStreamListener(monitor);
            }
            mkdir(fullRemoteDirectory);
            boolean isStoreFile = ftpClient.storeFile(remoteTmpFile, input);
            if (!isStoreFile) {
                log.info("put file[{}] failed", remoteFile);
                return false;
            }

            boolean isRename = this.rename(remoteTmpFile, remoteFile);
            if (isRename) {
                isPut = true;
            }
            return isPut;
        } catch (IOException e) {
            log.error("put file[{}] error:", file.getName(), e);
            throw e;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public boolean put(InputStream fileStream, String fileName) {
        return false;
    }

    /**
     * 创建目录
     *
     * @param remoteDirectory
     * @return
     * @throws IOException
     */
    @Override
    public boolean mkdir(String remoteDirectory) throws Exception {
        if (!remoteDirectory.endsWith("/")) {
            remoteDirectory += "/";
        }
        int begin = 0;
        int end = remoteDirectory.indexOf("/", begin);
        boolean mkdir = false;
        while (true) {
            String subDirectory = remoteDirectory.substring(0, end);
            if (ftpClient.makeDirectory(subDirectory)) {
                mkdir = true;
            }
            begin = end + 1;
            end = remoteDirectory.indexOf("/", begin);
            if (end <= begin) {
                break;
            }
        }
        log.info("mkdir [{}] {}", remoteDirectory, mkdir);
        return mkdir;
    }

    @Override
    public boolean put(List<File> files) throws Exception {
        boolean isPut = true;
        for (File file : files) {
            if (!put(file)) {
                isPut = false;
            }
        }
        return isPut;
    }

    public List<FileWrapper> list() throws Exception {
        try {
            List<FileWrapper> fileList = new ArrayList<>();
            String remoteDirectory = ftpConfig.getRemoteDirectory();
            if (!remoteDirectory.endsWith("/")) {
                ftpConfig.setRemoteDirectory(remoteDirectory + "/");
            }
            listFile(ftpClient, ftpConfig.getRemoteDirectory(), fileList);
            log.info("list file[{}] size:{}", remoteDirectory, fileList.size());
            return fileList;
        } catch (Exception e) {
            throw e;
        }
    }

    private void listFile(FTPClient ftpClient, String remoteDirectory, List<FileWrapper> fileList) throws Exception {
        try {
            boolean change = ftpClient.changeWorkingDirectory(remoteDirectory);
            log.info("change directory[{}] {}", remoteDirectory, change);
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                if (file.isDirectory()) {
                    String directory = remoteDirectory + file.getName() + "/";
                    listFile(ftpClient, directory, fileList);
                } else {
                    long lastModified = file.getTimestamp().getTimeInMillis() + file.getTimestamp().getTimeZone().getOffset(0);
                    String pathname = remoteDirectory + file.getName();
                    FileWrapper ftpFile = new FileWrapper(pathname, lastModified);
                    fileList.add(ftpFile);
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(String file) throws Exception {
        try {
            boolean isDelete = ftpClient.deleteFile(file);
            log.info("delete file[{}] {}", file, isDelete);
            return isDelete;
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean rename(String from, String to) throws IOException {
        boolean isRename = false;
        isRename = ftpClient.rename(from, to);
        if (log.isDebugEnabled()) {
            log.debug("rename from {} to {} {}", from, to, (isRename ? "success" : "failure"));
        }
        return isRename;
    }

    public boolean get(String remoteFileName, File localFile) throws IOException {
        FileOutputStream localFileStream = null;
        try {
            localFileStream = new FileOutputStream(localFile);
            boolean bool = ftpClient.retrieveFile(remoteFileName, localFileStream);
            log.info("get remote file[{}] {}", remoteFileName, bool);
            return bool;
        } catch (Exception e) {
            throw e;
        } finally {
            if (localFileStream != null) {
                localFileStream.close();
            }
        }
    }

}
