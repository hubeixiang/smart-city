package com.sct.commons.utils.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DataColumnarUtil {

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNumber(Object value) {
        try {
            Number num = ((Number) value);
            return true;
        } catch (Exception ee) {
            return false;
        }
    }

    public static boolean isDate(Object value) {
        try {

            if (value instanceof java.util.Date
                    || value instanceof java.sql.Date
                    || value instanceof java.sql.Timestamp
                    || value instanceof java.sql.Time) {
                return true;
            }
            return false;
        } catch (Exception ee) {
            return false;
        }
    }

    public static boolean isString(Object value) {
        if (value instanceof String) {
            return true;
        }
        return false;
    }

    public static Double stringToDouble(String object) {
        try {
            return Double.parseDouble(object);
        } catch (NumberFormatException ne) {
            String str = object;
            if (str.length() > 1 && (str.endsWith("d") || str.endsWith("D"))) {
                String tmp = str.substring(0, str.length() - 1);
                return Double.parseDouble(tmp);
            } else {
                throw ne;
            }
        }
    }

    public static Long stringToLong(String object) {
        try {
            return Long.parseLong(object);
        } catch (NumberFormatException ne) {
            String str = object;
            if (str.length() > 1 && (str.endsWith("l") || str.endsWith("L"))) {
                String tmp = str.substring(0, str.length() - 1);
                return Long.parseLong(tmp);
            } else {
                throw ne;
            }
        }
    }

    public static java.util.Date objectToDate(Object dateObj, SimpleDateFormat dateFormat) {
        if (dateObj == null) {
            return null;
        }
        if (isDate(dateObj)) {
            return (java.util.Date) dateObj;
        }

        if (dateObj instanceof Long) {
            return new java.util.Date(((Long) dateObj).longValue());
        }

        if (dateObj instanceof String) {
            if (dateFormat == null) {
                return (java.util.Date) (java.sql.Date.valueOf((String) dateObj));
            }
            try {
                return dateFormat.parse((String) dateObj);
            } catch (ParseException e) {
                return null;
            }
        }
        if (dateObj instanceof Integer) {
            return new java.util.Date(1000L * (long) (((Integer) dateObj).intValue()));
        }
        return null;
    }

    public static <T> T adapterValue(Object value, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        Object object = null;
        if (clazz.equals(Byte.class)) {
            object = adapterValueForByte(value);
        } else if (clazz.equals(Double.class)) {
            object = adapterValueForDouble(value);
        } else if (clazz.equals(Float.class)) {
            object = adapterValueForFloat(value);
        } else if (clazz.equals(Integer.class)) {
            object = adapterValueForInteger(value);
        } else if (clazz.equals(Long.class)) {
            object = adapterValueForLong(value);
        } else if (clazz.equals(Short.class)) {
            object = adapterValueForShort(value);
        } else if (clazz.equals(Boolean.class)) {
            object = adapterValueForBoolean(value);
        } else if (clazz.equals(String.class)) {
            object = adapterValueForString(value);
        }

        if (object == null) {
            return null;
        } else {
            return (T) object;
        }
    }

    public static Byte adapterValueForByte(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Byte) {
            return (Byte) value;
        } else if (DataColumnarUtil.isNumber(value)) {
            return ((Number) value).byteValue();
        } else if (DataColumnarUtil.isString(value)) {
            return Byte.parseByte(value.toString());
        }
        throw new RuntimeException("Param is not a byte number ");
    }

    public static Double adapterValueForDouble(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Double) {
            return (Double) value;
        } else if (DataColumnarUtil.isNumber(value)) {
            return ((Number) value).doubleValue();
        } else if (DataColumnarUtil.isString(value)) {
            return DataColumnarUtil.stringToDouble(value.toString());
        }
        throw new RuntimeException("Param is not a double number ");
    }

    public static Float adapterValueForFloat(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Float) {
            return (Float) value;
        } else if (DataColumnarUtil.isNumber(value)) {
            return ((Number) value).floatValue();
        } else if (DataColumnarUtil.isString(value)) {
            return Float.parseFloat(value.toString());
        }
        throw new RuntimeException("Param is not a float number ");
    }

    public static Integer adapterValueForInteger(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else if (DataColumnarUtil.isNumber(value)) {
            return ((Number) value).intValue();
        } else if (DataColumnarUtil.isString(value)) {
            return Integer.parseInt(value.toString());
        }
        throw new RuntimeException("Param is not a int number ");
    }

    public static Long adapterValueForLong(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Long) {
            return (Long) value;
        } else if (DataColumnarUtil.isNumber(value)) {
            return ((Number) value).longValue();
        } else if (DataColumnarUtil.isString(value)) {
            return DataColumnarUtil.stringToLong(value.toString());
        } else if (DataColumnarUtil.isDate(value)) {
            return ((java.util.Date) value).getTime();
        }
        throw new RuntimeException("Param is not a long number ");
    }

    public static Short adapterValueForShort(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Short) {
            return (Short) value;
        } else if (DataColumnarUtil.isNumber(value)) {
            return ((Number) value).shortValue();
        } else if (DataColumnarUtil.isString(value)) {
            return Short.parseShort(value.toString());
        }
        throw new RuntimeException("Param is not a short number ");
    }

    public static Boolean adapterValueForBoolean(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (DataColumnarUtil.isString(value)) {
            return Boolean.valueOf(value.toString());
        }
        throw new RuntimeException("Param is not a boolean ");
    }

    public static String adapterValueForString(Object value) {
        if (DataColumnarUtil.isNull(value)) {
            return null;
        } else if (DataColumnarUtil.isString(value)) {
            return (String) value;
        } else if (DataColumnarUtil.isDate(value)) {
            return String.valueOf(((java.util.Date) value).getTime());
        } else {
            return String.valueOf(value);
        }
    }
}
