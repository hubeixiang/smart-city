package com.sct.commons.file.fileutil;

import com.sct.commons.file.FileConstants;
import com.sct.commons.file.exception.FileUtilException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class WebHttpServletResponseUtil {
    private static Logger logger = LoggerFactory.getLogger(WebHttpServletResponseUtil.class);

    /**
     * 浏览器下载附件时,设置下载的 HttpServletResponse 头信息
     *
     * @param response web 返回信息
     * @param filename web浏览器下载的文件名  (下载文件.zip  下载文件.txt  下载文件.xlxs 等等).文件名需要是全名,带文件类型后缀
     * @throws UnsupportedEncodingException
     */
    public static void setHttpServletResponseAttachmentInfo(HttpServletResponse response, String filename)
            throws UnsupportedEncodingException {
        String charSet = FileConstants.CHARACTER_SET_UTF8;
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding(charSet);
        //response.setHeader("Content-Disposition", "attachment; filename="+new String(downloadFile.getBytes("utf-8"), "ISO8859-1" ));
        //response.setHeader( "Content-Disposition", "attachment;filename=" + new String( downloadFile.getBytes("gb2312"), "ISO8859-1" ) );
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, charSet) + ";filename*=utf-8''" + java.net.URLEncoder.encode(filename, charSet));
    }


    public static <T> void attachmentFileForHttpServletResponse(HttpServletResponse response, String filename, Supplier<InputStream> isSupplier) throws FileUtilException {
        OutputStream out = null;
        InputStream is = null;
        FileInputStream fis = null;
        try {
            //设置浏览器下载头
            setHttpServletResponseAttachmentInfo(response, filename);
            //获取response流操作对象
            out = response.getOutputStream();
            //获取要下载的文件内容输入流
            is = isSupplier.get();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            String error = String.format("download file attachment servlet response exception:%s", e.getMessage());
            logger.error(error, e);
            throw new FileUtilException(error, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static ResponseEntity<?> stream(String name, byte[] bytes) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        List<String> exposedHeaders = new ArrayList<>();
        exposedHeaders.add("filename");
        headers.setAccessControlExposeHeaders(exposedHeaders);
        headers.set("filename", URLEncoder.encode(name, "UTF-8"));
        return ResponseEntity.ok().headers(headers)
                .contentLength(bytes.length)
                .body(bytes);
    }
}
