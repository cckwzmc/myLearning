package com.toney.publish.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * @author toney.li
 * 出版过程ＡＯＰ.
 */
@Aspect
public class PublishServiceAspect {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishServiceAspect.class);
	
	@Before("execution(* publish(...))")
	public void beforPublicService(){
		logger.info("...publish...");
	}
}
