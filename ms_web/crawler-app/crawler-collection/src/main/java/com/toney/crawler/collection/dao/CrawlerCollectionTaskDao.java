package com.toney.crawler.collection.dao;

import java.util.List;

import com.toney.crawler.collection.model.CrawlerCollectionTaskModel;

/**
 * @author toney.li
 * 对应dede_crawler_task，抓取站点级别的任务表.
 */
public interface CrawlerCollectionTaskDao {
	
	public List<CrawlerCollectionTaskModel> getAll();
	
}
