package com.martian.common.exception;

/**
 * 业务异常基础接口
 */
public interface ExceptionType {
    /**
     * 异常代码
     *
     * @return
     */
    int getCode();

    /**
     * 异常描述信息
     *
     * @return
     */
    String getDescription();
}
