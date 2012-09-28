package com.toney.crawler.collection.biz;

import com.toney.crawler.collection.model.CrawlerModel;

/**
 * @author toney.li
 * 抓取接口
 * 
 */
public interface CrawlerBizManager extends Runnable{
	/**
	 * 业务处理方法
	 */
	public void process(CrawlerModel model);
	
	/**
	 * @return
	 * 当前业务的运行状态设置。
	 */
	public boolean getRunSetting();
	
	/**
	 * @return
	 * 获取当前运行线程指定的ID；
	 */
	public String getCurrentCrawlerId();
	
	/**
	 * @return
	 * 获取当前在容器中的线程ID。
	 */
	public int getTreadId();
	/**
	 * 如果运行状态为false，需要先暂停线程。
	 */
	public void stop();
	
	/**
	 * 如果运行状态为false，再判断是否需要清理当前线程。需要则调用clear方法。
	 */
	public boolean isClear();
	
	public void clear();
}
