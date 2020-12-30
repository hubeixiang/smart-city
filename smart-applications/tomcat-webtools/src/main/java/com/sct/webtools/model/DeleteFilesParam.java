package com.sct.webtools.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteFilesParam implements Serializable {
    private static final long serialVersionUID = 71717L;
    private List<String> files = new ArrayList<>();

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
