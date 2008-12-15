package org.model.decorator;

import org.slf4j.LoggerFactory;

public class DecoratoraA extends Decorator{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Test.class);
	@Override
	public void callNumber() {
		this.selfFunction("callNumber");
		super.callNumber();
	}
	@Override
	public void sendMessage() {
		this.selfFunction("sendMessage");
		super.sendMessage();
	}
	public void selfFunction(String methodName){
		logger.info("==A == self == function "+methodName);
	}

}
