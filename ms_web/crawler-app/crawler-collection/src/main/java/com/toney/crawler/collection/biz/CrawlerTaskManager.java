package com.toney.crawler.collection.biz;

import java.util.List;

import com.toney.crawler.collection.model.CrawlerTaskModel;
import com.toney.crawler.common.exception.BusinessException;

/**
 * @author toney.li
 * 
 */
public interface CrawlerTaskManager {
	
	public List<CrawlerTaskModel> getAll() throws BusinessException;

	List<CrawlerTaskModel> getEnabledAll() throws BusinessException;

	/**
	 * 首页深度抓取。<br/>
	 * 对一个网站启动一个线程去抓取.
	 * @throws BusinessException 
	 */
	public void crawlerHome() throws BusinessException;
}
