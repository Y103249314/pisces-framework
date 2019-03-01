/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.core;

/**
 * FastDFS Exception
 */
public class FastDFSException extends Exception {
	
	private static final long serialVersionUID = -6945495473324594904L;
	
	private int errorCode = 0;

    public FastDFSException(int errorCode) {
        this.setErrorCode(errorCode);
    }

    public FastDFSException(String message, int errorCode) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public FastDFSException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.setErrorCode(errorCode);
    }

    public FastDFSException(Throwable cause, int errorCode) {
        super(cause);
        this.setErrorCode(errorCode);
    }

    public FastDFSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                            int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setErrorCode(errorCode);
    }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
