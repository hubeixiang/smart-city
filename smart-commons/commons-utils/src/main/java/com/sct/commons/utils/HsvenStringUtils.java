package com.sct.commons.utils;

import com.sct.commons.utils.constant.CommonConstants;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符操作常用方法集
 * @author sven
 */
public class HsvenStringUtils {
	/**
	 * 编译后的正则表达式缓存
	 */
	private static final Map<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap<>();

	/**
	 * 编译一个正则表达式，并且进行缓存,如果换成已存在则使用缓存
	 *
	 * @param regex 表达式
	 * @return 编译后的Pattern
	 */
	public static final Pattern compileRegex(String regex) {
		Pattern pattern = PATTERN_CACHE.get(regex);
		if (pattern == null) {
			pattern = Pattern.compile(regex);
			PATTERN_CACHE.put(regex, pattern);
		}
		return pattern;
	}

	/**
	 * 字符串为空,或者全为空格组成
	 *
	 * @param cs
	 * @return
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串为空,或者字符串长度为0
	 *
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static String[] splitStringByByte(String original, int byteSize, int arraySize) throws UnsupportedEncodingException {
		return splitStringByByte(original, byteSize, arraySize, CommonConstants.encode_UTF8);
	}

	/**
	 * 将传入的字符串拆分为指定长度、指定份数的字符串数组
	 *
	 * @param original    要拆分的字符串
	 * @param byteSize    每份的字节长度
	 * @param arraySize   要拆分的总份数,如果字符串最终拆分的份数少于arraySize,则空余的字符数组元素为null,如果份数大于arraySize,则丢弃剩余字符
	 * @param charsetName 字符串编码格式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String[] splitStringByByte(String original, int byteSize, int arraySize, String charsetName)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(charsetName) || (!CommonConstants.encode_UTF8.equals(charsetName) && !CommonConstants.encode_GBK
				.equals(charsetName))) {
			throw new UnsupportedEncodingException(String.format("not supported encoding charset name %s", charsetName));
		}
		if (arraySize <= 0) {
			throw new UnsupportedEncodingException(String.format("spilt string to array,arraySize must > 0"));
		}
		String[] strTex = new String[arraySize];
		String org = original;
		if (org == null || "".equals(org.trim())) {
			strTex[0] = "";
		} else if (org.getBytes(charsetName).length <= byteSize) {
			strTex[0] = org;
		} else {
			for (int i = 0; i < strTex.length; i++) {
				strTex[i] = subStrByByteLen(org, charsetName, byteSize - 1, true);
				org = org.substring(strTex[i].length());

				//System.err.println("strTex["+i+"]="+strTex[i]+" original="+org);
				if (isBlank(org) || isEmpty(org)) {
					break;
				}
			}
		}
		return strTex;
	}

	public static String subStrByByteLen(String original, String charsetName, int byteLen, boolean isRightTruncation)
			throws UnsupportedEncodingException {
		if (original == null || "".equals(original.trim()))
			return "";
		if (charsetName == null || "".equals(charsetName))
			throw new UnsupportedEncodingException("subStrByByteLen，must code format!!! ");
		byte[] bytes = original.getBytes(charsetName);
		if (byteLen <= 0)
			return "";
		if (byteLen >= bytes.length)
			return original;

		int tempLen = 0;
		String result = "";
		if (isRightTruncation) {
			tempLen = new String(bytes, 0, byteLen, charsetName).length();
			result = original.substring(0, tempLen);
			if (result != null && !"".equals(result.trim()) && result.getBytes(charsetName).length > byteLen)
				result = original.substring(0, tempLen - 1);
		} else {
			tempLen = new String(bytes, 0, bytes.length - byteLen + 1, charsetName).length() - 1;
			result = original.substring(tempLen);

			if (result != null && !"".equals(result.trim()) && result.getBytes(charsetName).length > byteLen)
				result = original.substring(tempLen + 1);
		}
		return result;
	}

	/**
	 * 将字符串的第一位转为小写
	 *
	 * @param str 需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String toLowerCaseFirstOne(String str) {
		if (Character.isLowerCase(str.charAt(0)))
			return str;
		else {
			char[] chars = str.toCharArray();
			chars[0] = Character.toLowerCase(chars[0]);
			return new String(chars);
		}
	}

	/**
	 * 将字符串的第一位转为大写
	 *
	 * @param str 需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String toUpperCaseFirstOne(String str) {
		if (Character.isUpperCase(str.charAt(0)))
			return str;
		else {
			char[] chars = str.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return new String(chars);
		}
	}

	/**
	 * 下划线命名转为驼峰命名
	 *
	 * @param str 下划线命名格式
	 * @return 驼峰命名格式
	 */
	public static final String underScoreCase2CamelCase(String str) {
		if (!str.contains("_"))
			return str;
		StringBuilder sb = new StringBuilder();
		char[] chars = str.toCharArray();
		boolean hitUnderScore = false;
		sb.append(chars[0]);
		for (int i = 1; i < chars.length; i++) {
			char c = chars[i];
			if (c == '_') {
				hitUnderScore = true;
			} else {
				if (hitUnderScore) {
					sb.append(Character.toUpperCase(c));
					hitUnderScore = false;
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰命名法转为下划线命名
	 *
	 * @param str 驼峰命名格式
	 * @return 下划线命名格式
	 */
	public static final String camelCase2UnderScoreCase(String str) {
		StringBuilder sb = new StringBuilder();
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Character.isUpperCase(c)) {
				sb.append("_").append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将异常栈信息转为字符串
	 *
	 * @param e 字符串
	 * @return 异常栈
	 */
	public static String throwable2String(Throwable e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		return writer.toString();
	}

	/**
	 * 字符串连接，将参数列表拼接为一个字符串
	 *
	 * @param more 追加
	 * @return 返回拼接后的字符串
	 */
	public static String concat(Object... more) {
		return concatSpiltWith("", more);
	}

	public static String concatSpiltWith(String split, Object... more) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < more.length; i++) {
			if (i != 0)
				buf.append(split);
			buf.append(more[i]);
		}
		return buf.toString();
	}

	/**
	 * 将字符串转移为ASCII码
	 *
	 * @param str 字符串
	 * @return 字符串ASCII码
	 */
	public static String toASCII(String str) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = str.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	public static String toUnicode(String str) {
		StringBuffer strBuf = new StringBuffer();
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			strBuf.append("\\u").append(Integer.toHexString(chars[i]));
		}
		return strBuf.toString();
	}

	public static String toUnicodeString(char[] chars) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuf.append("\\u").append(Integer.toHexString(chars[i]));
		}
		return strBuf.toString();
	}

	static final char CN_CHAR_START = '\u4e00';
	static final char CN_CHAR_END = '\u9fa5';

	/**
	 * 是否包含中文字符
	 *
	 * @param str 要判断的字符串
	 * @return 是否包含中文字符
	 */
	public static boolean containsChineseChar(String str) {
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] >= CN_CHAR_START && chars[i] <= CN_CHAR_END)
				return true;
		}
		return false;
	}

	/**
	 * 对象是否为无效值
	 *
	 * @param obj 要判断的对象
	 * @return 是否为有效值（不为null 和 "" 字符串）
	 */
	public static boolean isNullOrEmpty(Object obj) {
		return obj == null || "".equals(obj.toString());
	}

	/**
	 * 参数是否是有效数字 （整数或者小数）
	 *
	 * @param obj 参数（对象将被调用string()转为字符串类型）
	 * @return 是否是数字
	 */
	public static boolean isNumber(Object obj) {
		if (obj instanceof Number)
			return true;
		return isInt(obj) || isDouble(obj);
	}

	public static String matcherFirst(String patternStr, String text) {
		Pattern pattern = compileRegex(patternStr);
		Matcher matcher = pattern.matcher(text);
		String group = null;
		if (matcher.find()) {
			group = matcher.group();
		}
		return group;
	}

	/**
	 * 参数是否是有效整数
	 *
	 * @param obj 参数（对象将被调用string()转为字符串类型）
	 * @return 是否是整数
	 */
	public static boolean isInt(Object obj) {
		if (isNullOrEmpty(obj))
			return false;
		if (obj instanceof Integer)
			return true;
		return obj.toString().matches("[-+]?\\d+");
	}

	/**
	 * 字符串参数是否是double
	 *
	 * @param obj 参数（对象将被调用string()转为字符串类型）
	 * @return 是否是double
	 */
	public static boolean isDouble(Object obj) {
		if (isNullOrEmpty(obj))
			return false;
		if (obj instanceof Double || obj instanceof Float)
			return true;
		return compileRegex("[-+]?\\d+\\.\\d+").matcher(obj.toString()).matches();
	}

	/**
	 * 判断一个对象是否为boolean类型,包括字符串中的true和false
	 *
	 * @param obj 要判断的对象
	 * @return 是否是一个boolean类型
	 */
	public static boolean isBoolean(Object obj) {
		if (obj instanceof Boolean)
			return true;
		String strVal = String.valueOf(obj);
		return "true".equalsIgnoreCase(strVal) || "false".equalsIgnoreCase(strVal);
	}

	/**
	 * 对象是否为true
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isTrue(Object obj) {
		return "true".equals(String.valueOf(obj));
	}

	/**
	 * 判断一个数组里是否包含指定对象
	 *
	 * @param arr 对象数组
	 * @param obj 要判断的对象
	 * @return 是否包含
	 */
	public static boolean contains(Object arr[], Object... obj) {
		if (arr == null || obj == null || arr.length == 0)
			return false;
		return Arrays.asList(arr).containsAll(Arrays.asList(obj));
	}

	/**
	 * 将对象转为int值,如果对象无法进行转换,则使用默认值
	 *
	 * @param object       要转换的对象
	 * @param defaultValue 默认值
	 * @return 转换后的值
	 */
	public static int toInt(Object object, int defaultValue) {
		if (object instanceof Number)
			return ((Number) object).intValue();
		if (isInt(object)) {
			return Integer.parseInt(object.toString());
		}
		if (isDouble(object)) {
			return (int) Double.parseDouble(object.toString());
		}
		return defaultValue;
	}

	/**
	 * 将对象转为int值,如果对象不能转为,将返回0
	 *
	 * @param object 要转换的对象
	 * @return 转换后的值
	 */
	public static int toInt(Object object) {
		return toInt(object, 0);
	}

	/**
	 * 将对象转为long类型,如果对象无法转换,将返回默认值
	 *
	 * @param object       要转换的对象
	 * @param defaultValue 默认值
	 * @return 转换后的值
	 */
	public static long toLong(Object object, long defaultValue) {
		if (object instanceof Number)
			return ((Number) object).longValue();
		if (isInt(object)) {
			return Long.parseLong(object.toString());
		}
		if (isDouble(object)) {
			return (long) Double.parseDouble(object.toString());
		}
		return defaultValue;
	}

	/**
	 * 将对象转为 long值,如果无法转换,则转为0
	 *
	 * @param object 要转换的对象
	 * @return 转换后的值
	 */
	public static long toLong(Object object) {
		return toLong(object, 0);
	}

	/**
	 * 将对象转为Double,如果对象无法转换,将使用默认值
	 *
	 * @param object       要转换的对象
	 * @param defaultValue 默认值
	 * @return 转换后的值
	 */
	public static double toDouble(Object object, double defaultValue) {
		if (object instanceof Number)
			return ((Number) object).doubleValue();
		if (isNumber(object)) {
			return Double.parseDouble(object.toString());
		}
		if (null == object)
			return defaultValue;
		return 0;
	}

	/**
	 * 将对象转为Double,如果对象无法转换,将使用默认值0
	 *
	 * @param object 要转换的对象
	 * @return 转换后的值
	 */
	public static double toDouble(Object object) {
		return toDouble(object, 0);
	}

	/**
	 * 分隔字符串,根据正则表达式分隔字符串,只分隔首个,剩下的的不进行分隔,如: 1,2,3,4 将分隔为 ['1','2,3,4']
	 *
	 * @param str   要分隔的字符串
	 * @param regex 分隔表达式
	 * @return 分隔后的数组
	 */
	public static String[] splitFirst(String str, String regex) {
		return str.split(regex, 2);
	}

	/**
	 * 将对象转为字符串,如果对象为null,则返回null,而不是"null"
	 *
	 * @param object 要转换的对象
	 * @return 转换后的对象
	 */
	public static String toString(Object object) {
		return toString(object, null);
	}

	/**
	 * 将对象转为字符串,如果对象为null,则使用默认值
	 *
	 * @param object       要转换的对象
	 * @param defaultValue 默认值
	 * @return 转换后的字符串
	 */
	public static String toString(Object object, String defaultValue) {
		if (object == null)
			return defaultValue;
		return String.valueOf(object);
	}

	/**
	 * 将对象转为String后进行分割，如果为对象为空或者空字符,则返回null
	 *
	 * @param object 要分隔的对象
	 * @param regex  分隔规则
	 * @return 分隔后的对象
	 */
	public static final String[] toStringAndSplit(Object object, String regex) {
		if (isNullOrEmpty(object))
			return null;
		return String.valueOf(object).split(regex);
	}

	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = 0;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
				chLength++;
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}

}
