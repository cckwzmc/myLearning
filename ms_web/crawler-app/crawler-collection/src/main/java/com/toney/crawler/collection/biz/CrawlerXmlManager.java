package com.toney.crawler.collection.biz;

import java.util.List;

import com.toney.crawler.collection.model.CrawlerXmlModel;
import com.toney.crawler.common.exception.BusinessException;

/**
 * @author toney.li
 *  抓取xml配置.
 */
public interface CrawlerXmlManager {
	
	public List<CrawlerXmlModel> getAll() throws BusinessException;

	public List<CrawlerXmlModel> getEnabledAll() throws BusinessException;
	
	/**
	 * @param cid 站点id
	 * @param type 抓取类型  list/detail
	 * @return
	 * @throws BusinessException
	 */
	public CrawlerXmlModel getCrawlerXmlById(Integer cid,String type) throws BusinessException;

	/**
	 * @param id
	 * @param xml
	 * @throws BusinessException
	 */
	void updateCrawlerXml(Integer id, String xml) throws BusinessException; 
}
