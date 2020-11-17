package com.sct.commons.file.excel.readexcel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月27日 上午8:48:51
 *
 */

public class ExcelUtil {
	public static void main(String[] args) {
		System.out.println(ExcelUtil.getJavaFormatDate(-1));
	}

	public static String getJavaFormatDate(double d) {
		if (d <= 0) {
			d = 1;
		}
		return ExcelUtil.formatDate(HSSFDateUtil.getJavaDate(d), "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String formatStr) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}

	/**
	 * 将字符串格式化为2010-01-01 00:00:00的格式
	 * author:123 create at 2015年5月27日 下午3:42:15
	 *
	 * @param timeString
	 * @return
	 */
	public static String timeStampString(String timeString) {
		if (timeString == null || timeString.equals("")) {
			return "";
		} else {
			timeString = timeString.replace("T", " ");
			if (timeString.contains("+")) {
				timeString = timeString.substring(0, timeString.indexOf("+"));
			}
			if (timeString.contains("/")) {
				timeString = timeString.replace("/", "-");
			}
			if (timeString.contains("\\")) {
				timeString = timeString.replace("\\", "-");
			}
			return timeString.trim();
		}
	}

	public static String dateString(String timeString) {
		if (timeString == null || timeString.equals("")) {
			return "";
		} else {
			timeString = ExcelUtil.timeStampString(timeString);
			if (timeString.contains(".")) {
				timeString = timeString.substring(0, timeString.indexOf("."));
			}
			return timeString;
		}
	}

	public static String dateStringFormat(String timeString) {
		if (timeString == null || timeString.equals("")) {
			return "";
		} else {
			timeString = ExcelUtil.dateString(timeString);
			if (timeString.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				return timeString;
			} else if (timeString.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return timeString.substring(0, 10) + " 00:00:00";
			} else {
				return "";
			}
		}
	}
}
