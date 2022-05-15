package com.martian.common.exception;


import com.martian.common.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 业务断言,当不满足条件时,
 * 抛出封装了具体ExceptionType的BusinessException
 */
public class BusinessAsserts {

    private BusinessAsserts() {
    }

    public static void isNull(Object object, ExceptionType throwing) {
        if (CommonUtils.isNotEmpty(object)) {
            throw new BusinessException(throwing);
        }
    }

    public static void notNull(Object object, ExceptionType throwing) {
        if (CommonUtils.isEmpty(object)) {
            throw new BusinessException(throwing);
        }
    }

    public static void isTrue(boolean expression, ExceptionType throwing) {
        if (!expression) {
            throw new BusinessException(throwing);
        }
    }

    public static void notBlank(String string, ExceptionType throwing) {
        if (StringUtils.isBlank(string)) {
            throw new BusinessException(throwing);
        }
    }
}
