package com.toney.crawler.collection.executor;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author toney.li
 *
 */
//@Service("crawlerHomeService")
//@Scope(value="prototype")
//@Lazy(value=false)
public class CrawlerHomeService implements CallableService {
	private static XLogger Logger=XLoggerFactory.getXLogger(CrawlerHomeService.class);
	@Override
	public Object call() throws Exception {
		Logger.info("...CrawlerHomeService call()...");
		return null;
	}

}
