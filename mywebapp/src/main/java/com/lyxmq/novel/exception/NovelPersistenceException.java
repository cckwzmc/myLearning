package com.lyxmq.novel.exception;

public class NovelPersistenceException extends NovelException {
	private static final long serialVersionUID = 1L;

	public NovelPersistenceException() {
		super();
	}

	public NovelPersistenceException(String message) {
		super(message);
	}

	public NovelPersistenceException(Throwable t) {
		super(t);
	}
}
