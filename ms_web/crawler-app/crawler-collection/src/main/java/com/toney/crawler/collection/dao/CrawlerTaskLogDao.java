package com.toney.crawler.collection.dao;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerTaskModel;

/**
 * @author toney.li
 * 对应dede_crawler_task，抓取站点级别的任务表.
 */
@Repository("crawlerTaskLogDao")
public interface CrawlerTaskLogDao {
	
	public void insert(CrawlerTaskModel model);
}
