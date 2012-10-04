package com.toney.crawler.collection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerTaskModel;

/**
 * @author toney.li
 * 对应dede_crawler_task，抓取站点级别的任务表.
 */
@Repository("crawlerTaskDao")
public interface CrawlerTaskDao {
	
	public List<CrawlerTaskModel> getAll();
	
}
