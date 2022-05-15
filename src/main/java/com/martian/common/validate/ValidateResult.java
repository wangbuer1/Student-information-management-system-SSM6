package com.martian.common.validate;

/**
 *
 */
public class ValidateResult {
    //校验结果是否有错
    private boolean hasError;

    //校验错误信息
    private String errorMsg;

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
