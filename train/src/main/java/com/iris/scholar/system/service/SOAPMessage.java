package com.iris.scholar.system.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 节点类
 * 
 * @author DigitalSonic
 */
public class SOAPMessage {
	private String messageId;
	private int insId;
	private int year;
	private int dbid;
	private String xmlData;
	private Lock lock = new ReentrantLock();

	/**
	 * 默认构造方法
	 */
	public SOAPMessage() {
	}

	/**
	 * 构造节点对象，设置名称及WSDL
	 */
	public SOAPMessage(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * 返回包含节点名称、WSDL以及验证结果的字符串
	 */

	public Lock getLock() {
		return lock;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getInsId() {
		return insId;
	}

	public void setInsId(int insId) {
		this.insId = insId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDbid() {
		return dbid;
	}

	public void setDbid(int dbid) {
		this.dbid = dbid;
	}

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}

}
