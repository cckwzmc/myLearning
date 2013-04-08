package com.toney.publish.exception;

public class PublishException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4271800728137607186L;

	public PublishException() {
		super();
	}

	public PublishException(String message) {
		super(message);
	}

	public PublishException(Throwable t) {
		super(t);
	}

	public PublishException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
