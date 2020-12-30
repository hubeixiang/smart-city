package com.sct.comons.ftp.transfer.factory;


import com.sct.comons.ftp.enums.FtpProtocal;
import com.sct.comons.ftp.monitor.IProgressMonitor;
import com.sct.comons.ftp.properties.FtpConfiguration;
import com.sct.comons.ftp.transfer.IFtpTransfer;
import com.sct.comons.ftp.transfer.impl.FtpTransferImpl;
import com.sct.comons.ftp.transfer.impl.SftpTransferImpl;

/**
 * FTP创建工厂
 */
public class FtpTransferFactory {

    public static IFtpTransfer getInstance(FtpConfiguration ftpConfig, IProgressMonitor monitor) {
        IFtpTransfer transfer = null;
        String ftpProtocal = ftpConfig.getProtocol();
        if (ftpProtocal == null) {
            ftpProtocal = FtpProtocal.FTP.name();
        }
        boolean useMonitor = ftpConfig.isMonitor();
        if (FtpProtocal.FTP.toString().equalsIgnoreCase(ftpProtocal)) {
            transfer = new FtpTransferImpl(ftpConfig);
        } else if (FtpProtocal.SFTP.toString().equalsIgnoreCase(ftpProtocal)) {
            transfer = new SftpTransferImpl(ftpConfig);
        } else {
            throw new RuntimeException("Unknown ftp protocal '" + ftpProtocal + "'");
        }

        if (useMonitor) {
            transfer.setProgressMonitor(monitor);
        }
        return transfer;
    }

    public static IFtpTransfer getInstance(FtpConfiguration ftpConfig) {
        return getInstance(ftpConfig, null);
    }

}
