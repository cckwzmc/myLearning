package com.toney.crawler.collection.jobs;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.toney.crawler.collection.Exception.CrawlerCollectionException;

/**
 * @author toney.li
 * 抓取列表页。
 * <ul>
 * 	<li>抓取任务表,任务表将录入首页和搜索页,录入关键字，关键字将标识该页面是否是列表页,结束关键字如果遇到结束关键字将停止扫描。</li>
 * 	<li>程序中将扫描页面中的 a 标签并且必须是有正确的href</li>
 * 	<li>抓取url日志，抓取的list URL  入库。有查重功能。</li>
 * </ul>
 */
public class CrawlerListPageJobImpl implements CrawlerListPageJob {
	private static final XLogger logger =XLoggerFactory.getXLogger(CrawlerListPageJobImpl.class);
	
	
	@Override
	public void crawlerList() throws CrawlerCollectionException{
		logger.info("...开始抓取列表页...");
		
		logger.info("...结束抓取列表页...");
	}
}
