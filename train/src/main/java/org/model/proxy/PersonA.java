package org.model.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonA implements Person{
	private static final Logger logger=LoggerFactory.getLogger(PersonA.class);
	@Override
	public void saidMessage() {
		logger.info("哈哈"+"====="+"I'm Person A!");
	}

}
