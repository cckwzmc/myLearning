package com.toney.crawler.collection.dao;

import java.util.List;

import com.toney.crawler.collection.model.CrawlerCollectionXmlModel;

/**
 * @author toney.li
 * 对应dede_crawler_xml，抓取页面内容配置表.
 */
public interface CrawlerCollectionXmlDao {
	 public List<CrawlerCollectionXmlModel> getAll();
}
