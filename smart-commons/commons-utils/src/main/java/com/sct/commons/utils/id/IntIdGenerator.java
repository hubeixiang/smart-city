package com.sct.commons.utils.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.UUID;
import java.util.zip.CRC32;

public class IntIdGenerator {
    private static Logger logger = LoggerFactory.getLogger(IntIdGenerator.class);
    private static final String MD5 = "MD5";
    private static final long INITIALCRC_64 = 0xFFFFFFFFFFFFFFFFL;
    private static long[] CRC_64_TABLE = new long[256];
    private static final long POLY64REV = 0x95AC9329AC4BC9B5L;

    private static final int[] CRC_32_TABLE =
            {
                    0x00000000, 0x77073096, 0xee0e612c, 0x990951ba, 0x076dc419, 0x706af48f,
                    0xe963a535, 0x9e6495a3, 0x0edb8832, 0x79dcb8a4, 0xe0d5e91e,
                    0x97d2d988, 0x09b64c2b, 0x7eb17cbd, 0xe7b82d07, 0x90bf1d91,
                    0x1db71064, 0x6ab020f2, 0xf3b97148, 0x84be41de, 0x1adad47d,
                    0x6ddde4eb, 0xf4d4b551, 0x83d385c7, 0x136c9856, 0x646ba8c0,
                    0xfd62f97a, 0x8a65c9ec, 0x14015c4f, 0x63066cd9, 0xfa0f3d63,
                    0x8d080df5, 0x3b6e20c8, 0x4c69105e, 0xd56041e4, 0xa2677172,
                    0x3c03e4d1, 0x4b04d447, 0xd20d85fd, 0xa50ab56b, 0x35b5a8fa,
                    0x42b2986c, 0xdbbbc9d6, 0xacbcf940, 0x32d86ce3, 0x45df5c75,
                    0xdcd60dcf, 0xabd13d59, 0x26d930ac, 0x51de003a, 0xc8d75180,
                    0xbfd06116, 0x21b4f4b5, 0x56b3c423, 0xcfba9599, 0xb8bda50f,
                    0x2802b89e, 0x5f058808, 0xc60cd9b2, 0xb10be924, 0x2f6f7c87,
                    0x58684c11, 0xc1611dab, 0xb6662d3d, 0x76dc4190, 0x01db7106,
                    0x98d220bc, 0xefd5102a, 0x71b18589, 0x06b6b51f, 0x9fbfe4a5,
                    0xe8b8d433, 0x7807c9a2, 0x0f00f934, 0x9609a88e, 0xe10e9818,
                    0x7f6a0dbb, 0x086d3d2d, 0x91646c97, 0xe6635c01, 0x6b6b51f4,
                    0x1c6c6162, 0x856530d8, 0xf262004e, 0x6c0695ed, 0x1b01a57b,
                    0x8208f4c1, 0xf50fc457, 0x65b0d9c6, 0x12b7e950, 0x8bbeb8ea,
                    0xfcb9887c, 0x62dd1ddf, 0x15da2d49, 0x8cd37cf3, 0xfbd44c65,
                    0x4db26158, 0x3ab551ce, 0xa3bc0074, 0xd4bb30e2, 0x4adfa541,
                    0x3dd895d7, 0xa4d1c46d, 0xd3d6f4fb, 0x4369e96a, 0x346ed9fc,
                    0xad678846, 0xda60b8d0, 0x44042d73, 0x33031de5, 0xaa0a4c5f,
                    0xdd0d7cc9, 0x5005713c, 0x270241aa, 0xbe0b1010, 0xc90c2086,
                    0x5768b525, 0x206f85b3, 0xb966d409, 0xce61e49f, 0x5edef90e,
                    0x29d9c998, 0xb0d09822, 0xc7d7a8b4, 0x59b33d17, 0x2eb40d81,
                    0xb7bd5c3b, 0xc0ba6cad, 0xedb88320, 0x9abfb3b6, 0x03b6e20c,
                    0x74b1d29a, 0xead54739, 0x9dd277af, 0x04db2615, 0x73dc1683,
                    0xe3630b12, 0x94643b84, 0x0d6d6a3e, 0x7a6a5aa8, 0xe40ecf0b,
                    0x9309ff9d, 0x0a00ae27, 0x7d079eb1, 0xf00f9344, 0x8708a3d2,
                    0x1e01f268, 0x6906c2fe, 0xf762575d, 0x806567cb, 0x196c3671,
                    0x6e6b06e7, 0xfed41b76, 0x89d32be0, 0x10da7a5a, 0x67dd4acc,
                    0xf9b9df6f, 0x8ebeeff9, 0x17b7be43, 0x60b08ed5, 0xd6d6a3e8,
                    0xa1d1937e, 0x38d8c2c4, 0x4fdff252, 0xd1bb67f1, 0xa6bc5767,
                    0x3fb506dd, 0x48b2364b, 0xd80d2bda, 0xaf0a1b4c, 0x36034af6,
                    0x41047a60, 0xdf60efc3, 0xa867df55, 0x316e8eef, 0x4669be79,
                    0xcb61b38c, 0xbc66831a, 0x256fd2a0, 0x5268e236, 0xcc0c7795,
                    0xbb0b4703, 0x220216b9, 0x5505262f, 0xc5ba3bbe, 0xb2bd0b28,
                    0x2bb45a92, 0x5cb36a04, 0xc2d7ffa7, 0xb5d0cf31, 0x2cd99e8b,
                    0x5bdeae1d, 0x9b64c2b0, 0xec63f226, 0x756aa39c, 0x026d930a,
                    0x9c0906a9, 0xeb0e363f, 0x72076785, 0x05005713, 0x95bf4a82,
                    0xe2b87a14, 0x7bb12bae, 0x0cb61b38, 0x92d28e9b, 0xe5d5be0d,
                    0x7cdcefb7, 0x0bdbdf21, 0x86d3d2d4, 0xf1d4e242, 0x68ddb3f8,
                    0x1fda836e, 0x81be16cd, 0xf6b9265b, 0x6fb077e1, 0x18b74777,
                    0x88085ae6, 0xff0f6a70, 0x66063bca, 0x11010b5c, 0x8f659eff,
                    0xf862ae69, 0x616bffd3, 0x166ccf45, 0xa00ae278, 0xd70dd2ee,
                    0x4e048354, 0x3903b3c2, 0xa7672661, 0xd06016f7, 0x4969474d,
                    0x3e6e77db, 0xaed16a4a, 0xd9d65adc, 0x40df0b66, 0x37d83bf0,
                    0xa9bcae53, 0xdebb9ec5, 0x47b2cf7f, 0x30b5ffe9, 0xbdbdf21c,
                    0xcabac28a, 0x53b39330, 0x24b4a3a6, 0xbad03605, 0xcdd70693,
                    0x54de5729, 0x23d967bf, 0xb3667a2e, 0xc4614ab8, 0x5d681b02,
                    0x2a6f2b94, 0xb40bbe37, 0xc30c8ea1, 0x5a05df1b, 0x2d02ef8d,};

    static {
        // http://bioinf.cs.ucl.ac.uk/downloads/crc64/crc64.c
        long part;
        for (int i = 0; i < 256; i++) {
            part = i;
            for (int j = 0; j < 8; j++) {
                long x = ((int) part & 1) != 0 ? POLY64REV : 0;
                part = (part >> 1) ^ x;
            }
            CRC_64_TABLE[i] = part;
        }
    }

    public static String generatorUuidForString(){
        return UUID.randomUUID().toString();
    }

    public static long generatorUuidForLong(){
       return getCRC64(generatorUuidForString());
    }

    /**
     * get the crc32 long value,from string GBK encoding to int
     *
     * @param val Java String
     * @return int value,when val is null or error,return 0
     */
    public static int getCRC32WithZip(String val) {
        if (val == null || val.length() == 0) {
            return 0;
        }
        CRC32 crc32 = new CRC32();
        byte[] bytes = null;
        try {
            bytes = val.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
        for (int jj = 0; jj < bytes.length; jj++) {
            crc32.update((int) bytes[jj]);
        }
        return (int) crc32.getValue();
    }

    public static int getCRC32(String val) {
        if (val == null || val.length() == 0) {
            return 0;
        }
        byte[] bytes = null;
        try {
            bytes = val.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
        int crc = 0xffffffff;
        for (byte b : bytes) {
            crc = (crc >>> 8 ^ CRC_32_TABLE[(crc ^ b) & 0xff]);
        }
        crc = crc ^ 0xffffffff;
        return (int) crc;
    }


    /**
     * get the crc64 long value,from string GBK encoding to long
     *
     * @param val Java String
     * @return long value,when val is null or error,return 0
     */
    public static long getCRC64(String val) {
        if (val == null || val.length() == 0) {
            return 0;
        }
        byte[] bytes = null;
        try {
            bytes = val.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            return 0;
        }

        long crc = INITIALCRC_64;
        for (int k = 0, n = bytes.length; k < n; ++k) {
            crc = CRC_64_TABLE[(((int) crc) ^ bytes[k]) & 0xff] ^ (crc >> 8);
        }
        return crc;
    }

    /**
     * from long number to byte array,
     * put high byte of number to low array byte, byte[0]=the highest byte of number
     *
     * @param n number
     * @return byte array
     */
    public static byte[] longToBytes(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }

    /**
     * from long to byte array,put high byte of number to low array byte, byte[0]=the highest byte of number
     *
     * @param n      the number to be converted
     * @param array  the array to be placed the number
     * @param offset the offset of the array
     */
    public static void longToBytes(long n, byte[] array, int offset) {
        array[7 + offset] = (byte) (n & 0xff);
        array[6 + offset] = (byte) (n >> 8 & 0xff);
        array[5 + offset] = (byte) (n >> 16 & 0xff);
        array[4 + offset] = (byte) (n >> 24 & 0xff);
        array[3 + offset] = (byte) (n >> 32 & 0xff);
        array[2 + offset] = (byte) (n >> 40 & 0xff);
        array[1 + offset] = (byte) (n >> 48 & 0xff);
        array[0 + offset] = (byte) (n >> 56 & 0xff);
    }

    /**
     * from byte array to long number,byte[0]=the highest byte of number
     *
     * @param array the array to be converted
     * @return the long number have been converted
     */
    public static long bytesToLong(byte[] array) {
        return ((((long) array[0] & 0xff) << 56)
                | (((long) array[1] & 0xff) << 48)
                | (((long) array[2] & 0xff) << 40)
                | (((long) array[3] & 0xff) << 32)
                | (((long) array[4] & 0xff) << 24)
                | (((long) array[5] & 0xff) << 16)
                | (((long) array[6] & 0xff) << 8)
                | (((long) array[7] & 0xff) << 0));
    }

    /**
     * from byte array to long number,byte[0]=the highest byte of number
     *
     * @param array  the array to be converted
     * @param offset the offset of the array
     * @return the long number have been converted
     */
    public static long bytesToLong(byte[] array, int offset) {
        return ((((long) array[offset + 0] & 0xff) << 56)
                | (((long) array[offset + 1] & 0xff) << 48)
                | (((long) array[offset + 2] & 0xff) << 40)
                | (((long) array[offset + 3] & 0xff) << 32)
                | (((long) array[offset + 4] & 0xff) << 24)
                | (((long) array[offset + 5] & 0xff) << 16)
                | (((long) array[offset + 6] & 0xff) << 8)
                | (((long) array[offset + 7] & 0xff) << 0));
    }

    public static String getMD5(String val) {
        return getMD5(val, "utf-8");
    }

    public static String getMD5(String val, String encodeStr) {
        try {
            // 生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance(MD5);
            // 使用指定的字节数组更新摘要。
            md.update(val.getBytes(encodeStr));
            // 通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            // 生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * convert string to a MD5,then return the low 8 bytes(64bit) with long type
     *
     * @param val       the string to be converted
     * @param encodeStr the encoding format when from string to byte array
     * @return the long number using the MD5 low 8 bytes,0 is for null/empty input string or exception.
     */
    public static long getMD5Low64(String val, String encodeStr) {
        if (val == null || val.length() == 0) {
            return 0;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            byte[] inputByteArray = val.getBytes(encodeStr);
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return bytesToLong(resultByteArray, 8);

        } catch (Exception e) {
            logger.error("getMD5Low64 Exception",e);
            return 0;
        }
    }

    /**
     * convert string(with GBK) to a MD5,then return the low 8 bytes(64bit) with long type
     *
     * @param val the string to be converted
     * @return the long number using the MD5 low 8 bytes,0 is for null/empty input string or exception.
     */
    public static long getMD5Low64(String val) {
        return getMD5Low64(val, "GBK");
    }


    public static Map<String, String> soapMsgToMap(String msgStr)
            throws ParserConfigurationException {

        // javax.xml.soap.SOAPMessage soap=new javax.xml.soap.SOAPMessage.
        Document doc = null;
        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        DocumentBuilder document = builder.newDocumentBuilder();
        return null;
    }

//	public static void main(String[] args) throws Exception
//	{
//		System.out.println("Function Test[MD5 low 64bits]:------------");
//		String [] testArr={
//				"北京市亿阳信通科技有限公司_BOCO_质量是研发的生命线",
//				"中国北京海淀区杏石口路西山赢府2-2405",
//				"ENB_23123.CEL_100",
//				"OMC_3401.ENBN_HF-市区-JAC大学南门移动联通铁塔处HFBBU01",
//				"",
//				null
//		} ;
//
//		for (int jj=0;jj<testArr.length;jj++)
//		{
//			System.out.println(testArr[jj] + ":" + getMD5Low64(testArr[jj]));
//		}
//
//		System.out.println("\r\nPerformance Test:------------");
//
//		long val = 0;
//		int count = 100 * 10000;
//		String oid =testArr[0];
//
//		long curMs = System.currentTimeMillis();
//		for (int jj = 0; jj < count; jj++)
//		{
//			val =getMD5Low64(oid);
//			 //val =getCRC32WithZip(oid);
//			 //val= getCRC64(oid);
//		}
//		curMs = System.currentTimeMillis() - curMs;
//
//		System.out.println("[MD5_Low_64bits]:\t" + oid + ":" +   val +",count:"+ count  + ", useMs:" + curMs + " rate:"
//				+ (count * 1000L / curMs) + " /s");
//
//
//		curMs = System.currentTimeMillis();
//		for (int jj = 0; jj < count; jj++)
//		{
//			val = getCRC64(oid);
//		}
//		curMs = System.currentTimeMillis() - curMs;
//
//		System.out.println("[CRC64]:\t\t" + oid + ":" +   val +",count:"+ count + ", useMs:" + curMs + " rate:"
//				+ (count * 1000L / curMs) + " /s");
//
//	}

}
