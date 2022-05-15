package com.martian.common.util;

import java.util.EnumSet;

/**
 * 枚举操作工具类
 */
public class EnumUtils {

    /**
     * 将指定枚举按序号转换成枚举类
     *
     * @param clazz   枚举类Class
     * @param ordinal 序号
     * @param <T>     枚举
     * @return 枚举类序号对应值
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> T ordinalOf(Class<T> clazz, int ordinal) {
        return ordinalOf(clazz, ordinal, null);
    }

    /**
     * 将指定枚举按序号转换成枚举类
     *
     * @param clazz   枚举类Class
     * @param ordinal 序号
     * @param <T>     枚举
     * @return 枚举类序号对应值
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> T ordinalOf(Class<T> clazz, int ordinal, T defaultValue) {
        EnumSet enumSet = EnumSet.allOf(clazz);
        for (Object object : enumSet) {
            if (object instanceof Enum) {
                Enum e = (Enum) object;
                if (e.ordinal() == ordinal) {
                    return (T) e;
                }
            }
        }
        return defaultValue;
    }

    /**
     * 将指定枚举字符串转换成枚举类
     *
     * @param clazz 枚举类Class
     * @param name  枚举字符串
     * @param <T>   枚举
     * @return 枚举类型
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> T nameOf(Class<T> clazz, String name) {
        return nameOf(clazz, name, null);
    }

    /**
     * 将指定枚举字符串转换成枚举类
     *
     * @param clazz 枚举类Class
     * @param name  枚举字符串
     * @param <T>   枚举
     * @return 枚举类型
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> T nameOf(Class<T> clazz, String name, T defaultValue) {
        EnumSet enumSet = EnumSet.allOf(clazz);
        for (Object object : enumSet) {
            if (object instanceof Enum) {
                Enum e = (Enum) object;
                if (e.name().equals(name)) {
                    return (T) e;
                }
            }
        }
        return defaultValue;
    }
}
