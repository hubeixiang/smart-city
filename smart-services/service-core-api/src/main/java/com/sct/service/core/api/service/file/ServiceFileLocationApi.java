package com.sct.service.core.api.service.file;

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
}
