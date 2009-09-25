package com.lyxmq.novel.core;

import com.lyxmq.novel.exception.InitializationException;

public interface NovelModulCore {

	/**
	 * Release all resources necessary for this instance of Novel.
	 */
	public void shutdown();

	/**
	 * Initialize any resources necessary for this instance of Novel.
	 */
	public void initialize() throws InitializationException;
}
