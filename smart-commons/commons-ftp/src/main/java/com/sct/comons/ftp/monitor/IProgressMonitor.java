package com.sct.comons.ftp.monitor;

import com.jcraft.jsch.SftpProgressMonitor;
import org.apache.commons.net.io.CopyStreamListener;

/**
 * FTP传输监控接口
 */
public interface IProgressMonitor extends SftpProgressMonitor, CopyStreamListener {

    /**
     * 设置监控文件的名称
     *
     * @param fileName
     */
    public void setFileName(String fileName);

    /**
     * 设置监控文件的大小
     *
     * @param fileSize
     */
    public void setFileSize(long fileSize);

    /**
     * 设置传输打印次数
     * <br>传输多少次打印一次
     *
     * @param times
     */
    public void setTransferedTimes(int times);

}
