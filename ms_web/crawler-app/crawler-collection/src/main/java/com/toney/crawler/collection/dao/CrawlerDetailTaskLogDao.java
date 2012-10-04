package com.toney.crawler.collection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerTaskModel;

/**
 * @author toney.li
 * 对应dede_crawler_dttask_log，详情页面的抓取日志
 */
@Repository("crawlerDetailTaskLogDao")
public interface CrawlerDetailTaskLogDao {
	
	public List<CrawlerTaskModel> getAll();
	
}
