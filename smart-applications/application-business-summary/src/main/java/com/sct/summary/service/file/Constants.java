package com.sct.summary.service.file;

import java.io.File;

public class Constants {
    public final static String DEFAULT_ENCRYPTED_PASSWORD = "e7b2238c589ddc6f2d86bf30861768e3";
    public final static String LOG_HOME = "log.home";
    private final static String OS = System.getProperty("os.name");
    private final static String APP_HOME = "APP_HOME";
    private final static String DEFAULT_EXPORT_PATH = ".";
    public final static String PROGRAM_HOME = System.getProperty(APP_HOME, DEFAULT_EXPORT_PATH) + File.separator;
    public final static String PROGRAM_LOG_HOME = System.getProperty(LOG_HOME, DEFAULT_EXPORT_PATH) + File.separator;
    public final static String PROGRAM_UPLOAD_TemporaryDir = PROGRAM_LOG_HOME + "temporary_upload" + File.separator;
    public final static String PROGRAM_UPLOAD_PermanentDir = PROGRAM_LOG_HOME + "permanent_upload" + File.separator;
}
