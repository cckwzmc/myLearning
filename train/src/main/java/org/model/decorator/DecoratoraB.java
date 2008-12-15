package org.model.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecoratoraB extends Decorator{
	private static final Logger logger=LoggerFactory.getLogger(DecoratoraB.class);
	@Override
	public void callNumber() {
		this.selfFunction("callNumber");
		this.phone.callNumber();
	}

	@Override
	public void sendMessage() {
		this.selfFunction("sendMessage");
		this.phone.sendMessage();
	}
	public void selfFunction(String methodName){
		logger.info("==B == self == function "+methodName);
	}
}
