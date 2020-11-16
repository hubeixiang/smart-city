package com.sct.commons.utils;

public class ClassEqualsUtils {
    public static int hashCode(int prime, int result, int local) {
        return prime * result + local;
    }

    public static int hashcode(int prime, int result, Object local) {
        return prime * result + ((local == null) ? 0 : local.hashCode());
    }

    public static int hashcode(int prime, int result, String local) {
        return prime * result + ((local == null) ? 0 : local.hashCode());
    }

    public static int hashcode(int prime, int result, Number local) {
        return prime * result + ((local == null) ? 0 : local.intValue());
    }

    public static int hashcode(int prime, int result, boolean local) {
        return prime * result + (local ? 1 : 2);
    }

    public static boolean isEquals(Object local, Object other) {
        if (local == null) {
            if (other != null)
                return false;
        } else if (!local.equals(other))
            return false;
        return true;
    }

    public static boolean isequals(short local, short other) {
        return local == other;
    }

    public static boolean isequals(int local, int other) {
        return local == other;
    }

    public static boolean isequals(long local, long other) {
        return local == other;
    }

    public static boolean isequals(double local, double other) {
        return local == other;
    }

    public static boolean isequals(byte local, byte other) {
        return local == other;
    }

    public static boolean isequals(boolean local, boolean other) {
        return local == other;
    }
}
