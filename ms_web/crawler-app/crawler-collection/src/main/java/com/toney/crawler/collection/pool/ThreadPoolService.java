package com.toney.crawler.collection.pool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Service;

import com.toney.crawler.collection.biz.CrawlerBizManager;


/**
 * 线程池服务类.
 * 
 */
@Service("threadPoolService")
public class ThreadPoolService {
	
	
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ThreadPoolService.class);
	/**
	 * 默认线程池大小
	 */
	public static final int DEFAULT_POOL_SIZE = 5;

	/**
	 * 默认一个任务的超时时间，单位为毫秒
	 */
	public static final long DEFAULT_TASK_TIMEOUT = 1000;

	// 线程池维护线程的最少数量
	private final static int CORE_POOL_SIZE = 5;

	// 线程池维护线程的最大数量
	private final static int MAX_POOL_SIZE = 10;

	// 线程池维护线程所允许的空闲时间
	private final static int KEEP_ALIVE_TIME = 40;

	// 线程池所使用的缓冲队列大小
	private final static int WORK_QUEUE_SIZE = 20;

//	private ExecutorService executorService;
	// 消息缓冲队列
	Queue<CrawlerBizManager> msgQueue = new LinkedList<CrawlerBizManager>();
	
	//~~~~~~~~~~~任务调度实例化~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	final Runnable accessBufferThread = new Thread() {
		public void run() {
			if (!msgQueue.isEmpty()) {
				CrawlerBizManager queue = (CrawlerBizManager) msgQueue.poll();
				threadPool.execute(queue);
			}
		}
	};
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println("-消息放入队列中重新等待执行");
			msgQueue.offer((CrawlerBizManager) r);
		}
	};

	// 调度线程池
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	final ScheduledFuture<?> taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, 1, TimeUnit.SECONDS);
	
	final ThreadPoolExecutor threadPool=new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE), handler);

	public void initService(){
	}
	public void putTask(Runnable rn){
		if(threadPool==null){
			this.initService();
		}
		threadPool.execute(rn);
	}
	
	public void resetExecutorService(){
		destoryExecutorService(1000);
		this.initService();
	}
	/**
	 * 关闭当前线程池 ExecutorService
	 * 
	 * @param timeout
	 *            以毫秒为单位的超时时间
	 */
	public void destoryExecutorService(long timeout) {
		if (threadPool != null && !threadPool.isShutdown()) {
			try {
				threadPool.awaitTermination(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				LOGGER.error("destoryExecutorService error={}",e);
			}
			threadPool.shutdown();
		}
	}

}