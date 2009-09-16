package com.lyxmq.novel.exception;

/**
 * A base exception class for Novel module.
 */
public class NovelException extends Exception {
	private static final long serialVersionUID = -2082365026545455167L;

	/**
	 * Construct with message string
	 * 
	 * @param message
	 */
	public NovelException(String message) {
		super(message);
	}

	/**
	 * Construct ,wrapping existing throwable.
	 * 
	 * @param message
	 * @param t
	 */
	public NovelException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * construct,wrapping existing throwable.
	 * 
	 * @param t
	 */
	public NovelException(Throwable t) {
		super(t);
	}

	/**
	 * default construct
	 */
	public NovelException() {
		super();
	}
}
