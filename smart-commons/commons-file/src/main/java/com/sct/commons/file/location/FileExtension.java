package com.sct.commons.file.location;

import java.io.Serializable;

/**
 * 文件后缀定义
 *
 * @author sven
 * @date 2019/10/29 10:00
 */
public class FileExtension implements Serializable {
    private final static long serialVersionUID = 129l;
    //后缀,比如  ".xls" ,".xlsx"
    private String extension;
    //后缀展示信息,比如 "excel03","excel07"
    private String extensionDisplay;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtensionDisplay() {
        return extensionDisplay;
    }

    public void setExtensionDisplay(String extensionDisplay) {
        this.extensionDisplay = extensionDisplay;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("FileExtension[extension=").append(extension).append(",extensionDisplay=").append(extensionDisplay).append("]")
                .toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((extension == null) ? 0 : extension.hashCode());
        result = prime * result + ((extensionDisplay == null) ? 0 : extensionDisplay.hashCode());
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
        FileExtension other = (FileExtension) obj;
        if (extension == null) {
            if (other.extension != null)
                return false;
        } else if (!extension.equals(other.extension))
            return false;
        if (extensionDisplay == null) {
            if (other.extensionDisplay != null)
                return false;
        } else if (!extensionDisplay.equals(other.extensionDisplay))
            return false;
        return true;
    }
}
