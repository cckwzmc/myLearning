package com.lyxmq.novel.core;

import com.lyxmq.novel.exception.BootstrapException;

public class NovelProviderImpl implements NovelProvider {

	private static NovelModulCore novelInstance;
	public NovelProviderImpl(){
		
	}
	
	@Override
	public void bootstrap() throws BootstrapException {

	}

	@Override
	public NovelModulCore getNovelModulCore() {
		return novelInstance;
	}

}
