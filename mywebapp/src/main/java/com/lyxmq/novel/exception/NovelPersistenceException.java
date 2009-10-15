package com.lyxmq.novel.exception;

import javax.persistence.PersistenceException;

public class NovelPersistenceException extends PersistenceException {
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
