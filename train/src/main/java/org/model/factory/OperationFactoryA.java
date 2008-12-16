package org.model.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationFactoryA extends RealOperatiaon implements Factory {
	private static final Logger logger = LoggerFactory.getLogger(OperationFactoryA.class);

	@Override
	public RealOperatiaon getOperation() {
		return new OperationFactoryA();
	}

	@Override
	protected void saidMessage() {
		logger.info("======================"+OperationFactoryA.class.getName()+"=========================");
	}

}
