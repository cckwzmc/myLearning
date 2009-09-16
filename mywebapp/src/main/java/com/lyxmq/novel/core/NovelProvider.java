package com.lyxmq.novel.core;

import com.lyxmq.novel.exception.BootstrapException;

public interface NovelProvider {
	public void bootstrap() throws BootstrapException;

	public NovelModulCore getNovelModulCore();
}
