package com.iris.scholar.system.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iris.scholar.server.ws.dao.ScholarFetchDao;

public class WriteDataSOAPService {
	private static final Log log = LogFactory.getLog(WriteDataSOAPService.class);
	private ScholarFetchDao fetchDao = null;
	/**
	 * 全局节点表
	 */
	private ThreadPoolService threadPoolService;
	
	public WriteDataSOAPService(ThreadPoolService threadPoolService) {
		this.threadPoolService = threadPoolService;
	}
//	public static final Queue<SOAPMessage> MSG_MAP = new ConcurrentLinkedQueue<SOAPMessage>();
//	public static final Queue<SOAPMessageResult> RESULT_MAP = new LinkedList<SOAPMessageResult>();
	public static final Map<String,Integer> MSG_COUNT_MAP = new ConcurrentHashMap<String,Integer>();
	public static final Map<String,String> RESULT_MAP= new ConcurrentHashMap<String,String>();
//	private ThreadPoolService threadPoolService;
//
//	public WriteDataSOAPService(ThreadPoolService threadPoolService) {
//		this.threadPoolService = threadPoolService;
//	}
//
//	public void writeDataMsg(SOAPMessage message) {
//		System.out.println("messageID==="+message.getMessageId());
//	}
//
//	private boolean hasMoreAcquire() {
//		return !MSG_MAP.isEmpty();
//	}
//
//	public ScholarFetchDao getFetchDao() {
//		return fetchDao;
//	}
//
//	public void setFetchDao(ScholarFetchDao fetchDao) {
//		this.fetchDao = fetchDao;
//	}

//	private class PoolWorker extends Thread {
//		boolean stopped = false;
//
//		public void exit() {
//			stopped = true;
//		}
//		@Override
//		public void run() {
//			while (!stopped){
//				synchronized (MSG_MAP) {
//					if(hasMoreAcquire()){
//						writeDataMsg(MSG_MAP.poll());
//					}
//				}
//			}
//		}
//	}
}
