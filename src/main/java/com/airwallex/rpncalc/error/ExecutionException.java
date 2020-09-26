package com.airwallex.rpncalc.error;

public class ExecutionException extends Exception {
    ErrorCode errorCode;

    public ExecutionException(ErrorCode e) {
        errorCode = e;
    }

    public ExecutionException(String ex) {
        super(ex);
        errorCode = ErrorCode.InternalError;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
