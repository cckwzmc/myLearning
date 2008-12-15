package org.model.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ：具体的对象
 * 在装饰UML图中位置：createComponent
 * @author Administrator
 *
 */
public class MotoPhone  implements Phone{
	private static final Logger logger=LoggerFactory.getLogger(DecoratoraB.class);
	@Override
	public void callNumber() {
		logger.info("=="+getClass().getName()+"==Method==callNumber==");
	}

	@Override
	public void sendMessage() {
		logger.info("=="+getClass().getName()+"==Method==sendMessage==");
	}
	
	public void saidMessage(){
		logger.info("=="+getClass().getName()+"==Method==saidMessage==");
	}

}
