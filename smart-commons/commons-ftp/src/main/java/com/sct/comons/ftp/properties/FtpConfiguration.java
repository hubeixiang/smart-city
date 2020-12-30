package com.sct.comons.ftp.properties;

import com.sct.comons.ftp.constants.FtpConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = FtpConstants.FTP_CONFIGURATION_PREFX)
public class FtpConfiguration {
    private boolean enable = false;
    private String protocol = "sftp";
    private String host;
    private int port = 22;
    private String username;
    private String password;
    private String remoteDirectory;
    private String remoteSubDirectory;
    private int fileType = 2;
    private boolean monitor = false;
    private boolean use = true;
    private int connectMode;
    private int connectTimes;
    private int bufferSize;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteDirectory() {
        return remoteDirectory;
    }

    public void setRemoteDirectory(String remoteDirectory) {
        this.remoteDirectory = remoteDirectory;
    }

    public String getRemoteSubDirectory() {
        return remoteSubDirectory;
    }

    public void setRemoteSubDirectory(String remoteSubDirectory) {
        this.remoteSubDirectory = remoteSubDirectory;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isMonitor() {
        return monitor;
    }

    public void setMonitor(boolean monitor) {
        this.monitor = monitor;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public int getConnectMode() {
        return connectMode;
    }

    public void setConnectMode(int connectMode) {
        this.connectMode = connectMode;
    }

    public int getConnectTimes() {
        return connectTimes;
    }

    public void setConnectTimes(int connectTimes) {
        this.connectTimes = connectTimes;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public FtpConfiguration clone() {
        FtpConfiguration ftp = new FtpConfiguration();
        ftp.setProtocol(this.protocol);
        ftp.setHost(this.host);
        ftp.setPort(this.port);
        ftp.setUsername(this.username);
        ftp.setPassword(this.password);
        ftp.setRemoteDirectory(this.remoteDirectory);
        ftp.setRemoteSubDirectory(this.remoteSubDirectory);
        ftp.setFileType(this.fileType);
        ftp.setBufferSize(this.bufferSize);
        ftp.setConnectMode(this.connectMode);
        ftp.setConnectTimes(this.connectTimes);
        ftp.setUse(this.use);
        ftp.setMonitor(this.monitor);
        return ftp;
    }

    public String toString() {
        return "FtpConfig{protocol=" + this.protocol + ",host=" + this.host + "/" + this.port + ",user=" + this.username + "/" + this.password + ",remote=" + this.remoteDirectory + ",sub=" + this.remoteSubDirectory + ",fileType=" + this.fileType + ",monitor=" + this.monitor + ",use=" + this.use + ",mode=" + this.connectMode + ",times=" + this.connectTimes + ",buffer=" + this.bufferSize + '}';
    }
}
