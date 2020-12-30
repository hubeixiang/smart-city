package com.sct.comons.ftp.transfer.impl;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sct.comons.ftp.properties.FtpConfiguration;
import com.sct.comons.ftp.transfer.AbstractFtpTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * SFTP传输实现
 */
public class SftpTransferImpl extends AbstractFtpTransfer {
    private static Logger log = LoggerFactory.getLogger(FtpTransferImpl.class);

    private Session session = null;
    private ChannelSftp channel = null;

    public SftpTransferImpl(FtpConfiguration ftpConfig) {
        this.ftpConfig = ftpConfig;
    }

    @Override
    public boolean connect() {
        boolean isConnect = false;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(ftpConfig.getUsername(), ftpConfig.getHost(), ftpConfig.getPort());
            session.setPassword(ftpConfig.getPassword());
            Properties props = new Properties();
            props.put("StrictHostKeyChecking", "no");
            session.setConfig(props);
            session.connect();
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            log.info("connect to sftp {}:{} success", ftpConfig.getHost(), ftpConfig.getPort());
            isConnect = true;
        } catch (JSchException e) {
            log.info("connect to sftp {}:{} error", ftpConfig.getHost(), ftpConfig.getPort());
            e.printStackTrace();
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return isConnect;
    }

    @Override
    public boolean disconnect() {
        if (channel != null) {
            channel.quit();
            channel.disconnect();
            log.info("sftp channel disconnect");
        }
        if (session != null) {
            session.disconnect();
            log.info("sftp session disconnect");
        }
        return true;
    }

    @Override
    public boolean put(File file) throws Exception {
        if (channel == null || channel.isClosed()) {
            throw new RuntimeException("Not connect to sftp");
        }
        boolean isPut = false;
        try {
            String remoteFile = ftpConfig.getRemoteDirectory() + "/" + file.getName();
            mkdir(ftpConfig.getRemoteDirectory());
            if (monitor != null) {
                monitor.setFileName(file.getName());
                monitor.setFileSize(file.length());
            }
            channel.put(file.getAbsolutePath(), remoteFile, monitor);
            // TODO没有改名
            isPut = true;
            log.info("put [{}] true");
        } catch (SftpException e) {
            throw e;
        }
        return isPut;
    }

    @Override
    public boolean put(List<File> files) throws Exception {
        for (File file : files) {
            put(file);
        }
        return true;
    }

    /**
     * @param fileStream
     * @param fileName   远程完成目录文件名称
     * @return
     * @throws Exception
     */
    @Override
    public boolean put(InputStream fileStream, String fileName) throws Exception {
        boolean isPut = true;
        try {

            String remoteDirectory = initRemoteDirectory(ftpConfig.getRemoteDirectory(), ftpConfig.getRemoteSubDirectory());
            String remoteFile = remoteDirectory + fileName;
            //log.info("put [{}] index={},name={},directory={}", fileName, index, remoteName, remoteDirectory);
            mkdir(remoteDirectory);
            if (monitor != null) {
                monitor.setFileName(fileName);
                monitor.setFileSize(fileStream.available());
            }
            String remoteTempFile = remoteFile + ".tmp";
            channel.put(fileStream, remoteTempFile, monitor);
            channel.rename(remoteTempFile, remoteFile);
            isPut = true;
            log.info("put [{}] true", remoteFile);
            return isPut;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 创建目录
     *
     * @param remoteDirectory
     * @return
     */
    @Override
    public boolean mkdir(String remoteDirectory) throws Exception {
        if (remoteDirectory == null || remoteDirectory.trim().isEmpty()) {
            return false;
        }
        boolean mkdir = true;
        try {
            channel.cd(remoteDirectory);
            mkdir = true;
        } catch (Exception e) {
            String[] dirs = remoteDirectory.split("/");
            String subDirectory = "";
            for (String dir : dirs) {
                if (dir == null || dir.trim().isEmpty()) {
                    continue;
                }
                subDirectory += "/" + dir;
                try {
                    channel.cd(subDirectory);
                    mkdir = true;
                } catch (SftpException se) {
                    try {
                        channel.mkdir(subDirectory);
                        channel.cd(subDirectory);
                        mkdir = true;
                    } catch (SftpException ex) {
                        mkdir = false;
                    }
                }
            }
            log.info("mkdir [{}] {}", remoteDirectory, mkdir);
        }
        return mkdir;
    }

    @Override
    public boolean delete(String file) throws Exception {
        return false;
    }

}
