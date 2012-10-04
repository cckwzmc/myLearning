package com.toney.crawler.collection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerDetailTaskModel;

/**
 * @author toney.li
 * 对应dede_crawler_dttask，抓取详细页面级别的任务表.
 */
@Repository("crawlerDetailTaskDao")
public interface CrawlerDetailTaskDao {
	
	public List<CrawlerDetailTaskModel> getAll();
	public List<CrawlerDetailTaskModel> getEnabledAll();
	public void insert(CrawlerDetailTaskModel model);
	public void updateStatus(CrawlerDetailTaskModel model);
	public void updateIsEnable(CrawlerDetailTaskModel model); 
}
