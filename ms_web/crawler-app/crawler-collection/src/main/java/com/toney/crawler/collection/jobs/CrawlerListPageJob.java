package com.toney.crawler.collection.jobs;

import com.toney.crawler.collection.Exception.CrawlerCollectionException;

/**
 * @author toney.li
 * 抓取列表页。
 */
public interface CrawlerListPageJob {

	void crawlerList() throws CrawlerCollectionException;

}
