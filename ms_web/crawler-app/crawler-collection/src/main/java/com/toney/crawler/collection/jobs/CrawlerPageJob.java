package com.toney.crawler.collection.jobs;

import com.toney.crawler.collection.Exception.CrawlerException;

/**
 * @author toney.li
 * 抓取列表页。
 */
public interface CrawlerPageJob {

	/**
	 * 从首页开始抓取.
	 * @throws CrawlerException
	 */
	void crawlerHome() throws CrawlerException;
	/**
	 * 从列表页开始抓取.
	 * @throws CrawlerException
	 */
	void crawlerList() throws CrawlerException;
	/**
	 * 从详细页抓取
	 * @throws CrawlerException
	 */
	void crawlerDetail() throws CrawlerException;

	/**
	 * 从搜索页开始抓取
	 * @throws CrawlerException
	 */
	void crawlerSearch() throws CrawlerException;
}
