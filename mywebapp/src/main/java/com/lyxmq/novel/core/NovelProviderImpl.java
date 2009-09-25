package com.lyxmq.novel.core;

import com.lyxmq.novel.exception.BootstrapException;
public class NovelProviderImpl implements NovelProvider {

	private static NovelModulCore novelInstance;
	private NovelModulCore novelCore;

	public NovelModulCore getNovelCore() {
		return novelCore;
	}

	public void setNovelCore(NovelModulCore novelCore) {
		this.novelCore = novelCore;
	}

	public NovelProviderImpl() {

	}

	@Override
	public void bootstrap() throws BootstrapException {
		novelInstance = getNovelCore();
	}

	@Override
	public NovelModulCore getNovelModulCore() {
		return novelInstance;
	}

}
