package com.sct.comons.ftp.transfer;


import com.sct.comons.ftp.file.FileWrapper;
import com.sct.comons.ftp.monitor.IProgressMonitor;
import com.sct.comons.ftp.properties.FtpConfiguration;

import java.util.List;

public abstract class AbstractFtpTransfer implements IFtpTransfer {

    protected IProgressMonitor monitor;

    protected FtpConfiguration ftpConfig;

    public void setFtpConfig(FtpConfiguration ftpConfig) {
        this.ftpConfig = ftpConfig;
    }

    public FtpConfiguration getFtpConfiguration() {
        return ftpConfig;
    }

    public void setProgressMonitor(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    public boolean isUse() {
        return ftpConfig.isUse();
    }

    public List<FileWrapper> list() throws Exception {
        return null;
    }

    protected String initRemoteDirectory(String remoteDirectory, String remoteSubDirectory) {
        String fullRemoteDirectory = "";
        if (remoteDirectory != null && !remoteDirectory.trim().isEmpty()) {
            fullRemoteDirectory += remoteDirectory;
            // /dir1/dir2/
            if (!fullRemoteDirectory.endsWith("/")) {
                fullRemoteDirectory += "/";
            }
        }

        if (remoteSubDirectory != null && !remoteSubDirectory.trim().isEmpty()) {
            // dir3/dir4/
            if (remoteSubDirectory.startsWith("/")) {
                remoteSubDirectory = remoteSubDirectory.substring(1);
            }
            fullRemoteDirectory += remoteSubDirectory;
        }

        // /dir1/dir2/dir3/dir4/
        if (!fullRemoteDirectory.endsWith("/")) {
            fullRemoteDirectory += "/";
        }
        return fullRemoteDirectory;
    }

}
