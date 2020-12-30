package com.sct.comons.ftp.file;

import org.apache.commons.lang3.time.DateFormatUtils;

public class FileWrapper {

    private String pathname;
    private long lastModified;

    public FileWrapper() {
    }

    public FileWrapper(String pathname) {
        this.pathname = pathname;
    }

    public FileWrapper(String pathname, long lastModified) {
        this.pathname = pathname;
        this.lastModified = lastModified;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String toSimpleString() {
        return pathname;
    }

    @Override
    public String toString() {
        return "FtpFile[" + pathname + "," + DateFormatUtils.format(lastModified, "yyyy-MM-dd HH:mm:ss") + "]";
    }

}
