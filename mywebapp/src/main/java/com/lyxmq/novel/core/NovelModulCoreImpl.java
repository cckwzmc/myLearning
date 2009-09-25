package com.lyxmq.novel.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lyxmq.novel.exception.InitializationException;

@Service("novelCore")
public class NovelModulCoreImpl implements NovelModulCore {
	private static final Log log = LogFactory.getLog(NovelModulCoreImpl.class);

	@Override
	public void initialize() throws InitializationException {
		log.debug("Novel Modul business initialize.");
	}

	@Override
	public void shutdown() {
		log.debug("Novel Modul business shutdown.");
	}

}
