package com.lottery.ssq.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolService {
	/**
	 * 默认线程池大小
	 */
	public static final int DEFAULT_POOL_SIZE = 5;
	/**
	 * 默认一个任务的超时时间，单位为毫秒
	 */
	public static final long DEFAULT_TASK_TIMEOUT = 1000;

	// 线程池维护线程的最少数量
	private final static int CORE_POOL_SIZE = 1;

	// 线程池维护线程的最大数量
	private final static int MAX_POOL_SIZE = 2;
	
	// 线程池维护线程所允许的空闲时间
	private final static int KEEP_ALIVE_TIME = 0;

	// 线程池所使用的缓冲队列大小
	private final static int WORK_QUEUE_SIZE = 10;
	
	private ExecutorService executorService;
	
	// 消息缓冲队列
	Queue<LotterySsqFilterBean> msgQueue = new LinkedList<LotterySsqFilterBean>();
	final ThreadPoolExecutor threadPool;
	
	/**
	 * 根据给定大小创建线程池
	 */
	public ThreadPoolService(int poolSize) {
		// setPoolSize(poolSize);
		threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), this.handler);
	}
	
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// log.info(((WriteDataSOAPTask) r).getSoapMsg().getMessageId() + "消息放入队列中重新等待执行");
//			msgQueue.offer(((WriteDataSOAPTask) r).getSoapMsg());
		}
	};
}
