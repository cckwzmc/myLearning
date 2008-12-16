package org.model.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RealOperatiaon {
	private static Logger logger = LoggerFactory.getLogger(RealOperatiaon.class);

	protected abstract void saidMessage();
	
	protected void said(){
		logger.info("============大家都要说话====================");
	}
}
