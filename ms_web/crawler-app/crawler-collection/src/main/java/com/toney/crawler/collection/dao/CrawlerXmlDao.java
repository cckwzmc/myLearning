package com.toney.crawler.collection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.crawler.collection.model.CrawlerXmlModel;

/**
 * @author toney.li 对应dede_crawler_xml，抓取页面内容配置表.
 */
@Repository("crawlerXmlDao")
public interface CrawlerXmlDao {

	public List<CrawlerXmlModel> getAll();

	/**
	 * @param cid
	 *            站点id
	 * @param type
	 *            抓取类型 list/detail
	 * @param query
	 * @return
	 */
	public CrawlerXmlModel getCrawlerXmlById(CrawlerXmlModel query);

	/**
	 * 更新配置xml内容.
	 * @param query
	 */
	public void updateCrawlerXml(CrawlerXmlModel query);
}
