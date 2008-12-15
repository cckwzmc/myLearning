package org.model.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyPerson {
	private static final Logger logger = LoggerFactory.getLogger(ProxyPerson.class);
	public ProxyPerson(){
		
	}
	public ProxyPerson getInstance(String proxyArg){
		try {
			Class clazz=Class.forName(proxyArg);
			((Person)clazz.newInstance()).saidMessage();
		} catch (ClassNotFoundException e) {
			logger.error(e+"");
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}
		return new ProxyPerson();
	}
}
