package com.sct.commons.file.location;

import java.util.Collection;

public class StringUtils {
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isObjectEmpty(final Object object) {
        return object == null;
    }

    public static boolean isCollectionEmpty(final Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
