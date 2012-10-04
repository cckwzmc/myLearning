package com.toney.crawler.collection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerListTaskModel;

/**
 * @author toney.li
 * 对应dede_crawler_task，抓取站点级别的任务表.
 */
@Repository("crawlerListTaskDao")
public interface CrawlerListTaskDao {
	
	public void insert(CrawlerListTaskModel model);
	public void updateStatus(CrawlerListTaskModel model);
	public void updateIsEnabled(CrawlerListTaskModel model);
	public List<CrawlerListTaskModel> getAll();
	public List<CrawlerListTaskModel> getEnabledAll();
	
}
