package org.zhangmz.cymbidium.modules.exception;

public class ServiceException extends RuntimeException {

	public ErrorCode errorCode;

	public ServiceException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
