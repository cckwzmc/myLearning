package org.model.decorator;

import org.slf4j.LoggerFactory;

public class Test {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Test.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("test Decorator!!");
		NokiaPhone n_phone = new NokiaPhone();
		DecoratoraA a = new DecoratoraA();
		DecoratoraB b = new DecoratoraB();
		a.setDecorator(n_phone);
		b.setDecorator(a);
		b.callNumber();
	}

}
