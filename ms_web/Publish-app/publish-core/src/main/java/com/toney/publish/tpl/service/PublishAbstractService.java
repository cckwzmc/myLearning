package com.toney.publish.tpl.service;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.AbstractApplicationContext;

public abstract class PublishAbstractService implements Lifecycle,
		ApplicationContextAware {
	AbstractApplicationContext applicationContext;

	public AbstractApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(
			AbstractApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void process() {
		
	}

}
