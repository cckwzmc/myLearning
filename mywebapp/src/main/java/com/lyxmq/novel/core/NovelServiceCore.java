package com.lyxmq.novel.core;

import com.lyxmq.novel.exception.InitializationException;
import com.lyxmq.novel.publish.NovelArchivesManager;
import com.lyxmq.novel.publish.NovelTypeManager;
import com.lyxmq.novel.publish.ThemeManager;
import com.lyxmq.novel.resource.FileManager;
import com.lyxmq.novel.resource.PropertiesManager;
import com.lyxmq.novel.system.IndexManager;
import com.lyxmq.novel.system.ThreadManager;
import com.lyxmq.novel.user.NovelUserManager;

public interface NovelServiceCore {
	/**
	 * Get PropertiesManager associated with this Novel instance.
	 */
	public PropertiesManager getPropertiesManager();

	/**
	 * Get FileManager associated with this Novel instance.
	 */
	public FileManager getFileManager();

	/**
	 * Get ThreadManager associated with this Novel instance.
	 */
	public ThreadManager getThreadManager();

	/**
	 * Get IndexManager associated with this Novel instance.
	 */
	public IndexManager getIndexManager();

	/**
	 * Get ThemeManager associated with this Novel instance.
	 */
	public ThemeManager getThemeManager();
	/**
	 * Get NovelTypeManager associated with this Novel instance.
	 */
	public NovelTypeManager getNovelTypeManager();
	/**
	 * Get NovelArchivesManager associated with this Novel instance.
	 */
	public NovelArchivesManager getNovelArchivesManager();

	/**
	 * Get UserManager associated with this Novel instance.
	 */
	public NovelUserManager getNovelUserManager();

	/**
	 * Release all resources necessary for this instance of Novel.
	 */
	public void shutdown();

	/**
	 * Initialize any resources necessary for this instance of Novel.
	 */
	public void initialize() throws InitializationException;
}
