package com.sct.commons.file.fileutil;

import com.sct.commons.file.FileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SimpleFileUtil {
    private static final String _R_N = "\r\n";
    private static final int _1024 = 1024;
    public static Logger logger = LoggerFactory.getLogger(SimpleFileUtil.class);

    public static String getAbsolutePath() {
        return new File("").getAbsolutePath();
    }

    /**
     * 读取文件的编码
     *
     * @param file
     * @return 编码, 默认为GBK
     */
    public static String getCharset(File file) {
        String charset = FileConstants.CHARACTER_SET_GBK; // 默认编码
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        String absolutePathFile = null;
        try {
            if (file == null) {
                return null;
            }
            if (!file.exists()) {
                return null;
            }

            absolutePathFile = file.getAbsolutePath();
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = FileConstants.CHARACTER_SET_UTF_16LE;
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = FileConstants.CHARACTER_SET_UTF_16BE;
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = FileConstants.CHARACTER_SET_UTF8;
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    // 单独出现BF以下的，也算是GBK
                    if (0x80 <= read && read <= 0xBF)
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF)// 双字节 (0xC0 - 0xDF)
                            // (0x80 -
                            // 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                        // 也有可能出错，但是几率较小
                    } else if (0xE0 <= read && read <= 0xEF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = FileConstants.CHARACTER_SET_UTF8;
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
                logger.warn(loc + " " + Integer.toHexString(read));
            }
        } catch (Exception e) {
            logger.error("getCharset Exception! ,absolutePathFile:" + absolutePathFile, e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                }
            }
        }
        return charset;
    }


    /**
     * 将指定properties对象,保存输出到指定文件中
     *
     * @param properties
     * @param targetFile
     * @return 是否正常保存
     */
    public static boolean storeProperties(Properties properties, File targetFile) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            properties.store(fileOutputStream, "");
            return true;
        } catch (Exception e) {
            logger.error("storeProperties Exception.filePath=" + targetFile, e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    /**
     * 将properties文件加载为Properties
     *
     * @param propertiesFilePath
     * @return 读取的properties
     */
    public static Properties loadProperties(String propertiesFilePath) {
        File propertiesFile = new File(propertiesFilePath);
        return loadProperties(propertiesFile);
    }

    /**
     * 将properties文件加载为Properties
     *
     * @param propertiesFile
     * @return 读取的properties
     */
    public static Properties loadProperties(File propertiesFile) {
        FileInputStream fileInputStream = null;
        String filePath = null;
        try {
            if (propertiesFile == null) {
                logger.error("properties file is null.");
                return new Properties();
            }
            filePath = propertiesFile.getAbsolutePath();
            if (!propertiesFile.exists()) {
                logger.error("properties file not exists.filePath=" + filePath);
                return null;
            }
            if (!propertiesFile.isFile()) {
                logger.error("properties file not exists.filePath=" + filePath);
                return null;
            }
            fileInputStream = new FileInputStream(propertiesFile);
            Properties properties = new Properties();

            properties.load(fileInputStream);
            return properties;
        } catch (Exception e) {
            logger.error("properties load Exception.filePath=" + filePath, e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                } finally {
                    fileInputStream = null;
                }
            }
        }
        return null;
    }

    //写入文件

    /**
     * 将二进制数组写入文件
     * author:123 create at 2015年7月9日 上午9:22:30
     *
     * @param absolutePathFile 文件路径及名字
     * @param content          二进制数组
     * @param isAppend         是否增量写入文件
     * @return 是否写入成功
     */
    public static boolean write(String absolutePathFile, byte[] content, boolean isAppend) {
        BufferedInputStream bi = new BufferedInputStream(new ByteArrayInputStream(content));
        return write(absolutePathFile, bi, isAppend);
    }

    /**
     * 将二进制数组写入文件
     * author:123 create at 2015年7月9日 上午9:22:30
     *
     * @param absolutePathFile 文件路径及名字
     * @param input            输入流
     * @param isAppend         是否增量写入文件
     * @return 是否写入成功
     */
    public static boolean write(String absolutePathFile, InputStream input, boolean isAppend) {
        try {
            File file = new File(absolutePathFile);
            FileOutputStream fos = new FileOutputStream(file, isAppend);
            byte[] byteff = new byte[_1024];
            int size;
            while ((size = input.read(byteff)) != -1) {
                fos.write(byteff, 0, size);
            }
            fos.flush();
            fos.close();
            return true;
        } catch (IOException io) {
            logger.error("write input[] Exception.absolutePathFile=" + absolutePathFile, io);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                } finally {
                    input = null;
                }
            }
        }
        return false;
    }


    /**
     * 将指定的json串写入指定的文件中
     *
     * @param absolutePathFile 文件的绝对url
     * @param jsonContext      要写入的json内容
     */
    public static void write(String absolutePathFile, String jsonContext) {
        BufferedWriter bw = null;
        if (jsonContext == null || jsonContext.equalsIgnoreCase("")) {
            logger.warn(String.format("write absolutePathFile=%s,jsonContext is empty", absolutePathFile));
            return;
        }
        try {
            SimpleFileUtil.createNewFile(absolutePathFile, true);
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(absolutePathFile), FileConstants.CHARACTER_SET_GBK);
            bw = new BufferedWriter(out);
            bw.write(jsonContext);
            bw.newLine();
            bw.flush();
        } catch (IOException io) {
            logger.error("write byte[] Exception.absolutePathFile=" + absolutePathFile, io);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                }
                bw = null;
            }
        }
    }

    /**
     * 将提供的数组写入到指定的文件中
     *
     * @param absolutePathFile 文件绝对路径
     * @param datas            数组内容
     * @return 是否写入成功
     */
    public static boolean write(String absolutePathFile, List<String> datas) {
        BufferedWriter bw = null;
        try {
            SimpleFileUtil.createNewFile(absolutePathFile, true);
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(absolutePathFile), FileConstants.CHARACTER_SET_GBK);
            bw = new BufferedWriter(out);
            for (String data : datas) {
                bw.write(data);
                bw.newLine();
            }
            bw.flush();
            return true;
        } catch (Exception e) {
            logger.error("write list Exception.absolutePathFile=" + absolutePathFile, e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                }
                bw = null;
            }
        }
        return false;
    }

    /**
     * 将指定的内容以csv的方式写入到指定文件
     *
     * @param absolutePathFile 指定文件
     * @param firstdata        第一行数据,csv的第一行
     * @param datas            csv数据内容
     * @param sp               csv的分隔符
     * @return 是否写入成功
     */
    public static boolean write(String absolutePathFile, List<String> firstdata, List<List<Object>> datas, char sp) {
        BufferedWriter bw = null;
        try {
            SimpleFileUtil.createNewFile(absolutePathFile, true);
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(absolutePathFile), FileConstants.CHARACTER_SET_GBK);
            bw = new BufferedWriter(out);
            if (firstdata != null && firstdata.size() > 0) {
                //写文件头
                bw.write(combineString(firstdata, sp));
                bw.newLine();
                //写内容
                if (datas != null && datas.size() > 0) {
                    for (List<Object> data : datas) {
                        bw.write(combineObject(data, sp));
                        bw.newLine();
                    }
                }
            }
            bw.flush();
            return true;
        } catch (Exception e) {
            logger.error("write list csv Exception.absolutePathFile=" + absolutePathFile, e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                }
                bw = null;
            }
        }
        return false;
    }

    public static String combineObject(List<Object> list, char sp) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sb.append(list.get(i) == null ? "" : String.valueOf(list.get(i)));
            } else {
                sb.append(sp).append(list.get(i) == null ? "" : String.valueOf(list.get(i)));
            }
        }
        sb.append(sp);
        return sb.toString();
    }

    public static String combineString(List<String> list, char sp) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sb.append(list.get(i) == null ? "" : String.valueOf(list.get(i)));
            } else {
                sb.append(sp).append(list.get(i) == null ? "" : String.valueOf(list.get(i)));
            }
        }
        sb.append(sp);
        return sb.toString();
    }

    /**
     * 将指定文件内容读出为byte[]内容
     *
     * @param absolutePathFile
     * @return 字节数据文件内容
     */
    public static byte[] read2byte(String absolutePathFile) {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            File file = new File(absolutePathFile);
            in = new BufferedInputStream(
                    new FileInputStream(file));
            out = new ByteArrayOutputStream(_1024);

            byte[] temp = new byte[_1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }

            byte[] content = out.toByteArray();
            if (content.length > 0) {
                return content;
            } else {
                return null;
            }
        } catch (IOException ioe) {
            logger.error("read2byte Exception.absolutePathFile=" + absolutePathFile, ioe);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
                in = null;
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
                out = null;
            }
        }
        return null;
    }

    /**
     * 将文件内容以StringBuffer方式读出
     *
     * @param absolutePathFile
     * @return 文件内容
     */
    public static StringBuffer read2StringBuffer(String absolutePathFile) {
        BufferedReader bufferedreader = null;
        try {
            StringBuffer sb = new StringBuffer();
            FileInputStream is = new FileInputStream(new File(absolutePathFile));
            InputStreamReader sr = new InputStreamReader(is, FileConstants.CHARACTER_SET_UTF8);
            bufferedreader = new BufferedReader(sr);
            String readLineContent = null;

            while ((readLineContent = bufferedreader.readLine()) != null) {
                sb.append(readLineContent);
            }

            if (sb.length() > 0) {
                return sb;
            } else {
                return null;
            }
        } catch (IOException ioe) {
            logger.error("read2StringBuffer Exception.absolutePathFile=" + absolutePathFile, ioe);
        } finally {
            if (bufferedreader != null) {
                try {
                    bufferedreader.close();
                } catch (IOException e) {
                }
                bufferedreader = null;
            }
        }
        return null;
    }

    /**
     * 将文件内容以List<String>的方式读出
     *
     * @param absolutePathFile
     * @return 文件内容
     */
    public static List<String> read2ListString(String absolutePathFile) {
        BufferedReader bufferedreader = null;
        try {
            List<String> fileLineList = new ArrayList<>();
            FileInputStream is = new FileInputStream(new File(absolutePathFile));
            InputStreamReader sr = new InputStreamReader(is, FileConstants.CHARACTER_SET_UTF8);
            bufferedreader = new BufferedReader(sr);
            String readLineContent = null;

            while ((readLineContent = bufferedreader.readLine()) != null) {
                fileLineList.add(readLineContent);
            }

            if (fileLineList.size() > 0) {
                return fileLineList;
            } else {
                return null;
            }
        } catch (IOException ioe) {
            logger.error("read2ListString Exception.absolutePathFile=" + absolutePathFile, ioe);
        } finally {
            if (bufferedreader != null) {
                try {
                    bufferedreader.close();
                } catch (IOException e) {
                }
                bufferedreader = null;
            }
        }
        return null;
    }

    /**
     * 判断文件是否存在
     *
     * @param absolutePathFile
     * @return 是否存在
     */
    public static boolean existsFile(String absolutePathFile) {
        return SimpleFileUtil.existsFile(new File(absolutePathFile));
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     * @return 是否存在
     */
    public static boolean existsFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文件目录是否存在
     *
     * @param absolutePathFileDir 文件目录
     * @return 是否存在
     */
    public static boolean existsDir(String absolutePathFileDir) {
        return SimpleFileUtil.existsDir(new File(absolutePathFileDir));
    }

    /**
     * 文件目录是否存在
     *
     * @param file 目录的File对象
     * @return 是否存在
     */
    public static boolean existsDir(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    ///删除相关

    /**
     * 删除指定文件或者目录,如果是目录同时删除其子节点
     *
     * @param absolutePathFile 要删除的文件
     * @return 删除是否成功
     */
    public static boolean delete(String absolutePathFile) {
        return SimpleFileUtil.delete(new File(absolutePathFile));
    }


    /**
     * 删除文件或者目录,如果是目录同时删除其子节点
     *
     * @param file 要删除的文件
     * @return 删除是否成功
     */
    public static boolean delete(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return SimpleFileUtil.deleteDir(file);
            } else {
                return SimpleFileUtil.deleteFile(file);
            }
        } else {
            return true;
        }
    }

    /**
     * 指定的删除文件
     *
     * @param absolutePathFile 指定的文件
     * @return 删除是否成功
     */
    public static boolean deleteFile(String absolutePathFile) {
        return SimpleFileUtil.deleteFile(new File(absolutePathFile));
    }

    /**
     * 指定的删除文件
     *
     * @param file 指定的文件
     * @return 删除是否成功
     */
    public static boolean deleteFile(File file) {
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return true;
        }
    }


    /**
     * 删除目录及其子目录与文件
     *
     * @param absolutePathFileDir 指定的目录
     * @return 删除是否成功
     */
    public static boolean deleteDir(String absolutePathFileDir) {
        return SimpleFileUtil.deleteDir(new File(absolutePathFileDir));
    }


    /**
     * 删除目录及其子目录与文件
     *
     * @param dir 指定的目录
     * @return 删除是否成功
     */
    public static boolean deleteDir(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            if (dir.listFiles().length > 0) {
                for (File subfile : dir.listFiles()) {
                    SimpleFileUtil.delete(subfile);
                }
            }
            //表明此时是空目录,可以直接删除此目录
            return dir.delete();
        } else {
            return true;
        }
    }
    //创建相关,创建相关都是如果指定的是存在的,则不用重新创建,如果不存在这需要创建

    //创建相关,创建相关都是如果指定的是存在的,则需要删除重新创建,如果不存在这需要创建

    /**
     * 创建指定路径的目录
     *
     * @param absolutePathFileDir 指定的目录
     * @param isTemp              true:如果存在就需要删除重建    false:如果存在就不用重新创建
     * @return 是否创建成功
     * @throws IOException
     */
    public static boolean createNewDir(String absolutePathFileDir, boolean isTemp) {
        return SimpleFileUtil.createNewDir(new File(absolutePathFileDir), isTemp);
    }

    /**
     * 创建指定路径的目录
     *
     * @param dir    指定的目录
     * @param isTemp true:如果存在就需要删除重建    false:如果存在就不用重新创建
     * @return 是否创建成功
     */
    public static boolean createNewDir(File dir, boolean isTemp) {
        boolean canCreate = true;
        if (dir.exists()) {
            if (dir.isDirectory() && !isTemp) {
                return true;
            } else {
                //删除已经存在的
                if (!delete(dir)) {
                    //删除失败,不能创建
                    canCreate = false;
                }
            }
        }
        if (canCreate) {
            return dir.mkdirs();
        } else {
            return false;
        }
    }

    /**
     * 创建指定路径的文件
     *
     * @param absolutePathFile 指定的文件
     * @param isTemp           true:如果存在就需要删除重建    false:如果存在就不用重新创建
     * @return 是否创建成功
     * @throws IOException
     */
    public static boolean createNewFile(String absolutePathFile, boolean isTemp) throws IOException {
        return SimpleFileUtil.createNewFile(new File(absolutePathFile), isTemp);
    }

    /**
     * 创建指定路径的文件
     *
     * @param file   指定的文件
     * @param isTemp true:如果存在就需要删除重建    false:如果存在就不用重新创建
     * @return 是否创建成功
     * @throws IOException
     */
    public static boolean createNewFile(File file, boolean isTemp) throws IOException {
        boolean canCreate = true;
        if (file.exists()) {
            if (file.isFile() && !isTemp) {
                return true;
            } else {
                //删除已经存在的
                if (!delete(file)) {
                    //删除失败,不能创建
                    canCreate = false;
                }
            }
        } else {
            //如果是不存在的,则需要判断其父节点是否存在,只有父节点存在是,才能创建文件
            file.getParentFile().mkdirs();
        }
        if (canCreate) {
            return file.createNewFile();
        } else {
            return false;
        }
    }

    /**
     * 判断文件创建时间是否超过设定时间,超过则删除
     *
     * @param file         传入的文件
     * @param limitTimeOut 超时的时间,单位为分钟
     * @return 删除是否成功
     */
    public static boolean deleteFileTimeCondition(File file, long limitTimeOut) {
        if (file.exists()) {
            long modifiedTime = file.lastModified();
            if (System.currentTimeMillis() - modifiedTime > limitTimeOut * 60 * 1000) {
                return SimpleFileUtil.deleteFile(file);
            }
            return false;
        }
        return false;
    }

    /**
     * 删除目录下所有超出时间限制的文件,有子目录也会判断子目录下的文件,而且子目录为空时,需要删除子目录
     *
     * @param dir          传入的目录
     * @param limitTimeOut 超时的时间,单位为分钟
     * @param delEmptyDir  当前如果是目录,而且为空目录时,是否删除该目录,true:删除,false:不删除
     * @return 删除是否成功
     */
    public static boolean deleteDirTimeCondition(File dir, final long limitTimeOut, boolean delEmptyDir) {
        if (dir.exists()) {
            if (dir.isFile()) {
                return SimpleFileUtil.deleteFileTimeCondition(dir, limitTimeOut);
            } else {
                if (delEmptyDir && dir.list().length == 0) {
                    return SimpleFileUtil.deleteDir(dir);
                } else if (dir.list().length > 0) {
                    //获取满足条件的文件
                    File[] deleteFile = dir.listFiles(new FileFilter() {
                        long myCurrentTime = System.currentTimeMillis();

                        @Override
                        public boolean accept(File pathname) {
                            if (pathname.exists() && pathname.isFile()) {
                                long modifiedTime = pathname.lastModified();
                                if (myCurrentTime - modifiedTime > limitTimeOut * 60 * 1000) {
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                    if (deleteFile != null) {
                        for (File file : deleteFile) {
                            SimpleFileUtil.deleteFileTimeCondition(file, limitTimeOut);
                        }
                    }

                    //获取所有的子目录,对子目录进行判断
                    File[] subdir = dir.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            if (pathname.exists() && pathname.isDirectory()) {
                                return true;
                            }
                            return false;
                        }
                    });
                    if (subdir != null) {
                        for (File file : subdir) {
                            SimpleFileUtil.deleteDirTimeCondition(file, limitTimeOut, true);
                            if (file.exists() && file.isDirectory() && file.list().length == 0) {
                                SimpleFileUtil.deleteDir(file);
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 采用锁的形式读取文本文件
     *
     * @param absolutePathFile
     * @param characterCode
     * @return 读取出来的字符串
     */
    public static String readTxtByLock(String absolutePathFile, String characterCode) {
        RandomAccessFile raf = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        StringBuffer buff = new StringBuffer();
        try {
            raf = new RandomAccessFile(absolutePathFile, "rw");
            fileChannel = raf.getChannel();
            try {
                fileLock = fileChannel.tryLock(0, Long.MAX_VALUE, true);
                logger.info(absolutePathFile + "get the lock");
            } catch (Exception ex) {
                logger.error("", ex);
            }

            ByteBuffer bb = ByteBuffer.allocate(_1024);

            if ((fileChannel.read(bb)) != -1) {
                buff.append(new String(bb.array(), characterCode));
            }
            bb.clear();
        } catch (Exception e) {
            logger.error("readTxtByLock Exception.absolutePathFile=" + absolutePathFile + ",characterCode=" + characterCode, e);
        } finally {
            if (fileLock != null) {
                try {
                    fileLock.release();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }

            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }

        return buff.toString();
    }

    /**
     * 采用锁的形式写文本文件
     *
     * @param absolutePathFile
     * @param characterCode
     * @return
     */
    public static void wiriteTxtByLock(String absolutePathFile, String characterCode) {
        RandomAccessFile raf = null;
        FileChannel fc = null;
        FileLock fl = null;
        FileInputStream in = null;
        try {
            raf = new RandomAccessFile(absolutePathFile, "rw");
            fc = raf.getChannel();
            while (true) {
                try {
                    fl = fc.tryLock();
                    try {
                        fl = fc.tryLock(0, Long.MAX_VALUE, true);
                        logger.info(absolutePathFile + "get the lock");
                    } catch (Exception ex) {
                        logger.error("", ex);
                    }
                    break;
                } catch (Exception e) {
                    logger.error("", e);
                }

            }
            in = new FileInputStream(absolutePathFile);
            byte[] b = new byte[_1024];
            int len = 0;
            ByteBuffer bb = ByteBuffer.allocate(_1024);
            while ((len = in.read(b)) != -1) {
                bb.clear();
                bb.put(b, 0, len);
                bb.flip();
                fc.write(bb);

            }
        } catch (Exception e) {
            logger.error("wiriteTxtByLock Exception.absolutePathFile=" + absolutePathFile + ",characterCode=" + characterCode, e);
        } finally {
            if (fl != null) {
                try {
                    fl.release();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }

            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
            if (fc != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

    /**
     * 列举指定目录下的文件
     *
     * @param absolutePathDir 绝对路径
     * @param isListChildren  是否列举子目录下的文件
     * @return
     */
    public static List<File> fileList(String absolutePathDir, boolean isListChildren) {
        if (absolutePathDir == null || absolutePathDir.trim().equals("")) {
            return null;
        }

        File root = new File(absolutePathDir);
        if (!root.exists()) {
            return null;
        }

        List<File> list = new ArrayList<File>();
        if (root.isDirectory() && isListChildren) {
            File[] files = root.listFiles();
            if (files == null || files.length == 0) {
                return null;
            }
            for (File file : files) {
                if (file.isDirectory() && isListChildren) {
                    List<File> subList = fileList(file.getAbsolutePath(), isListChildren);
                    if (subList != null && subList.size() > 0) {
                        list.addAll(subList);
                    }
                } else {
                    list.add(file);
                }
            }
        } else {
            list.add(root);
        }
        return list;
    }
}
