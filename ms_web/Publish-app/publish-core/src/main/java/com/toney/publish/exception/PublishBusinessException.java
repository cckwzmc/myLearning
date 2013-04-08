package com.toney.publish.exception;

public class PublishBusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4271800728137607186L;

	public PublishBusinessException() {
		super();
	}

	public PublishBusinessException(String message) {
		super(message);
	}

	public PublishBusinessException(Throwable t) {
		super(t);
	}

	public PublishBusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
