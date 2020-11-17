package com.sct.commons.file.location;

import java.io.Serializable;

/**
 * @author sven
 * @date 2019/10/29 9:57
 * 外部传给服务识别的服务内部文件定位信息
 */
public class FileLocation implements Serializable {
    private final static long serialVersionUID = 128L;
    //最终生成可用生成一个访问文件的唯一定位符
    // serviceUrl + "/" + fileUuid + fileExtension.extension
    //文件后缀,不能为空
    private FileExtension fileExtension;
    //文件唯一定位标志,使用算法生成的uuid
    private String fileUuid;
    //文件在服务器上存放的目录,不能为空
    private String serviceUrl;

    public FileExtension getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(FileExtension fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("FileLocation[extension=").append(fileExtension).append(",fileUuid=").append(fileUuid).append(",serviceUrl=")
                .append(serviceUrl).append("]").toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileExtension == null) ? 0 : fileExtension.hashCode());
        result = prime * result + ((fileUuid == null) ? 0 : fileUuid.hashCode());
        result = prime * result + ((serviceUrl == null) ? 0 : serviceUrl.hashCode());
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
        FileLocation other = (FileLocation) obj;
        if (fileExtension == null) {
            if (other.fileExtension != null)
                return false;
        } else if (!fileExtension.equals(other.fileExtension))
            return false;
        if (fileUuid == null) {
            if (other.fileUuid != null)
                return false;
        } else if (!fileUuid.equals(other.fileUuid))
            return false;
        if (serviceUrl == null) {
            if (other.serviceUrl != null)
                return false;
        } else if (!serviceUrl.equals(other.serviceUrl))
            return false;
        return true;
    }
}
