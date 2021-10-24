package com.sullivan.lmall.service.ex;

/**
 * 描述 插入数据时的异常
 *
 * @author : 小蚊子
 * @date : 2021-10-16 14:16
 **/
public class InsertException extends ServiceException {
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
