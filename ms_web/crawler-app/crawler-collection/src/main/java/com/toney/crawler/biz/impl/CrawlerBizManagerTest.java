package com.toney.crawler.biz.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.toney.crawler.biz.CrawlerBizManager;
import com.toney.crawler.model.CrawlerModel;

public class CrawlerBizManagerTest implements CrawlerBizManager {
	
	private static final XLogger LOGGER=XLoggerFactory.getXLogger(CrawlerBizManagerTest.class);
	private String id="";
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void run() {
		LOGGER.debug("Thread-id={} run current time {}" ,new Object[]{this.getTreadId(),System.currentTimeMillis()});
		System.out.println(String.format("Thread-id={%s} run current time={%s} id=%s", new Object[]{this.getTreadId(),System.currentTimeMillis(),id}));
		this.stop();
	}

	@Override
	public void process(CrawlerModel model) {
		this.run();
	}

	@Override
	public boolean getRunSetting() {
		return false;
	}

	@Override
	public String getCurrentCrawlerId() {
		return null;
	}

	@Override
	public int getTreadId() {
		return 0;
	}

	@Override
	public void stop() {

	}

	@Override
	public boolean isClear() {
		return false;
	}

	@Override
	public void clear() {

	}

}
