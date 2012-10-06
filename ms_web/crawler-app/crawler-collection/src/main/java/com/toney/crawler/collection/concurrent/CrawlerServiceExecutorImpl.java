package com.toney.crawler.collection.concurrent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.toney.crawler.collection.biz.CrawlerTaskManager;

/**
 * @author toney.li
 * 该类为单例模式对象。
 */
public class CrawlerServiceExecutorImpl implements CrawlerServiceExecutor{
	
	private static final XLogger logger=XLoggerFactory.getXLogger(CrawlerServiceExecutorImpl.class);
	private ThreadPoolTaskExecutor crawlerHomeTaskExecutor;
	@Autowired
	CrawlerTaskManager crawlerTaskManager;
 	
	CallHandler callHandler;
	/**
	 * 采用单例模式，所以同步对象数据放这里. 
	 */
	private List<Long> crawlerData=new ArrayList<Long>();
	
	@Override
	public void startExecutor(){
//		if(CollectionUtils.isNotEmpty(crawlerData)){
//			logger.info("...抓取数据任然没有处理完成,处理完成后再获取处理数据...");
//			return ;
//		}
		logger.info("...开始初始化抓取线程...");
		for(Long i=0l;i<100;i++){
			crawlerData.add(i);
		}
		synchronized (crawlerData) {
			if(CollectionUtils.isNotEmpty(crawlerData)){
				for(int i=0;i<100;i++){
					callHandler=new CallHandler(crawlerData.get(i));
					crawlerHomeTaskExecutor.execute(callHandler);
				}
			}
		}
		logger.info("...结束初始化抓取线程...");
	}
	public void setCrawlerHomeTaskExecutor(ThreadPoolTaskExecutor crawlerHomeTaskExecutor) {
		this.crawlerHomeTaskExecutor = crawlerHomeTaskExecutor;
	}
	
	public void stopCrawlerHomeTaskExecutor(){
		if(this.crawlerHomeTaskExecutor!=null){
			this.crawlerHomeTaskExecutor.shutdown();
		}
	}
	
	@Component
	class CallHandler implements Runnable{
		
		public CallHandler(Long t){
			logger.info("... callhanlder "+t+" ...");
		}
		@Autowired
		private CrawlerHomeService crawlerHomeService;
		
		@Override
		public void run() {
			try {
				if(crawlerHomeService!=null)
				crawlerHomeService.call();
			} catch (Exception e) {
				logger.error("... callhanlder  ...",e);
			}
		}
		
	}

}
