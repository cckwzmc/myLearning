package org.model.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationFactoryB extends RealOperatiaon implements Factory {
	private static final Logger logger = LoggerFactory.getLogger(OperationFactoryB.class);

	@Override
	public RealOperatiaon getOperation() {
		return new  OperationFactoryB();
	}

	@Override
	protected void saidMessage() {
		logger.info("======================"+OperationFactoryB.class.getName()+"=========================");
	}

}
