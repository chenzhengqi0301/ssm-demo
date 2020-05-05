package com.czq.ssmdemo.Service.ex;

public class IlllegalArgumentException extends ServiceException{
    public IlllegalArgumentException() {
        super();
    }

    public IlllegalArgumentException(String message) {
        super(message);
    }

    public IlllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IlllegalArgumentException(Throwable cause) {
        super(cause);
    }

    protected IlllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
