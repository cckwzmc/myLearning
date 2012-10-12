package com.toney.crawler.collection.jobs;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.crawler.collection.Exception.CrawlerException;
import com.toney.crawler.collection.executor.CrawlerServiceExecutor;
import com.toney.crawler.common.exception.BusinessException;

/**
 * @author toney.li 抓取列表页。
 *         <ul>
 *         <li>抓取任务表,任务表将录入首页和搜索页,录入关键字，关键字将标识该页面是否是列表页,结束关键字如果遇到结束关键字将停止扫描。</li>
 *         <li>程序中将扫描页面中的 a 标签并且必须是有正确的href</li>
 *         <li>抓取url日志，抓取的list URL 入库。有查重功能。</li>
 *         </ul>
 */
public class CrawlerPageJobImpl implements CrawlerPageJob {
	private static final XLogger logger = XLoggerFactory.getXLogger(CrawlerPageJobImpl.class);

//	@Autowired
//	private CrawlerServiceExecutor crawlerServiceExecutor;
	@Autowired Job crawlerHomeTaskExecutionJob;
	@Autowired JobLauncher jobLauncher;
	@Override
	public void crawlerHome() throws CrawlerException {
		logger.info("...开始抓取首页...");
		JobParameters arg1=new JobParameters();
		try {
//			JobLauncher jobLauncher=new SimpleJobLauncher();
			jobLauncher.run(crawlerHomeTaskExecutionJob, arg1);
		} catch (JobExecutionAlreadyRunningException e) {
			logger.error("CrawlerPageJobImpl",e);
		} catch (JobRestartException e) {
			logger.error("CrawlerPageJobImpl",e);
		} catch (JobInstanceAlreadyCompleteException e) {
			logger.error("CrawlerPageJobImpl",e);
		} catch (JobParametersInvalidException e) {
			logger.error("CrawlerPageJobImpl",e);
		}
		//crawlerServiceExecutor.startExecutor();
		logger.info("...结束抓取首页...");
	}

	@Override
	public void crawlerList() throws CrawlerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void crawlerDetail() throws CrawlerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void crawlerSearch() throws CrawlerException {
		// TODO Auto-generated method stub

	}
}
