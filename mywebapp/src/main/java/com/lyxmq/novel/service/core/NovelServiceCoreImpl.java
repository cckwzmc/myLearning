package com.lyxmq.novel.service.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lyxmq.novel.exception.InitializationException;
import com.lyxmq.novel.service.publish.NovelArchivesManager;
import com.lyxmq.novel.service.publish.NovelTypeManager;
import com.lyxmq.novel.service.publish.ThemeManager;
import com.lyxmq.novel.service.resource.FileManager;
import com.lyxmq.novel.service.resource.PropertiesManager;
import com.lyxmq.novel.service.system.IndexManager;
import com.lyxmq.novel.service.system.ThreadManager;
import com.lyxmq.novel.service.user.NovelUserManager;

@Service("novelCore")
public class NovelServiceCoreImpl implements NovelServiceCore {
	private static final Log log = LogFactory.getLog(NovelServiceCoreImpl.class);

	private NovelUserManager novelUserManager;
	private PropertiesManager propertiesManager;
	private FileManager fileManager;
	private ThreadManager threadManager;
	private IndexManager indexManager;
	private ThemeManager themeManager;
	private NovelTypeManager novelTypeManager;
	private NovelArchivesManager novelArchivesManager;

	public PropertiesManager getPropertiesManager() {
		return propertiesManager;
	}

	public void setPropertiesManager(PropertiesManager propertiesManager) {
		this.propertiesManager = propertiesManager;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public ThreadManager getThreadManager() {
		return threadManager;
	}

	public void setThreadManager(ThreadManager threadManager) {
		this.threadManager = threadManager;
	}

	public IndexManager getIndexManager() {
		return indexManager;
	}

	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}

	public ThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(ThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public NovelTypeManager getNovelTypeManager() {
		return novelTypeManager;
	}

	public void setNovelTypeManager(NovelTypeManager novelTypeManager) {
		this.novelTypeManager = novelTypeManager;
	}

	public NovelArchivesManager getNovelArchivesManager() {
		return novelArchivesManager;
	}

	public void setNovelArchivesManager(NovelArchivesManager novelArchivesManager) {
		this.novelArchivesManager = novelArchivesManager;
	}

	public void setNovelUserManager(NovelUserManager novelUserManager) {
		this.novelUserManager = novelUserManager;
	}

	@Override
	public void initialize() throws InitializationException {
		log.debug("Novel Modul business initialize.");
	}

	@Override
	public void shutdown() {
		log.debug("Novel Modul business shutdown.");
	}

	@Override
	public NovelUserManager getNovelUserManager() {
		return novelUserManager;
	}

}
