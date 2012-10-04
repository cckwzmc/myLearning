package com.toney.crawler.collection.dao;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerListTaskLogModel;

/**
 * @author toney.li
 * 对应dede_crawler_listtask_log，抓取列表级别的任务日志.
 */
@Repository("crawlerListTaskLogDao")
public interface CrawlerListTaskLogDao {
	public void insert(CrawlerListTaskLogModel model);
}
