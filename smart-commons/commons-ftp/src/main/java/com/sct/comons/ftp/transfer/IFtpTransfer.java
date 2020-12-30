package com.sct.comons.ftp.transfer;


import com.sct.comons.ftp.file.FileWrapper;
import com.sct.comons.ftp.monitor.IProgressMonitor;
import com.sct.comons.ftp.properties.FtpConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * FTP/SFTP传输接口
 */
public interface IFtpTransfer {

    /**
     * 连接FTP/SFTP
     *
     * @return
     */
    public boolean connect();

    /**
     * 断开连接FTP/SFTP
     *
     * @return
     */
    public boolean disconnect();

    /**
     * 传输文件
     *
     * @param file 文件名称
     * @return
     */
    public boolean put(File file) throws Exception;

    public boolean put(InputStream fileStream, String fileName) throws Exception;

    /**
     * 传输文件列表
     *
     * @param files 文件列表
     * @return
     */
    public boolean put(List<File> files) throws Exception;

    public List<FileWrapper> list() throws Exception;

    public boolean mkdir(String directory) throws Exception;

    public boolean delete(String file) throws Exception;

    public FtpConfiguration getFtpConfiguration();

    public boolean isUse();

    /**
     * 设置传输过程监控
     *
     * @param monitor
     */
    public void setProgressMonitor(IProgressMonitor monitor);

}
