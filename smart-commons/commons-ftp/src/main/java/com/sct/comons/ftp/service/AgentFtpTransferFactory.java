package com.sct.comons.ftp.service;

import com.sct.comons.ftp.monitor.IProgressMonitor;
import com.sct.comons.ftp.properties.FtpConfiguration;
import com.sct.comons.ftp.transfer.IFtpTransfer;
import com.sct.comons.ftp.transfer.factory.FtpTransferFactory;

public class AgentFtpTransferFactory {
    public IFtpTransfer createIFtpTransfer(FtpConfiguration ftpConfig, IProgressMonitor monitor) {
        return FtpTransferFactory.getInstance(ftpConfig, monitor);
    }

    public IFtpTransfer createIFtpTransfer(FtpConfiguration ftpConfig) {
        return FtpTransferFactory.getInstance(ftpConfig);
    }
}
