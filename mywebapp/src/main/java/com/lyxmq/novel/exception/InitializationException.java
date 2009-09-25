package com.lyxmq.novel.exception;

public class InitializationException extends NovelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5746891475351335401L;

	/**
	 * 
	 */
	public InitializationException() {
		super();
	}

	/**
	 * @param message
	 */
	public InitializationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param t
	 */
	public InitializationException(String message, Throwable t) {
		super(message, t);
	}

}
