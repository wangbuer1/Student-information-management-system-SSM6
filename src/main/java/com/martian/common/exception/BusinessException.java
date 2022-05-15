package com.martian.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = -2095898810922999028L;
    /**
     * 异常状态码
     */
    private Integer code;

    /**
     *
     */
    private String message;


    public BusinessException() {
        super();
    }

    public BusinessException(ExceptionType type) {
        super(type.getDescription());
        this.code = type.getCode();
        this.message = type.getDescription();
    }

    public BusinessException(ExceptionType type, String msg) {
        super(type.getDescription());
        this.code = type.getCode();
        if (StringUtils.isBlank(msg)) {
            this.message = type.getDescription();

        } else {
            this.message = type.getDescription() + ":" + msg;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
