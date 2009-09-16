package com.lyxmq.novel.exception;

public class BootstrapException extends NovelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1641939545826133960L;

	public BootstrapException(String message) {
		super(message);
	}

	public BootstrapException(String message, Throwable t) {
		super(message, t);
	}
}
