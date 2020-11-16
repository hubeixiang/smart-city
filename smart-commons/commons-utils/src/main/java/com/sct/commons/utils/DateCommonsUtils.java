package com.sct.commons.utils;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期时间工具类,使用 org.apache.commons.lang3 三方类转换的
 *
 * @author sven
 */
public class DateCommonsUtils {

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_WITH_HOUR_PATTERN = "yyyy-MM-dd HH";
    public static final String SHORT_TIME_PATTERN = "HH:mm";
    private static Logger logger = LoggerFactory.getLogger(DateCommonsUtils.class);

    /**
     * Formats a Calendar into a date string.
     *
     * @param calendar the calendar value to be formatted into a time string.
     * @param pattern  the pattern describing the date and time format.
     * @return Formatted time.
     * @see DateFormatUtils#format(Calendar, String)
     */
    public static String format(final Calendar calendar, final String pattern) {
        if (calendar == null)
            return null;
        return DateFormatUtils.format(calendar, pattern);
    }

    /**
     * Formats a Calendar into a date string.
     *
     * @param calendar the calendar value to be formatted into a time string.
     * @return Formatted time.
     */
    public static String format(final Calendar calendar) {
        return format(calendar, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * Formats a Date into a date/time string.
     *
     * @param date    the time value to be formatted into a time string.
     * @param pattern the pattern describing the date and time format.
     * @return Formatted time.
     * @see DateFormatUtils#format(Date, String)
     */
    public static String format(final Date date, final String pattern) {
        if (date == null)
            return null;
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * Formats a Date into a date/time string.
     *
     * @param date the time value to be formatted into a time string.
     * @return Formatted time.
     */
    public static String format(final Date date) {
        return format(date, DEFAULT_DATETIME_PATTERN);
    }

    public static String formatDate(final String date, String parsePatterns) {
        Date d = parseDate(date, null);
        if (d == null) {
            return null;
        }
        return format(d, parsePatterns);
    }

    /**
     * Formats a Date into the string anly with hour & minutes.
     *
     * @param date the time value to be formatted
     * @return formatted time
     */
    public static String formatToShortTime(final Date date) {
        return format(date, SHORT_TIME_PATTERN);
    }

    /**
     * Formats a Date into the string anly with hour & minutes.
     *
     * @param date the time value to be formatted
     * @return formatted time
     */
    public static String formatToHourOnly(final Date date) {
        return format(date, "HH");
    }


    /**
     * Parses a string representing a date by trying a variety of different parsers.
     *
     * @param str           the date to parse
     * @param parsePatterns the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date, may be null
     * @see DateUtils#parseDate(String, String...)
     */
    public static Date parseDate(final String str, String... parsePatterns) {
        if (str == null) {
            return null;
        }
        if (parsePatterns == null || parsePatterns.length == 0) {
            parsePatterns = new String[]{SQL_DATE_FORMAT, DEFAULT_DATETIME_PATTERN, DATE_WITH_HOUR_PATTERN, DEFAULT_DATE_PATTERN};
        }
        try {
            return DateUtils.parseDate(str, parsePatterns);
        } catch (ParseException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    /**
     * Parses a string representing a date by trying a variety of different parsers.
     *
     * @param str           the date to parse
     * @param defaultDate   default date to return
     * @param parsePatterns the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date, may be null
     * @see DateUtils#parseDate(String, String...)
     */
    public static Date parseDate(final String str, final Date defaultDate, final String... parsePatterns) {
        Date result = parseDate(str, parsePatterns);
        if (result == null) {
            result = defaultDate;
        }
        return result;
    }


    public static int getMonth(Date date) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        return DateUtils.toCalendar(date).get(Calendar.MONDAY) + 1;
    }


    /**
     * 判断是否是周一
     */
    public static boolean isMonday(Date date) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        return DateUtils.toCalendar(date).get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    /**
     * 判断是否是月初一号
     */
    public static boolean isMonthBegin(Date date) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        return DateUtils.toCalendar(date).get(Calendar.DAY_OF_MONTH) == 1;
    }


    /**
     * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
     *
     * @param date 日期对象
     * @return 该天的起始时间
     */
    public static Date getDayBegin(Date date) {
        return getDayBegin(date, 0);
    }

    /**
     * Get begin time of a day.
     * e.g. 2005-10-02 00:00:00.000
     *
     * @param date      the date, not null
     * @param dayAmount the amount of day to add, may be negative
     * @return the new date
     * @throws IllegalArgumentException if the date is null
     */
    public static Date getDayBegin(Date date, int dayAmount) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        final Calendar cal = DateUtils.toCalendar(date);
        cal.add(Calendar.DAY_OF_MONTH, dayAmount);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
     *
     * @param date 日期对象
     * @return 该天的结束时间
     */
    public static Date getDayEnd(Date date) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        final Calendar cal = DateUtils.toCalendar(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取指定时间所在月的第一天，时分秒为零
     */
    public static Date getMonthBegin(Date date) {
        return getMonthBegin(date, 0);
    }

    /**
     * Get begin day of a month.
     * e.g. 2005-10-01 00:00:00.000
     *
     * @param date        the date, not null
     * @param monthAmount the amount of month to add, may be negative
     * @return the new date
     * @throws IllegalArgumentException if the date is null
     */
    public static Date getMonthBegin(Date date, int monthAmount) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        final Calendar cal = DateUtils.toCalendar(date);
        cal.add(Calendar.MONTH, monthAmount);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取本月最后一天,即下一月的第一天
     */
    public static Date getNextMonthBegin(Date date) {
        return getMonthBegin(date, 1);
    }

    /**
     * 根据type计算起始日期的差值, 仅仅实现了HOUR,MINUTE,SECOND逻辑
     *
     * @author tianlong
     */
    public static int diff(Date startDate, Date endDate, int calendarField) {
        if (startDate.after(endDate)) {
            return -1;
        }

        double diff = endDate.getTime() - startDate.getTime();

        switch (calendarField) {
            case Calendar.DAY_OF_MONTH:
            case Calendar.MONTH:
            case Calendar.YEAR:
                throw new NotImplementedException("Not support type.");
            case Calendar.HOUR:
                diff /= 60;
            case Calendar.MINUTE:
                diff /= 60;
            case Calendar.SECOND:
                diff /= 1000;
            default:
        }
        return (int) diff;
    }

    /**
     * 将日期转换为特定格式的日期形式。
     * 推荐使用 {@link #getDayBegin(Date)} 之类的方法，效率更高。
     *
     * @param date         the date to parse
     * @param parsePattern the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date, may be null
     */
    public static Date parseSpecificDate(Date date, String parsePattern) {
        //noinspection ConstantConditions
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (parsePattern == null) {
            parsePattern = DEFAULT_DATE_PATTERN;
        }
        String str = format(date, parsePattern);
        return parseDate(str, parsePattern);
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(GregorianCalendar cal) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar newXMLGregorianCalendar() {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar c = toXMLGregorianCalendar(cal);
        c.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return c;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendarDate(Date date) {
        Calendar cal = DateUtils.toCalendar(date);
        XMLGregorianCalendar c = newXMLGregorianCalendar();

        int year = cal.get(Calendar.YEAR);
        if (cal.get(Calendar.ERA) == GregorianCalendar.BC) {
            year = -year;
        }
        c.setYear(year);
        c.setMonth(cal.get(Calendar.MONTH) + 1);
        c.setDay(cal.get(Calendar.DAY_OF_MONTH));

        return c;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendarTime(Date date) {
        Calendar cal = DateUtils.toCalendar(date);
        XMLGregorianCalendar c = newXMLGregorianCalendar();

        c.setTime(
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND),
                cal.get(Calendar.MILLISECOND));

        return c;
    }

    /**
     * 获取Timestamp
     */
    public static Timestamp toSqlDate(String dateTimeStr) throws Exception {
        Date date = DateCommonsUtils.parseDate(dateTimeStr);
        return date == null ? null : new Timestamp(date.getTime());
    }

    /**
     * 获取Timestamp（不含时分秒）
     */
    public static Timestamp toSqlDateWithoutTime(String dateTimeStr) throws Exception {
        Date date = DateCommonsUtils.parseDate(dateTimeStr);
        return date == null ? null : new Timestamp(getDayBegin(date).getTime());
    }
}
