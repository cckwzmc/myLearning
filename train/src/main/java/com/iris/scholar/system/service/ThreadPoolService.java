package com.iris.scholar.system.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 线程池服务类
 * 
 * @author DigitalSonic
 */
public class ThreadPoolService {
	private static final Log log = LogFactory.getLog(ThreadPoolService.class);
	/**
	 * 默认线程池大小
	 */
	public static final int DEFAULT_POOL_SIZE = 5;

	/**
	 * 默认一个任务的超时时间，单位为毫秒
	 */
	public static final long DEFAULT_TASK_TIMEOUT = 1000;

	private int poolSize = DEFAULT_POOL_SIZE;
	// 线程池维护线程的最少数量
	private final static int CORE_POOL_SIZE = 4;

	// 线程池维护线程的最大数量
	private final static int MAX_POOL_SIZE = 10;

	// 线程池维护线程所允许的空闲时间
	private final static int KEEP_ALIVE_TIME = 0;

	// 线程池所使用的缓冲队列大小
	private final static int WORK_QUEUE_SIZE = 20;

	private ExecutorService executorService;
	// 消息缓冲队列
	Queue<SOAPMessage> msgQueue = new LinkedList<SOAPMessage>();
	final ThreadPoolExecutor threadPool;

	/**
	 * 根据给定大小创建线程池
	 */
	public ThreadPoolService(int poolSize) {
		// setPoolSize(poolSize);
		threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), this.handler);
	}

	private static boolean stop = false;

	public static void exit() {
		stop = true;
	}

	/**
	 * 使用线程池中的线程来执行任务
	 */
	public void execute(Runnable task) {
		threadPool.execute(task);
	}

	final Runnable accessBufferThread = new Thread() {
		public void run() {
			if (!msgQueue.isEmpty()) {
//				log.info("WriteDataSOAPService.MSG_MAP.size()===" + msgQueue.size());
				SOAPMessage msg = (SOAPMessage) msgQueue.poll();
				Runnable task = new WriteDataSOAPTask(msg);
//				log.info("WriteDataSOAPService.MSG_MAP.size()=== task==" + msgQueue.size());
				threadPool.execute(task);
			}
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//			log.info(((WriteDataSOAPTask) r).getSoapMsg().getMessageId() + "消息放入队列中重新等待执行");
			msgQueue.offer(((WriteDataSOAPTask) r).getSoapMsg());
		}
	};

	// 调度线程池
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	final ScheduledFuture taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, 1, TimeUnit.SECONDS);

	/**
	 * 在线程池中执行所有给定的任务并取回运行结果，使用默认超时时间
	 * 
	 * @see #invokeAll(List, long)
	 */
	public List<SOAPMessage> invokeAll(List<ValidationTask> tasks) {
		return invokeAll(tasks, DEFAULT_TASK_TIMEOUT * tasks.size());
	}

	/**
	 * 在线程池中执行所有给定的任务并取回运行结果
	 * 
	 * @param timeout
	 *            以毫秒为单位的超时时间，小于0表示不设定超时
	 * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection)
	 */
	public List<SOAPMessage> invokeAll(List<ValidationTask> tasks, long timeout) {
		List<SOAPMessage> nodes = new ArrayList<SOAPMessage>(tasks.size());
		try {
			List<Future<SOAPMessage>> futures = null;
			if (timeout < 0) {
				futures = executorService.invokeAll(tasks);
			} else {
				futures = executorService.invokeAll(tasks, timeout, TimeUnit.MILLISECONDS);
			}
			for (Future<SOAPMessage> future : futures) {
				try {
					nodes.add(future.get());
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nodes;
	}

	/**
	 * 关闭当前ExecutorService
	 * 
	 * @param timeout
	 *            以毫秒为单位的超时时间
	 */
	public void destoryExecutorService(long timeout) {
		if (executorService != null && !executorService.isShutdown()) {
			try {
				executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executorService.shutdown();
		}
	}

	/**
	 * 关闭当前ExecutorService，随后根据poolSize创建新的ExecutorService
	 */
	public void createExecutorService() {
		destoryExecutorService(1000);
		executorService = Executors.newFixedThreadPool(poolSize);
	}

	/**
	 * 调整线程池大小
	 * 
	 * @see #createExecutorService()
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
		createExecutorService();
	}
}
