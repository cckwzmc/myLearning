package com.lyxmq.novel.service.core;

import com.lyxmq.novel.exception.BootstrapException;

/**
 * Provides access to a Weblogger instance.
 */
public interface NovelProvider {
	/**
	 * Trigger bootstrapping.
	 * 
	 * @throws BootstrapException
	 */
	public void bootstrap() throws BootstrapException;

	/**
	 * 
	 * Get a Weblogger instance.
	 * 
	 * @return
	 */
	public NovelServiceCore getNovelServiceCore();
}
