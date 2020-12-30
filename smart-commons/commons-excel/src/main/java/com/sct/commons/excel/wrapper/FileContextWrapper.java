package com.sct.commons.excel.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 文件包装类
 */
public class FileContextWrapper {
    private String name;
    private InputStream input;
    private ByteArrayOutputStream output;

    public FileContextWrapper() {
    }

    public FileContextWrapper(String name) {
        this.name = name;
    }

    public FileContextWrapper(String name, InputStream input) {
        this.name = name;
        this.input = input;
    }

    public FileContextWrapper(String name, ByteArrayOutputStream output) {
        this.name = name;
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public ByteArrayOutputStream getOutput() {
        return output;
    }

    public void setOutput(ByteArrayOutputStream output) {
        this.output = output;
    }
}
