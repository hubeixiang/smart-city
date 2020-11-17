package com.sct.commons.file.location;

import java.io.Serializable;

/**
 * @author sven
 * @date 2019/10/29 14:48
 * 给定的文件名称的描述信息
 */
public class FileNameDescribe implements Serializable {
    private final static long serialVersionUID = 129l;
    //文件名字不带后缀的
    private String fileName;
    //文件的后缀描述
    private FileExtension fileExtension;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileExtension getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(FileExtension fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("FileNameDescribe[extension=").append(fileExtension).append(",fileName=").append(fileName).append("]").toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileExtension == null) ? 0 : fileExtension.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FileNameDescribe other = (FileNameDescribe) obj;
        if (fileExtension == null) {
            if (other.fileExtension != null)
                return false;
        } else if (!fileExtension.equals(other.fileExtension))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        return true;
    }
}
