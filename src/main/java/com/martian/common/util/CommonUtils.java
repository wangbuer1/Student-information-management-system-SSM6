package com.martian.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 验证工具类
 */
public class CommonUtils {

    /**
     * 验证一个对象是否非空，支持String,Map,Collection等。
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        return isBaseEmpty(o);
    }

    /**
     * 验证String是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return ((s == null) || (s.length() == 0));
    }

    /**
     * 验证数组是否为空
     *
     * @param arrays
     * @return
     */
    public static boolean isEmpty(Object[] arrays) {
        return ((arrays == null) || (arrays.length == 0));
    }

    /**
     * 验证一个集合是否为空
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> c) {
        return ((c == null) || (c.size() == 0));
    }

    /**
     * 验证Map集合是否为空
     *
     * @param m
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> m) {
        return ((m == null) || (m.size() == 0));
    }

    /**
     * 验证charsequence是否为空
     *
     * @param c
     * @return
     */
    public static boolean isEmpty(CharSequence c) {
        return ((c == null) || (c.length() == 0));
    }

    /**
     * 验证一个对象是否非空，支持String,Map,Collection等。
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isBaseEmpty(o);
    }

    /**
     * 验证String是否不为空
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return ((s != null) && (s.length() > 0));
    }

    /**
     * 验证数组是否为空
     *
     * @param arrays
     * @return
     */
    public static boolean isNotEmpty(Object[] arrays) {
        return ((arrays == null) || (arrays.length > 0));
    }

    /**
     * 验证Collection是否不为空
     *
     * @param c
     * @param <E>
     * @return
     */
    public static <E> boolean isNotEmpty(Collection<E> c) {
        return ((c != null) && (c.size() > 0));
    }

    /**
     * 验证CharSequence是否不能为空
     *
     * @param c
     * @return
     */
    public static boolean isNotEmpty(CharSequence c) {
        return ((c != null) && (c.length() > 0));
    }

    /**
     * 基类
     *
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    private static boolean isBaseEmpty(Object value) {
        if (value == null) return true;
        if (value instanceof String) return CommonUtils.isEmpty((String) value);
        if (value instanceof Collection) return CommonUtils.isEmpty((Collection<? extends Object>) value);
        if (value instanceof Map) return CommonUtils.isEmpty((Map<? extends Object, ? extends Object>) value);
        if (value instanceof CharSequence) return CommonUtils.isEmpty((CharSequence) value);
        // These types would flood the log
        // Number covers: BigDecimal, BigInteger, Byte, Double, Float, Integer, Long, Short
        if (value instanceof Boolean) return false;
        if (value instanceof Number) return false;
        if (value instanceof Character) return false;
        if (value instanceof java.sql.Timestamp) return false;
        return false;
    }
}
