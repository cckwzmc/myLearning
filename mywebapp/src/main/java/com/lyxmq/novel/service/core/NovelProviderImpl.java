package com.lyxmq.novel.service.core;

import com.lyxmq.novel.exception.BootstrapException;
public class NovelProviderImpl implements NovelProvider {

	private static NovelServiceCore novelInstance;
	private NovelServiceCore novelCore;

	public NovelServiceCore getNovelCore() {
		return novelCore;
	}

	public void setNovelCore(NovelServiceCore novelCore) {
		this.novelCore = novelCore;
	}

	public NovelProviderImpl() {

	}

	@Override
	public void bootstrap() throws BootstrapException {
		novelInstance = getNovelCore();
	}

	@Override
	public NovelServiceCore getNovelServiceCore() {
		return novelInstance;
	}

}
