package com.martian.common.handler;

import com.martian.common.exception.BusinessException;
import com.martian.common.exception.Exceptions;
import com.martian.common.result.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public static JSONResult exceptionHandler(Exception e, HttpServletResponse response, HttpServletRequest request) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            logger.debug("exception  code:[{}],message:[{}]", businessException.getCode(), businessException.getMessage());
            return new JSONResult(businessException.getCode(), businessException.getMessage());
        } else if (e instanceof MissingServletRequestParameterException || e instanceof BindException) {
            logger.debug("exception  code:[{}],message:[{}]", Exceptions.System.REQUEST_PARAM_ERROR.getCode(), e.getMessage());
            return new JSONResult(Exceptions.System.REQUEST_PARAM_ERROR.getCode(), Exceptions.System.REQUEST_PARAM_ERROR.getDescription());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            logger.debug("exception  code:[{}],message:[{}]", Exceptions.System.REQUEST_METHOD_ERROR.getCode(), e.getMessage());
            return new JSONResult(Exceptions.System.REQUEST_METHOD_ERROR.getCode(), Exceptions.System.REQUEST_METHOD_ERROR.getDescription());
        } else {
            logger.debug("exception  code:[{}],message:[{}]", Exceptions.System.SYSTEM_ERROR.getCode(), e.getMessage());
            return new JSONResult(Exceptions.System.SYSTEM_ERROR.getCode(), Exceptions.System.SYSTEM_ERROR.getDescription());
        }
    }
}
