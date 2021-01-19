package com.sct.service.core.api.service.file;

import com.sct.commons.file.fileutil.SimpleFileUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;

/**
 * 不同的服务有不同的实现
 */
public interface ServiceFileLocationApi {
    /**
     * 获取服务主目录
     *
     * @return
     */
    public String locationDir();

    /**
     * 获取服务主日志目录
     *
     * @return
     */
    public String locationLogDir();

    /**
     * 获取服务上传文件临时保存目录
     *
     * @return
     */
    public String locationUploadTemporaryDir();


    /**
     * 获取服务上传文件永久保存目录
     *
     * @return
     */
    public String locationUploadPermanentDir();

    /**
     * 程序自动生成的相关文件保存的目录,可以删除的
     *
     * @return
     */
    default String locationTemporaryDir() {
        String absolutePathFileDir = locationLogDir() + File.separator + "runtime";
        makeSureDirAlreadyExists(absolutePathFileDir);
        return absolutePathFileDir;
    }

    /**
     * 程序自动生成的相关文件,按照日期保存的目录,可以定时删除
     * 返回目录不包含日期这一级的目录
     *
     * @return D:\03Downloads\Browser\daily\
     */
    default String locationTemporaryDailyDir() {
        String absolutePathFileDir = locationLogDir() + File.separator + "runtime_daily";
        makeSureDirAlreadyExists(absolutePathFileDir);
        return absolutePathFileDir;
    }

    /**
     * 确保 locationTemporaryDailyDir()相对应的目录是存在的
     * 并返回当前的目录
     *
     * @return
     */
    default String makeSureLocationTemporaryDailyDir() {
        String dailyDir = locationTemporaryDailyDir();
        makeSureDirAlreadyExists(dailyDir);
        String currentDay = currentDayDir();
        String absolutePathFileDir = dailyDir + File.separator + currentDay;
        makeSureDirAlreadyExists(absolutePathFileDir);
        return absolutePathFileDir;
    }

    /**
     * 已经当前日期生成的目录名称
     *
     * @return
     */
    default String currentDayDir() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
    }

    default String datetime2idName() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss");
    }

    default String timestamp2idName() {
        return "A" + System.currentTimeMillis();
    }

    default void makeSureDirAlreadyExists(String absolutePathFileDir) {
        boolean ok = SimpleFileUtil.createNewDir(absolutePathFileDir, false);
        if (!ok) {
            throw new RuntimeException(String.format("create dir=%s failure!", absolutePathFileDir));
        }
    }

    default boolean delete(String absolutePathFileDir) {
        return SimpleFileUtil.delete(absolutePathFileDir);
    }
}
