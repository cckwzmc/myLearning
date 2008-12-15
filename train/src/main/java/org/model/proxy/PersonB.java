package org.model.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonB implements Person{
	private static final Logger logger=LoggerFactory.getLogger(PersonA.class);

	@Override
	public void saidMessage() {
		logger.info("^_^"+" === "+ "I'm Person B!");
	}
}
