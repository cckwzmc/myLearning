package com.toney.publish.tpl.service;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class PublishChannelService extends PublishAbstractService {

	private static final XLogger Logger = XLoggerFactory
			.getXLogger(PublishChannelService.class);
	boolean isRunning = false;
	AbstractApplicationContext applicationContext;

	@Override
	public void process() {
		
	}

//	@Override
//	public void run() {
//
//	}

	@Override
	public synchronized void start() {
		if (isRunning) {
			return;
		}
		this.isRunning = true;
//		this.run();
	}

	@Override
	public synchronized void stop() {
		if (!isRunning) {
			return;
		}
		// TODO stop publish channel service
	}

	@Override
	public synchronized boolean isRunning() {
		// TODO running publish channel service
		return isRunning;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = (AbstractApplicationContext) applicationContext;
	}

}
