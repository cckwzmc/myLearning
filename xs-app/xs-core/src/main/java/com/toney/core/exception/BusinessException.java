package com.toney.core.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2997388485750831733L;

	public BusinessException(String message, Throwable t) {
		super(message, t);
	}

	public BusinessException() {
		super();
	}
	public BusinessException(Throwable t) {
		super(t);
	}
	public BusinessException(String message) {
		super(message);
	}
}
